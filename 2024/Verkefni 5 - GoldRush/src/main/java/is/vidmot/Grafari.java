package is.vidmot;
/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *  Viðmótsforritun 2024
 *
 *  Viðmótsklasi fyrir gullgrafara. Er Rectangle
 *  Grafari hefur stefnu og veit hvað hann fer hratt
 *
 *****************************************************************************/

import javafx.scene.shape.Rectangle;

public class Grafari extends Rectangle {
    // fastar
    private static final double HRATT = 10;
    private final double hradi = HRATT; // hve hratt fer grafarinn
    private int stefna; // í hvaða stefnu fer grafarinn

    /**
     * Smiður les inn notendaviðmót og setur stefnuna niður
     */
    public Grafari() {
        FXML_Lestur.lesa(this, "grafari-view.fxml");
        setLayoutX(getWidth() / 2);
        setStefna(Stefna.NIDUR.getGradur());
    }


    /**
     * Boltinn færist áfram. Ef komið er að vinstri jaðrinum kemstu ekki lengra. Ef komið er að hægri jaðrinum
     * ferðu hringinn að vinstri jaðrinum. Sama með efri og neðri jaðarinn
     */
    public void afram() {
        Leikbord parent = (Leikbord) this.getParent();
        setX((int) (getX() < 0 || getX() > parent.getWidth() ? 0 : getX() + Math.cos(Math.toRadians(getStefna())) * hradi) % (parent.getWidth() - getWidth()));
        setY((int) (getY() < getHeight() || getY() > parent.getHeight() ? getHeight() : getY() - Math.sin(Math.toRadians(getStefna())) * hradi) % (parent.getHeight() - getHeight()));
    }

    // get og set aðferðir

    private double getStefna() {
        return stefna;
    }

    public void setStefna(int gradur) {
        this.stefna = gradur;
    }
}
