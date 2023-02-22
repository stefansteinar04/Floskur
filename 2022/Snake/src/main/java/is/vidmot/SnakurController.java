/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Controller fyrir snákaleikinn. Heldur utan um leikjalykkju og hefur samskipti
 *  við vinnsluna
 *
 *
 *****************************************************************************/
package is.vidmot;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.util.Duration;
import is.vinnsla.Leikur;

import java.util.HashMap;
import java.util.Optional;

public class SnakurController {
    // Fastar
    public static final int INTERVAL = 100;
    public static final String VILTU_HALDA_AFRAM = ": Viltu halda áfram?";
    private static final int MAXEITURSNAKAR = 3;    // Hvað geta eitursnákar flest orðið margir
    private static final int MAXFAEDA =3;   // Hámark fjöldi fæða

    private static final int UPP = 90;
    private static final int NIDUR = 270;
    private static final int VINSTRI = 180;
    private static final int HAEGRI = 360;

    // Býr til beinan aðgang frá KeyCode og í heiltölu. Hægt að nota til að fletta upp
    // heiltölu fyrir KeyCode
    private static final HashMap<KeyCode, Integer> map = new HashMap<KeyCode, Integer>();


    // viðmótshlutir
    @FXML
    private ListView<Integer> fxStigin; //stigataflan
    @FXML
    private SnakurBord snakeBord;    // leiksvæðið

    private Leikur leikur;  // vinnslan
    private Timeline t; // Tímalína fyrir Animation á leiknum

    /**
     * Upphafsstillir vinnsluna, setur stigin og lætur leiksvæðið vita hvaða controller hann á að hafa samband við
     */
    public void initialize() {
        leikur = new Leikur();      // búa til vinnsluna
        fxStigin.setItems(leikur.getStigin());  // setja upp listann
        snakeBord.setController(this);
        fxStigin.setFocusTraversable(false);    // ekki hægt að focus-a á listann
    }


    /**
     * Tengir örvatakka við fall sem á að keyra í controller
     **/
    public void orvatakkar() {
        map.put(KeyCode.UP, UPP);   // setjum upp beina aðganginn frá örvatökkunum og í hornið
        map.put(KeyCode.DOWN, NIDUR);
        map.put(KeyCode.RIGHT, HAEGRI);
        map.put(KeyCode.LEFT, VINSTRI);
        fxStigin.getScene().addEventFilter(KeyEvent.ANY,      //KeyEvents eru sendar á Scene
                event -> {      // lambda fall - event er parameter
                    try {
                        this.setStefna(map.get(event.getCode())); // flettum upp horninu fyrir KeyCode í map
                    } catch (NullPointerException e) {
                        event.consume();        // Ef rangur lykill er sleginn inn þá höldum við áfram
                    }
                });
    }

    /**
     * Setur upp Animation fyrir leikinn og setur upp leikjalykkjuna
     */
    public void hefjaLeik() {
        KeyFrame k = new KeyFrame(Duration.millis(INTERVAL),
                e -> {
                    snakeBord.borda();
                    snakeBord.afram();
                    snakeBord.aframEitursnakar();
                });
        t = new Timeline(k);
        t.setCycleCount(Timeline.INDEFINITE);   // leikurinn leikur endalaust
        snakeBord.nyrLeikur(3, MAXEITURSNAKAR);
        t.play();
    }

    // get- og set-terar
    public void setStefna(int s) {
        snakeBord.setStefna(s);
    }

    public Leikur getLeikur() {
        return leikur;
    }

    public Timeline getT() {
        return t;
    }

    /**
     * Leik er lokið eð skilaboðum til notanda sem segir ástæðuna.
     * Stig birt og leikmaður spurður hvort hann vilji leika annan leik
     *
     * @param skilabod ástæðan fyrir að leik lauk
     */
    public void leikLokid(String skilabod) {
        leikur.leikLokid();
        t.stop();
        Platform.runLater(() -> synaAlert(skilabod));
    }

    /**
     * Stillir upp nýjum leik og byrjar hann
     */
    public void nyrLeikur() {
        snakeBord.nyrLeikur(MAXEITURSNAKAR, MAXFAEDA );
        t.play();
    }

    /**
     * Spyr notanda hvort hann vilji leika annan leik. Hefur nýjan leik ef svo er
     *
     * @param s
     */
    private void synaAlert(String s) {
        Alert a = new AdvorunDialog("", SnakurApplication.SNAKE, s + VILTU_HALDA_AFRAM);
        Optional<ButtonType> u = a.showAndWait();
        if (u.isPresent() && !u.get().getButtonData().isCancelButton())
            nyrLeikur();
    }

    /**
     * Setur leik í bið ef hann var í gangi. Annars heldur leikurinn áfram
     * Leikur verður að vera annað hvort í pásu eða í keyrslu
     */
    public void bida() {
        assert (t.getStatus() != Animation.Status.STOPPED);
        if (t.getStatus() == Animation.Status.PAUSED)
            t.play();
        else            // er í play stöðu
            t.pause();
    }
}
