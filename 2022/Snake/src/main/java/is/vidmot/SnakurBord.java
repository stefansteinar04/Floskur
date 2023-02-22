/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Sérhæfður klasi fyrir leiksvæði snákaleiksins. Sér um að elda mat,
 *  láta snákinn borða mat, dreifa eitursnákum, láta eitursnáka éta snákinn, senda snákinn áfram
 *  og breyta stefnu snáksins, hreinsa leiksvæðið
 *
 *****************************************************************************/
package is.vidmot;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

import java.util.Random;

public class SnakurBord extends Pane {


    // Fastar - klasabreytur
    public static final int FJOLDIATTA = 4;
    public static final String EITURSNAKUR_DRAP = "Eitursnákur drap þig";
    private static final int TIDNISTEFNUBREYTING = 100;
    private static final int HRINGUR = 360;
    private static final Random random = new Random();

   // tilviksbreytur
    private final ObservableList<Faeda> matur = FXCollections.observableArrayList();    // maturinn
    private final ObservableList<EiturSnakur> eiturSnakar = FXCollections.observableArrayList();//eitursnákar
    private Snakur snakur;    // snákurinn
    private SnakurController sc; // tenging í controllerinn til að geta sett í bið, áfram, uppfæra stig og ljúka leik

    public void nyrLeikur(int fjoldiEitur, int fjoldiMatur) {
        hreinsaBord();    // borð hreinsað í upphafi leiks
        snakur = nyrSnakur();    // nýr snákur
        eldaMat(fjoldiMatur); // Elda 3 fæður með radíus 7
        nyirEitursnakar(fjoldiEitur);
    }

    /**
     * Færir eitursnák áfram á leiksvæði
     */
    public void afram() {
        snakur.afram();
    }

    /**
     * Eldar mat ef maturinn er búinn
     *
     * @param supply hámark fjöldi fæða á borði í einu
     */
    public void eldaMat(int supply) {
        System.out.println("elda mat");
        if (matur.isEmpty()) {      // ef maturinn er búinn þá elda
            for (int i = 0; i < supply; i++) {
                Faeda f = new Faeda(this);
                matur.add(f);
                getChildren().add(f);
            }
        }
    }

    /**
     * Athugar hvort snákur borðar matinn og fjarlægir hann af borðinu ef svo er. Snákurinn vex og
     * stigin eru hækkuð
     */
    public void borda() {
        for (Faeda f : matur) {
            if (snakur.erEtur(f)) {
                snakur.vaxa();
                sc.getLeikur().vinningur();
                getChildren().remove(f);
                matur.remove(f);
                return;
            }
        }
    }

    public void setStefna(int upp) {
        snakur.setRotate(upp);
    }

    /**
     * Setur eitursnák á borðið ef það er tímabært
     */
    public void nyirEitursnakar(int fjoldi) {
        for (int i = 0; i < fjoldi; i++) {
            EiturSnakur e = new EiturSnakur();
            e.setjaABord(this);
            eiturSnakar.add(e);
        }
    }

    /**
     * Býr til nýjan snák. Ef snákur er á borðinu þá er hann fjarlægður
     *
     * @return skilar nýjum snák
     */
    public Snakur nyrSnakur() {
        if (snakur != null)
            getChildren().remove(snakur);
        snakur = new Snakur(this);
        snakur.setjaUppBid();                // til að sé hægt að setja í bið
        getChildren().add(snakur);
        return snakur;
    }

    /**
     * Breytir stefnu eitursnáka ef það er tímabært og athugar hvort snákur rekst á eitursnák
     * Ef eitursnákur rekst á snák þá er leik lokið
     */
    public void aframEitursnakar() {
        breytaStefnuEitursnaka();
        athugaAreksturEitursnaka();
    }

    /**
     * Hreinsar leikborðið, eitursnákana og matinn
     */
    public void hreinsaBord() {
        hreinsaEitursnaka();
        hreinsaMatinn();
    }

    /**
     * Nær í controllerinn
     *
     * @return controllerinn
     */
    public SnakurController getController() {
        return sc;
    }

    /**
     * Setur controller fyrir viðmótshlutinn
     *
     * @param sc controllerinn
     */
    public void setController(SnakurController sc) {
        this.sc = sc;
    }

    /**
     * Athugar hvort eitursnákar rekast á snákinn og ef svo er þá er leik lokið
     */
    private void athugaAreksturEitursnaka() {
        for (EiturSnakur d : eiturSnakar) {
            d.afram();
            if (snakur.erArekstur(d)) { // Lagfæring
                sc.leikLokid(EITURSNAKUR_DRAP);
                return;
            }
        }
    }

    /**
     * Breytur stefnu eitursnáka ef það er tímabært
     */
    private void breytaStefnuEitursnaka() {
        // er tímabært að breyta stefnu eitursnáka
        if (sc.getT().getCurrentTime().toSeconds() % TIDNISTEFNUBREYTING == 0) {
            for (EiturSnakur d : eiturSnakar) {
                d.setRotate(random.nextInt(FJOLDIATTA - 1) * (HRINGUR / (double) FJOLDIATTA) + HRINGUR / (double) FJOLDIATTA);
            }
        }
    }


    /**
     * Hreinsar matinn af leikborðinu
     */
    private void hreinsaMatinn() {
        for (Faeda f : matur)
            getChildren().remove(f);
        matur.clear();
    }

    /**
     * Hreinsar eitursnáka af leikborðinu
     */
    private void hreinsaEitursnaka() {
        for (EiturSnakur e : eiturSnakar) {
            getChildren().remove(e);
        }
        eiturSnakar.clear();
    }


}

