/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Controller fyrir að skoða nemanda. Birtir glugga með upplýsingum um
 *            nemanda. Ekki hægt að breyta upplýsingum. Glugginn er non-modal.
 *
 *****************************************************************************/
package vidmot;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import vinnsla.Nemandi;

import java.net.URL;
import java.util.ResourceBundle;

import static vidmot.Main.CSS_SKRA;

public class SkodaNemandiController implements Initializable {
    @FXML
    private Label fxSvid;       // Svið
    @FXML
    private Label fxDeild;      // Deild
    @FXML
    private Label fxNamsleid;   // Námsleið
    @FXML
    private Label fxPostur;     // Tölvupóstur
    @FXML
    private Label fxNemandi;    // Nafn nemanda
    @FXML
    private Pane fxPane;        // Pane fyrir gluggann

    // Ein sena fyrir að skoða nemanda
    private Scene sena;

    /**
     * Frumstillir controller. Býr til senu með Pane fyrir væntanlegan glugga
     *
     * @param url            ónotað
     * @param resourceBundle ónotað
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sena = new Scene(fxPane);
        // Stylesheet bætt við - meira til sýnis
        sena.getStylesheets().add(getClass().getResource(CSS_SKRA).toExternalForm());
    }

    /**
     * Birtir glugga með upplýsingum um nemanda
     *
     * @param nem nemandinn með upplýsingum
     */
    public void birtaGlugga(Nemandi nem) {
        fxNemandi.setText(nem.getNafn());
        fxPostur.setText(nem.getTolvupostfang());
        fxSvid.setText(nem.getSvid().name());
        fxDeild.setText(nem.getDeild().name());
        fxNamsleid.setText(nem.getNamsleid().name());
        Stage gluggi = new Stage();

        gluggi.setScene(sena);
        gluggi.show();
    }
}
