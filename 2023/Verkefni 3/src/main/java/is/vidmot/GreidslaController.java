package is.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : 
 *
 *
 *****************************************************************************/
public class GreidslaController  {

    public Label fxVidskiptavinur;
    public Label fxHeimilisfang;
    @FXML
    private Label fxVerd;

    /**
     * upphafsstilla reglur sem tengja senurnar
     */
    public void initialize() {
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
