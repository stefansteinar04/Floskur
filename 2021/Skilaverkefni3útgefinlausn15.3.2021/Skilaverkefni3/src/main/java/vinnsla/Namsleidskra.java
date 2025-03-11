/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Gagnaklasi sem getur lesið inn námsleiðir deilda. útfærir Skra interface
 *
 *
 *****************************************************************************/
package vinnsla;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Scanner;


public class Namsleidskra implements Skra<Namsleid> {
    /**
     * Les inn námsleiðir dielda
     *
     * @param s heiti á skrá
     * @return listi af námsleiðum deilda
     */
    @Override
    public ObservableList<Namsleid> lesa(String s) {
        ObservableList<Namsleid> namsleidirDeildar = FXCollections.observableArrayList();

        // setjum try catch utan um innlesturinn til að varna krassi ef skrá er ekki til
        try {
            Scanner inntak = new Scanner(getClass().getResourceAsStream(s));
            while (inntak.hasNext()) {
                // lesa inn streng og setja í Namsleid breytu
                Namsleid nyNamsleid = Namsleid.valueOf(inntak.next());  // getur valdið (throw) Exceptions ef rangt gildi er lesið
                namsleidirDeildar.add(nyNamsleid);
            }
        }
        // grípum IllegalArgumentException villu sem getur komið út úr valueOf aðferðinni lína 52 -
        catch (IllegalArgumentException e) {
            System.out.println("rangt gildi " + e.toString() + " á enum í " + s);
            // Hættum - gætum líka gert eitthvað annað
            Platform.exit();
            System.exit(0);
        }
        // hægt að grípa fleiri exceptions - gæti komið null pointer við að búa til nýjan Scanner lína 49, þ.e null út
        // úr getResourceAsStream...
        catch (NullPointerException e) {
            System.out.println("villa");
            Platform.exit();
            System.exit(0);
        }
        return namsleidirDeildar;
    }


    /**
     * Ónotað - Ætlum ekki að skrifa námsleiðir út í skrá
     *
     * @param listi listi af hlutum
     * @param s     heiti á skrá
     */
    @Override
    public void skrifa(ObservableList<Namsleid> listi, String s) {
    }
}
