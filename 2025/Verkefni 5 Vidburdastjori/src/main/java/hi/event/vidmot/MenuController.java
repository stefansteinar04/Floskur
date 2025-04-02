package hi.event.vidmot;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioMenuItem;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Controller fyrir menu-inn. Eyða, Hætta, Vista, Loka, Opna, Nýr og Um
 *
 *
 *****************************************************************************/
public class MenuController {

    /**
     * Eyðir viðburði
     * @param event ónotað
     */
    @FXML
    void onEyda(ActionEvent event) {
        getEventManagerController().eyda();
    }

    /**
     * Hættir í forritinu
     * @param event ónotað
     */
    @FXML
    void onHaetta(ActionEvent event) {
        Platform.exit();
    }

    /**
     * Opnar viðburð sem hefur verið vistaður
     * @param event ónotað
     */
    @FXML
    void onOpna(ActionEvent event) {
        EventManagerController controller = getEventManagerController();
        controller.opna();
    }

    /**
     * Gefur upplýsingar um forritið
     * @param event ónotað
     */
    @FXML
    void onUm(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.INFORMATION,
                "Höfundur: Ebba Þóra Hvannberg, útgáfa 1.0",
                ButtonType.OK);
        a.setTitle("Viðburðarstjóri");
        a.setHeaderText("Viðburðarstjóri");
        a.show();
    }

    /**
     * Vistar viðburðinn
     * @param event ónotað
     */
    @FXML
    void onVista(ActionEvent event) {
        getEventManagerController().vista();
    }

    /**
     * Býr til nýjan viðburð
     * @param actionEvent
     */
    public void onNyr(ActionEvent actionEvent) {
        EventManagerController controller = getEventManagerController();
        controller.nyr();
    }

    /**
     * Lokar viðburðinum
     * @param actionEvent
     */
    public void onLoka(ActionEvent actionEvent) {
        EventManagerController controller = getEventManagerController();
        controller.loka();
    }

    // prívat aðgerðir

    /**
     * skilar controllernum fyrir Event Managerinn
     * @return event manager controller
     */
    private EventManagerController getEventManagerController() {
        return EventManagerApplication.getController();
    }

    // Ekki hluti af verkefninu
    @FXML
    void onLitur(ActionEvent event) {
        getEventManagerController().breytaLit((RadioMenuItem)event.getSource());
    }
}
