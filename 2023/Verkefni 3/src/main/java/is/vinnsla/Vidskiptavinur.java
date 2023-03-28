package is.vinnsla;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Vinnsluklasi fyrir viðskiptavin
 *
 *
 *****************************************************************************/
public class Vidskiptavinur {
    private final StringProperty nafn = new SimpleStringProperty();
    private final StringProperty heimilisfang = new SimpleStringProperty();

    public Vidskiptavinur (String nafn, String heimilisfang) {
        this.nafn.set(nafn);
        this.heimilisfang.set(heimilisfang);
    }


    // get og set aðferðir - öllum haldið inni þó svo að ekki allar séu notaðar


    public String getNafn() {
        return nafn.get();
    }

    public StringProperty nafnProperty() {
        return nafn;
    }

    public void setNafn(String nafn) {
        this.nafn.set(nafn);
    }

    public String getHeimilisfang() {
        return heimilisfang.get();
    }

    public StringProperty heimilisfangProperty() {
        return heimilisfang;
    }

    public void setHeimilisfang(String heimilisfang) {
        this.heimilisfang.set(heimilisfang);
    }
}
