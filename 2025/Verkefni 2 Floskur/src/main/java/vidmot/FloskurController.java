
/*****************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *  Lýsing  : Stýriklasi fyrir Flöskur. Notandi getur sett inn fjöldi flaska,
 *  dósa, hreinsað og fengið
 *  greitt fyrir flöskur og dósir
 *****************************************************************************/
package vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import vinnsla.Floskur;

import java.util.List;

public class FloskurController {
    // fastar
    private static final String RANGT = "rangt-inntak";

    // viðmótshlutir
    @FXML
    private TextField fxFloskur; // fjöldi flaska
    @FXML
    private TextField fxDosir; // fjöldi dósa
    @FXML
    public Label fxISKFloskur;  // virði flaska
    @FXML
    public Label fxISKDosir;    // virði dósa
    @FXML
    public Label fxSamtalsFjoldi; // samtals fjöldi dósa og flaska
    @FXML
    public Label fxSamtalsVirdi;   // sammtals virði dósa og flaska
    @FXML
    public Label fxHeildFjoldi;    // heildarfjöldi dósa og flaska sem hefur verið safnað
    @FXML
    public Label fxHeildVirdi;     // heildarvirði dósa og flaska sem hefur verið safnað

    // vinnsla
    private final Floskur floskur = new Floskur();  // Model klasi

    /**
     * Handler fyrir að setja inn fjöldi flaska. Ef fjöldi er neikvæður eða ólöglegur birtist rauður rammi
     *
     * @param ignoredEvent ónotað
     */
    @FXML
    protected void onFloskur(ActionEvent ignoredEvent) {

        try {
            int fjoldi = Integer.parseInt(fxFloskur.getText());
            if (fjoldi > 0) {
                floskur.setFjoldiFloskur(fjoldi);
                fxISKFloskur.setText(floskur.getISKFloskur() + "");
                setSamtals();
            }
            else {
                neikvaedurFjoldi(fxFloskur);
            }
        }
        catch (NumberFormatException e) {
            fxFloskur.getStyleClass().add(RANGT);
        }
    }

    /**
     * Handler fyrir að setja inn fjöldi dósa. Ef fjöldi er neikvæður eða ólöglegur birtist rauður rammi
     *
     * @param actionEvent action atburður
     */
    @FXML
    protected void onDosir(ActionEvent actionEvent) {

        try {
            int fjoldi = Integer.parseInt(fxDosir.getText());
            if (fjoldi > 0) {
                floskur.setFjoldiDosir(fjoldi);
                fxISKDosir.setText(floskur.getISKDosir() + "");
                setSamtals();
            }
            else {
                neikvaedurFjoldi(fxDosir);
            }
        } catch (NumberFormatException e) {
            fxDosir.getStyleClass().add(RANGT);
        }
    }

    /**
     * Handler fyrir að hreinsa tölur úr dósa og flöskusviðum
     *
     * @param actionEvent ónotað
     */
    @FXML
    protected void onHreinsa(ActionEvent actionEvent) {
        floskur.hreinsa();
        fxDosir.setText(floskur.getFjoldiDosir() + "");
        eydaRanga(fxDosir.getStyleClass());
        fxFloskur.setText(floskur.getFjoldiFloskur() + "");
        eydaRanga(fxFloskur.getStyleClass());
        fxISKDosir.setText(floskur.getISKDosir() + "");
        fxISKFloskur.setText(floskur.getISKFloskur() + "");
        setSamtals();
    }

    /**
     * Handler fyrir að greiða fyrir flöskur og dósir
     * bætir við heildarfjölda og heildarvirði
     *
     * @param actionEvent ónotað
     */
    @FXML
    protected void onGreida(ActionEvent actionEvent) {
        floskur.greida();
        fxHeildFjoldi.setText(floskur.getHeildFjoldi() + "");
        fxHeildVirdi.setText(floskur.getHeildVirdi() + "");
        onHreinsa(actionEvent);
    }

    /**
     * Eyðir stílnum með röngu inntaki og setur stílinn með réttu inntaki
     *
     * @param keyEvent atburðurinn sem berst
     */
    public void onStafur(KeyEvent keyEvent) {
        TextField textField = ((TextField) keyEvent.getSource());
        List<String> styleClasses = textField.getStyleClass();
        keyEydaRanga(keyEvent, styleClasses);
    }

// private hjálparaðferðir
    /**
     * Bregðast við keyEvent og fjarlægja rangt stílinn
     *
     * @param keyEvent     lyklaatburður
     * @param styleClasses listi af stílum
     */
    private static void keyEydaRanga(KeyEvent keyEvent, List<String> styleClasses) {
        if (styleClasses == null || styleClasses.isEmpty()) {
            return;
        }
        if (keyEvent.getCode() != KeyCode.ENTER ) {
            eydaRanga(styleClasses);
        }
    }

    private static void eydaRanga(List<String> styleClasses) {
        styleClasses.remove(RANGT);
    }


    /**
     * Setur samtals viðmótshlutina
     */
    private void setSamtals() {
        fxSamtalsFjoldi.setText(floskur.getSamtalsFjoldi() + "");
        fxSamtalsVirdi.setText(floskur.getSamtalsVirdi() + "");
    }

    /**
     * Ef fjöldi er neikvæður þá er stíllinn setur RANGT
     *
     * @param f textasvið fyrir annað hvort dósir eða flöskur
     * @return skilar true ef fjöldi er jákvæður annars false
     */
    private void neikvaedurFjoldi(TextField f) {
        List<String> styleClasses = f.getStyleClass();
        if (Integer.parseInt(f.getText()) < 0) {
            styleClasses.add(RANGT);
        }
    }
}
