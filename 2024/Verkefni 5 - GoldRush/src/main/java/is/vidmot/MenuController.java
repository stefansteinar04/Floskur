package is.vidmot;
/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Viðmótsforritun 2024
 *  Stýring fyrir valmynd
 *
 *  Getur hafið nýjan leik, sett erfiðleikastig og spurt um forritið
 *
 *****************************************************************************/
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioMenuItem;

import java.util.Optional;

public class MenuController {
    // fastar
    public static final String VILTU_HAETTA = "Viltu hætta? ";
    public static final String UMFORRIT = "Gullgrafari grefur gull og fær stig ";

    private GoldController goldController;  // tenging í aðalcontroller

    /**
     * Setur erfiðleikastig - létt, miðlungs erfitt
     * @param actionEvent
     */
    public void onErfidleikastig(ActionEvent actionEvent) {
        goldController.setErfidleikastig
                (Integer.parseInt(((RadioMenuItem) actionEvent.getSource()).getId()));
    }

    /**
     * Hefur nýjan leik
     * @param actionEvent
     */
    public void onNyrLeikur(ActionEvent actionEvent) {
        goldController.hefjaLeik();
        goldController.raesaKlukku();
    }

    /**
     * Hættir í forriti
     * @param actionEvent
     */
    public void onHaetta(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING, VILTU_HAETTA);
        Optional<ButtonType> optional = alert.showAndWait();
        if (optional.isPresent() && optional.get().equals(ButtonType.OK)) {
            System.exit(0);
        }
    }

    /**
     * Veitir upplýsingar um forritið
     * @param actionEvent
     */
    public void onUmForritid(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, UMFORRIT);
        alert.showAndWait();
    }

    // set aðferðir

    public void setController(GoldController goldController) {
        this.goldController = goldController;
    }


}
