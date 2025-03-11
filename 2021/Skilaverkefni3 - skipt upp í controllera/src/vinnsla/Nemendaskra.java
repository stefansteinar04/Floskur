package vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  :
 *
 *
 *****************************************************************************/
public class Nemendaskra implements Skra<Nemandi> {
    @Override
    public ObservableList<Nemandi> lesa(String s) {

        ObservableList<Nemandi> listi = FXCollections.observableArrayList();
        listi.add(new Nemandi("Sigurður", "siggi@hi.is", Svid.VON, Deild.IVT, Namsleid.HBV));
        listi.add(new Nemandi("Sigríður", "sigga@hi.is", Svid.VON, Deild.UB, Namsleid.REI));
        return listi;
    }

    @Override
    public void skrifa(ObservableList<Nemandi> listi, String s) {
        System.out.println("skrifa ");
    }
}
