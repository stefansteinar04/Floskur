/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Býr til einfaldan aðvörunardialog sem hægt er að nota fyrir ýmiss konar
 *  spurningar
 *
 *****************************************************************************/
package vidmot;

import javafx.scene.control.Alert;

import static vidmot.Main.CSS_SKRA;

public class AdvorunDialog extends Alert {  // Erfir frá Alert
    /**
     * Smiður sem setur titil, haus og spurningu í Alert dialog
     *
     * @param titill   titillinn
     * @param haus     hausinn
     * @param spurning spurning sem borin er upp
     */
    public AdvorunDialog(String titill, String haus, String spurning) {
        super(Alert.AlertType.NONE, spurning, NyrNemandiController.BTYPE, NyrNemandiController.HTYPE);  // kallar á smið yfirklasans
        getDialogPane().getStylesheets().add(getClass().getResource(CSS_SKRA).toExternalForm());
        setTitle(titill);
        setHeaderText(haus);
    }
}
