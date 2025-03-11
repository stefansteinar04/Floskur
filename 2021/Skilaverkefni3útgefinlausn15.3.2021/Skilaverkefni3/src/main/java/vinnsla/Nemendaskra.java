/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Les og skrifar upplýsingar um nemendur. Einn nemandi per línu og bil
 *  á milli sviða
 *
 *
 *****************************************************************************/
package vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Nemendaskra implements Skra<Nemandi> {

    // fastar
    private static final String EOL = "\n";
    private static final String TOKST_EKKI_SKRIFT = "Tókst ekki að skrifa í skrá";
    private static final String SKRA_EKKI_TIL = "Skrá ekki til ";

    @Override
    public ObservableList<Nemandi> lesa(String s) {

        ObservableList<Nemandi> listi = FXCollections.observableArrayList();

        Scanner scanner = new Scanner(getClass().getResourceAsStream(s));
        while (scanner.hasNextLine()) {
            String lina = scanner.nextLine();
            Nemandi n = new Nemandi(lina);
            listi.add(n);
        }
     /* Ef kosið var að lesa ekki upplýsingar um nemanda
        listi.add(new Nemandi("Sigurður", "siggi@hi.is", Svid.HEI, Deild.HEI1, Namsleid.NHEI11));
        listi.add(new Nemandi("Sigríður", "sigga@hi.is", Svid.FVS, Deild.FVS1, Namsleid.NFVS11));*/

        return listi;
    }

    @Override
    public void skrifa(ObservableList<Nemandi> listi, String s) {

        FileWriter fw = opnaSkra(s);

        // Ítra yfir listann og skrfa út í skrá
        try {
            for (Nemandi n : listi) {
                String lina = n.skrifa()+ EOL;
                fw.write(lina);
            }
            fw.close(); // hreinsar úr buffer og lokar skránni
        } catch (IOException e) {
            System.out.println(TOKST_EKKI_SKRIFT);
            System.exit(0);    // að öllu jöfnu myndum við skila gildi til notendaviðmóts sem segir að skrift hafi ekki tekist
        }
    }

    /**
     * Opnar skrá með nafni s og skilar filewriter
     *
     * @param s nafn á skrá
     * @return FileWriter
     */
    private FileWriter opnaSkra(String s) {
        FileWriter fw=null;
        try {
            File f = new File ("src\\vinnsla\\gogn\\"+s);
            if (f.createNewFile()) {
                System.out.println ("Skráin "+s+" búin til");
            } else
                System.out.println ("Skráin "+s+" er þegar til");
            fw = new FileWriter(f); // vitum að f er til
        } catch (IOException e) {
            System.out.println(SKRA_EKKI_TIL + s);   // til öryggis
            e.printStackTrace();
            System.exit(0); // að öllu jöfnu myndum við skila gildi til notendaviðmóts sem segir að skrift hafi ekki tekist
        }
        return fw;
    }
}
