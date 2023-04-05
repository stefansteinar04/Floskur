package is.verkefni4.vinnsla;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Vinnsluhlutur fyrir leik. Heldur aðallega utan um stigin
 *****************************************************************************/
public class Leikur {

    private final IntegerProperty stigin = new SimpleIntegerProperty();

    public IntegerProperty stiginProperty() {
        return stigin;
    }

    public int getStigin() {
        return stigin.get();
    }


    public void haekkaStigin() {
        stigin.setValue(stigin.getValue()+1);
    }

    public void nyrLeikur() {
        stigin.setValue(0);
    }
}
