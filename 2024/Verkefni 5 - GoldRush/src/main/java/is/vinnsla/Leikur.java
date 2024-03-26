package is.vinnsla;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Vinnsluhlutur fyrir leik. Heldur aðallega utan um stigin
 *
 *  Leikur geymir stigin og erfiðleikastig leiksins
 *
 *  Sér um að hækka stigin og framleiða meira gull
 *
 *****************************************************************************/
public class Leikur {

    private static final Random rand = new Random(); // slembigjafi
    private static final int[] timinn = {0, 30, 20, 10}; // tíminn tengdur við erfiðleikastig
    public static final int MEIRAGULL = 5; // líkur á meira gulli

    private final IntegerProperty stigin = new SimpleIntegerProperty(); // stigin
    private int erfidleikastig = 1; // erfiðleikastig

    private boolean iGangi; // segir til um hvort leikur er í gangi

    /**
     * Hækkar stigin um einn
     */
    public void haekkaStigin() {
        stigin.setValue(stigin.getValue() + 1);
    }

    /**
     * Setur nýjan leik. Stigin verða 0
     */
    public void nyrLeikur() {
        stigin.setValue(0);
    }

    /**
     * Skilar tímanum sem er tengdur við erfiðleikastig leiksins
     *
     * @return
     */
    public int getTiminn() {
        return timinn[erfidleikastig];
    }

    /**
     * Segir til um hvort eigi að búa til meira gull
     *
     * @return
     */
    public boolean erMeiraGull() {
        return rand.nextInt(100) < MEIRAGULL;
    }


    // set og get aðferðir
    public void setErfidleikastig(int eStig) {
        erfidleikastig = eStig;
    }

    public IntegerProperty stiginProperty() {
        return stigin;
    }

    public boolean isIGangi() {
        return iGangi;
    }

    public void setiGangi(boolean iGangi) {
        this.iGangi = iGangi;
    }

}
