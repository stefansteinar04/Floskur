package is.vidmot;
/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *  Viðmótsforritun 2024
 *
 *  Controller fyrir leikinn
 *  stýrir örvatkökkum
 *****************************************************************************/

import is.vinnsla.Klukka;
import is.vinnsla.Leikur;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.util.HashMap;

public class GoldController {
    // fasti
    private static final double INTERVAL = 150; // hve oft gull er framleitt

    // viðótshlutir
    @FXML
    public Leikbord fxLeikbord; // leikborðið
    public Label fxTimiEftir;   // tíminn eftir
    public Label fxStig;        // stigin
    @FXML
    private MenuController menuStyringController; // stýring fyrir menu

    // vinnsluhlutir
    public Klukka klukka = new Klukka(30);
    private static final HashMap<KeyCode, Stefna> map = new HashMap(); // hakkatafla fyrir stefnur
    Timeline leikjalykkjaTimalina;  // tímalína fyrir leikjalykkju
    private Timeline klukkuTimalina; // tímalína fyrir klukku
    Leikur leikur; // vinnslan


    /**
     * Frumstillir controller. Gerir nýjan leik, tengir hann við leikborð, tengir
     * við menucontroller. Setur upp bindingu við stigin og tímann sem er eftir.
     */
    public void initialize() {
        leikur = new Leikur();
        fxLeikbord.setLeikur(leikur);
        menuStyringController.setController(this);
        fxLeikbord.requestFocus();
        fxStig.textProperty().bind(leikur.stiginProperty().asString()); // binda stigin við viðmótið
        fxTimiEftir.textProperty().bind(klukka.timiProperty().asString());
    }

    /**
     * Setur upp atburðafilter fyrir örvatakka og atburðahandler
     */
    public void orvatakkar() {
        // setjum upp beina aðganginn frá örvatökkunum og í hornið
        map.put(KeyCode.UP, Stefna.UP);
        map.put(KeyCode.DOWN, Stefna.NIDUR);
        map.put(KeyCode.RIGHT, Stefna.HAEGRI);
        map.put(KeyCode.LEFT, Stefna.VINSTRI);
        // þarf ekki að vera bara á senuna - getur verið á einstaka hlut en sá verður að vera í focus
        fxLeikbord.addEventFilter(KeyEvent.ANY,
                this::stefna);
    }

    /**
     * Færir grafara áfram í stefnu örvatakkans ef leikur í gangi
     *
     * @param event lyklaborðsatburður - hér örvatakkar
     */
    private void stefna(KeyEvent event) {      // lambda fall - event er parameter
        // flettum upp horninu fyrir KeyCode í map
        if (leikur.isIGangi() && map.get(event.getCode()) != null) {
            fxLeikbord.setStefna(map.get(event.getCode()).getGradur());
            fxLeikbord.afram();
        }
    }

    /**
     * Ræsir klukkuna. Klukkan telur niður frá tíma samkvæmt erfiðleikastigi
     */
    public void raesaKlukku() {
        // stoppa klukkuna ef hún var til
        if (klukkuTimalina != null) {
            klukkuTimalina.stop();
        }
        klukka.setTimi(leikur.getTiminn());
        klukkuTimalina = setjaUppKlukkuTimalinu(klukka);
        klukkuTimalina.play();
    }

    /**
     * Setur upp Klukkutímalínu til að telja niður klukku
     */
    private Timeline setjaUppKlukkuTimalinu(Klukka k) {
        KeyFrame kf = new KeyFrame(Duration.seconds(1),
                e -> k.tic());
        Timeline t = new Timeline(kf); // búa til nýja klukku
        t.setCycleCount(k.getTimi());
        t.setOnFinished(this::timinnBuinn);
        return t;
    }

    /**
     * Fall sem kallað er á þegar tími leiks er búinn
     *
     * @param actionEvent atburðurinn sem verður til þegar tíminn er búinn
     */
    private void timinnBuinn(ActionEvent actionEvent) {
        leikur.setiGangi(false);
        Timeline t = (Timeline) actionEvent.getSource();
        t.stop();           // stoppar klukkuna
        leikjalykkjaTimalina.stop(); // stoppar gullframleiðsluna
    }

    /**
     * Hefja leik með því að setja upp leikjalykkju fyrir meira gull
     */
    public void hefjaLeik() {
        leikur.setiGangi(true); // leikur í gangi
        fxLeikbord.nyrLeikur(); // nýr leikur hafinn
        leikjalykkjaTimalina = setjaUppLeikjalykkjuTimalinu();
        leikjalykkjaTimalina.play();
    }

    /**
     * Setur upp leikjalykkjuTimalinu til að framleiða meira gull
     */
    private Timeline setjaUppLeikjalykkjuTimalinu() {
        KeyFrame k = new KeyFrame(Duration.millis(INTERVAL),
                e -> {
                    fxLeikbord.meiraGull(); // á að setja meira gull ?
                });
        Timeline t = new Timeline(k);    // búin til tímalína fyrir leikinn
        t.setCycleCount(Timeline.INDEFINITE);   // leikurinn leikur endalaust
        return t;
    }

    public void setErfidleikastig(int eStig) {
        leikur.setErfidleikastig(eStig);
    }
}