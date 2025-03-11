/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Leitar dialog - er eins konar textadialog
 *
 *
 *****************************************************************************/
package vidmot;

import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

import static vidmot.Main.CSS_SKRA;


public class LeitDialog extends TextInputDialog {

    /**
     * Smiður fyrir klasann. Setur titil, haus og spurningu í dialoginn
     *
     * @param titill   titill á glugga
     * @param haus     haus
     * @param spurning hvað er beðið um í textasviði
     */
    public LeitDialog(String titill, String haus, String spurning) {
        super();                // kallar á smið yfirklasans (þ.e. TextInputDialog)
        // Stylesheet bætt við - meira til sýnis
        getDialogPane().getStylesheets().add(getClass().getResource(CSS_SKRA).toExternalForm());
        setTitle(titill);
        setGraphic(null);       // engin mynd
        setHeaderText(haus);    // hausinn
        setContentText(spurning);   //spurningin
        ObservableList<ButtonType> bTypes = getDialogPane().getButtonTypes();   // Náum í alla hnappana
        bTypes.remove(0, 2);     // fjarlægjum hnappana
        bTypes.add(NyrNemandiController.BTYPE);    // Bætum við hnöppum sem er á íslensku
        bTypes.add(NyrNemandiController.HTYPE);
    }
}
