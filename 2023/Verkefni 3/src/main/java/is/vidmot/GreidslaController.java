package is.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Greiðslustýring - aðeins hægt að fara til baka í pöntun
 *
 *
 *****************************************************************************/
public class GreidslaController  {

    public Label fxVidskiptavinur; // viðmótshlutur fyrir nafn viðskiptavinar
    public Label fxHeimilisfang;    // heimilsfang viðskiptavinar
    @FXML
    private Label fxVerd;   // verðið sem á að greiða

    /**
     * upphafsstilla reglur sem tengja senurnar
     */
    public void initialize() {
        // náum í controllerinn fyrir  pöntunarview svo hægt sé að setja reglur á milli greiðslu og pöntunar
        PontunController pontunController = (PontunController) ViewSwitcher.lookup(View.PONTUN);
        fxVerd.textProperty().bind(pontunController.getKarfa().heildarVerdProperty().asString());
        fxVidskiptavinur.textProperty().bind(pontunController.getVidskiptavinur().nafnProperty());
        fxHeimilisfang.textProperty().bind(pontunController.getVidskiptavinur().heimilisfangProperty());
    }

    /**
     * Handler fyrir að fara aftur í pöntunarsenu
     *
     * @param actionEvent
     */
    public void pontunHandler(ActionEvent actionEvent) {
        PontunController pontunController = (PontunController) ViewSwitcher.lookup(View.PONTUN);
        pontunController.taemaKorfu();
        ViewSwitcher.switchTo(View.PONTUN);
    }
}
