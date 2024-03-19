package is.vidmot;

import is.vinnsla.Kubbur;
import is.vinnsla.Stykki;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *  Viðmótsforritun 2024
 *
 *  Lýsing  : Stýriklasi fyrir Kubbur application. Leyfir notanda að
 *  velja tölu og giska á hvaða reit talan er. Gefin eru stig og látið vita hvort leik er lokið
 *
 *
 *****************************************************************************/

public class KubburController {

    // fastar
    public static final String LEIK_LOKID = "Leik lokið";
    public static final String LEIKUR_I_GANGI = "Leikur í gangi";
    // viðmótshlutir
    @FXML
    private Label fxLeikurstada; // staða leiks
    @FXML
    private Label fxStig;    // stigin
    @FXML
    private GridPane fxBord; // borðið
    @FXML
    private TextField fxTala; // notandi velur tölu

    // vinnsluhlutur
    private final Kubbur kubbur = new Kubbur(3, 3);

    /**
     * Frumstillir stýrieininguna eftir að búið er að lesa inn .fxml skrána
     */
    public void initialize() {
        // Binda stigin í vinnslunni við viðmótið
        fxStig.textProperty().bind(kubbur.stigProperty());
        // Binda stöðu leiks í vinnslunni við viðmótið

        fxLeikurstada.textProperty().bind(Bindings.createStringBinding(() -> {
            if (kubbur.getFjoldiReita() == 9) {
                return LEIK_LOKID;
            } else
                return LEIKUR_I_GANGI;
        }, kubbur.fjoldiReitaProperty()));
    }

    /**
     * Setur stykki á borðið. Athugar hvort stykkið er á réttum stað.
     * uppfærir stigin
     *
     * @param actionEvent atburður
     */
    public void onSetjaStykki(ActionEvent actionEvent) {
        // hnappurinn sem notandi valdi
        Button reitur = (Button) actionEvent.getSource();

        // hvaða reitur var valinn á borðinu
        int i = GridPane.getRowIndex(reitur);
        int j = GridPane.getColumnIndex(reitur);

        // talan sem notandi hefur valið sér
        int tala = Integer.parseInt(fxTala.getText());

        // náum í stykkið fyrir tala af raðað borðinu með uppfletttingu úr vinnslunni
        Stykki stykki = kubbur.getRadadStykki(tala);

        // myndin á borðinu uppfærð með stykkinu á random borðinu
        setjaMynd(kubbur.getBordStykki(i, j), reitur);

        // ef random borðið er með réttu stykki þá afvirkja
        if (kubbur.setjaStykki(i, j, stykki)) {
            reitur.setDisable(true);    // reitur afvirkjaður
            fxBord.setDisable(true);    // borðið afvirkjað
        }
    }

    /**
     * Notandi velur nýja tölu
     *
     * @param keyEvent atburðurinn
     */
    public void onNyTala(KeyEvent keyEvent) {
        // borðið er virkjað
        fxBord.setDisable(false);

        // eyða myndum af öllum hnöppum sem eru virk
        for (int i = 0; i < fxBord.getChildren().size() - 1; i++) {
            Button b = (Button) fxBord.getChildren().get(i);
            if (!b.isDisable()) {
                eydaMynd(b);
            }
        }
    }

    /**
     * Eyðir mynd af reit á borði
     *
     * @param reitur hnappurinn sem á að verða með auðri mynd
     */
    private void eydaMynd(Button reitur) {
        if (!reitur.getStyleClass().getLast().equals("button")) {
            reitur.getStyleClass().removeLast();
        }
    }

    /**
     * Skipt er um mynd á hnappi b með mynd á Stykki.
     *
     * @param b      hnappur
     * @param stykki Stykki
     */
    private void setjaMynd(Stykki stykki, Button b) {
        String s = stykki.getMynd();
        b.getStyleClass().add(s);
    }
}