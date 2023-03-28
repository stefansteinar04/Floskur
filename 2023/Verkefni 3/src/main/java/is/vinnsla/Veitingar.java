package is.vinnsla;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Vinnsluklasi fyrir veitingar. Hefur heiti og verð.
 *
 *
 *****************************************************************************/
public class Veitingar {

    private final StringProperty heiti = new SimpleStringProperty();
    private final IntegerProperty verd = new SimpleIntegerProperty();

    public Veitingar(String heiti, int verd) {
        this.heiti.set(heiti);
        this.verd.set(verd);
    }

    // get og set aðferðir - öllum haldið inni þó svo að ekki allar séu notaðar
    public String getHeiti() {
        return heiti.get();
    }

    public StringProperty heitiProperty() {
        return heiti;
    }

    public void setHeiti(String heiti) {
        this.heiti.set(heiti);
    }

    public int getVerd() {
        return verd.get();
    }

    public IntegerProperty verdProperty() {
        return verd;
    }

    public void setVerd(int verd) {
        this.verd.set(verd);
    }

    public String toString () {
        return heiti.get() + " verð: "+verd.get();
    }
}
