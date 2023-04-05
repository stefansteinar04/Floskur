/******************************************************************************
 *  @author Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Býr til einfaldan aðvörunardialog sem hægt er að nota fyrir ýmiss konar
 *  spurningar
 *
 *****************************************************************************/
package is.verkefni4.vidmot;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Útfærir aðvörunar dialog sem kallað er á þegar notandi er spurður hvort
 *  halda eigi áfram
 *****************************************************************************/

public class AdvorunDialog extends Alert {  // Erfir frá Alert
    private static final String I_LAGI = "Í lagi";
    public static final ButtonType BTYPE = new ButtonType(I_LAGI,
            ButtonBar.ButtonData.OK_DONE);
    private static final String HAETTA_VID = "Hætta við";
    public static final ButtonType HTYPE = new ButtonType(HAETTA_VID,
            ButtonBar.ButtonData.CANCEL_CLOSE); // ButtonType er merktur með CANCEL_CLOSE (er enum);

    /**
     * Smiður sem setur titil, haus og spurningu í Alert dialog
     *
     * @param titill   titillinn
     * @param haus     hausinn
     * @param spurning spurning sem borin er upp
     */
    public AdvorunDialog(String titill, String haus, String spurning) {
        super(AlertType.NONE, spurning, BTYPE, HTYPE);  // kallar á smið yfirklasans
        setTitle(titill);
        setHeaderText(haus);
    }
}
