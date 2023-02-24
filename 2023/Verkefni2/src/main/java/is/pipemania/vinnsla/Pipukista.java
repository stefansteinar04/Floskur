package is.pipemania.vinnsla;

import edu.princeton.cs.algs4.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Vinnsluklasi fyrir pípukistu. Í upphafi eru settar 4 pípur í kistuna. Þegar elsta pípan
 *  er tekin úr er sett inn ný af handahófi
 *
 *****************************************************************************/

public class Pipukista {

    private final ObservableList<Pipa> kista = FXCollections.observableArrayList();

    // Fylki af áttum til að nota í slembipípu
    private static final Pipa.Att[] att = {Pipa.Att.V, Pipa.Att.N, Pipa.Att.A, Pipa.Att.S};


    // Smiður sem setur fjoldi pípa í kistuna

    public Pipukista(int fjoldi) {
        for (int i = 0; i < fjoldi; i++) {
            kista.add(nyPipa());
        }
    }

    /**
     * Býr til nýja pípu af handahófli
     *
     * @return skilar nýju pípunni
     */
    private Pipa nyPipa() {
        int inn = StdRandom.uniform(4);
        int ut = inn;
        while (inn == ut) {
            ut = StdRandom.uniform(4);
        }
        return new Pipa(att[inn], att[ut]);
    }

    /**
     * Nær í næstu pipu úr pípukistu og fjarlægir hana jafnframt
     *
     * @return næstu pípu sem er sú elsta
     */
    public Pipa naestaPipa() {
        Pipa naesti = kista.get(0); // taka fremst
        kista.remove(0, 1);
        Pipa nyr = nyPipa();
        kista.add(nyr); // setja aftast
        return naesti;
    }

    // getter
    public ObservableList<Pipa> getKista() {
        return kista;
    }

}
