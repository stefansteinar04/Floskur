package vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import vinnsla.Strengir;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Stýriklasi fyrir Strengir application. Leyfir notanda að
 *  Skrifa inn texta
 *  Vista texta
 *  Leita í texta
 *  Telja orð í texta
 *
 *
 *****************************************************************************/

public class StrengirController {

    // fastar
    public static final String EKKI_FUNDID = "ekki fundið";
    public static final String ENGINN_TEXTI = "enginn-texti";
    public static final String EKKERT_LEITARORD = "ekkert-leitarord";
    public static final String LEITARORD = "leitarord";
    public static final String TEXTI = "texti";
    // Notendaviðmóts tilviksbreytur
    @FXML
    private TextField fxLeitarord;   //Leitarorð
    @FXML
    private TextArea fxTexti;       //Texti sem notandi slær inn
    @FXML
    private Label fxFjoldiOrda;      // úttak fyrir fjölda orða
    @FXML
    private Label fxStadsetning;     // úttak fyrir staðsetningu leitarorðs


    // Vinnsluhlutur
    private final Strengir strengir = new Strengir();

    // aðferðir fyrir aðgerðir í notendaviðmóti

    /**
     * Leitar að orði og setur staðsetninguna í staðsetningarviðmótshlut
     * Ef orðið fannst ekki er skrifað ekki fundið í staðsetningarviðmótshlutinn
     * Ef leitarorðið er af lengd 0 verður leitarglugginn rauður
     * Ef textinn er null verður textglugginn rauður
     *
     * @param actionEvent ónotað
     */
    @FXML
    public void onLeit(ActionEvent actionEvent) {
        if (fxLeitarord.getText().isEmpty()) {
            fxLeitarord.getStyleClass().clear();
            fxLeitarord.getStyleClass().add(EKKERT_LEITARORD);
        }
        try {
            int n = strengir.leita(fxLeitarord.getText());
            if (n >= 0) {
                fxStadsetning.setText(n + "");
            } else {
                fxStadsetning.setText(EKKI_FUNDID);
            }
        } catch (NullPointerException e) {
            fxTexti.getStyleClass().add(ENGINN_TEXTI);
        }
    }

    /**
     * Telur fjölda orða í textanum og setur í fjölda orða viðmótshlutinn
     * Ef textinn er null verður textglugginn rauður
     *
     * @param actionEvent ónotað
     */
    @FXML
    public void onTeljaOrd(ActionEvent actionEvent) {
        try {
            fxFjoldiOrda.setText(strengir.fjoldiOrda() + "");
        } catch (NullPointerException e) {
            fxTexti.getStyleClass().add(ENGINN_TEXTI);
        }
    }

    /**
     * Vistar textann í vinnsluhlut
     * ramminn verður grár
     *
     * @param actionEvent ónotað
     */
    @FXML
    public void onVistaTexta(ActionEvent actionEvent) {
        fxTexti.getStyleClass().add(TEXTI);
        strengir.setTexti(fxTexti.getText());
    }

    /**
     * Ef notandi skrifar af lyklaborði í texta hlut (textinn eða leitarorð) þá verður ramminn grár
     *
     * @param keyEvent viðmótshlutur sem er TextField
     */
    @FXML
    public void onSkrifa(KeyEvent keyEvent) {
        // ef það væru fleiri en einn TextField en einnig má setja beint á fxLeitarord
        ((TextField) keyEvent.getSource()).getStyleClass().add(LEITARORD);
    }
}