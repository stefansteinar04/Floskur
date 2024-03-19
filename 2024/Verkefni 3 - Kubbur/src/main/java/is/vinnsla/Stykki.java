package is.vinnsla;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Vinnsluklasi fyrir Stykki
 *   Hefur nafn á mynd og tölu
 *****************************************************************************/

public class Stykki {

    // static breyta sem heldur utan um raðtölu stykkkja
    private static int radTala = 1; // static raðtala stykkja sem uppfærist þegar nýtt stykki er búið til

    // tilvikskbreytur
    private final String mynd; // nafn á styleClass fyrir stykkið
    private final int tala; // tala á stykki

    /**
     * Smiður sem býr til stykki sem er í staðsetningu (i,j) á borði
     *
     * @param mynd mynd stykkisins
     */
    public Stykki(String mynd) {
        this.mynd = mynd;
        tala = radTala++;
    }

    // get aðferðir
    public String getMynd() {
        return mynd;
    }

    public int getTala() {
        return tala;
    }
}
