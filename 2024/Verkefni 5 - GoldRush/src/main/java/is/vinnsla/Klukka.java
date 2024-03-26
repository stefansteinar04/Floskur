package is.vinnsla;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing: Vinnsluklasi fyrir klukku. Geymir tímann, lætur tímann líða
 *
 *****************************************************************************/

import javafx.beans.property.SimpleIntegerProperty;

public class Klukka {
    private SimpleIntegerProperty timi = new SimpleIntegerProperty();

    /**
     * Smiður. Tekur inn upphafstíma
     *
     * @param upphafsTimi
     */
    public Klukka(int upphafsTimi) {
        setTimi(upphafsTimi);
    }

    /**
     * Tíminn líður
     */
    public void tic() {
        setTimi(getTimi() - 1);
    }

    // Set og get aðferðir
    public void setTimi(int timi) {
        this.timi.set(timi);
    }

    public int getTimi() {
        return timi.get();
    }

    public SimpleIntegerProperty timiProperty() {
        return timi;
    }


}
