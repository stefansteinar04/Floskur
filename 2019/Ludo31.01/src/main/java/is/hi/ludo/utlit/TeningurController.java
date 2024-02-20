package is.hi.ludo.utlit;

import is.hi.ludo.vinnsla.Teningur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class Controller klasi fyrir teningshnappinn
 *
 * @author Ebba Þóra Hvannberg ebba@hi.is
 */
public class TeningurController implements Initializable {

    @FXML
    private Button jTeningur;
    private LudoBordController ludoController;          // vísar í aðalcontroller

    private final Teningur teningur = new Teningur();    // teningur vinnslu hlutur
    private final Image[] ikonar = new Image[6];
    private final String[] MYNDIR = {"one", "two", "three", "four", "five", "six"};

    /**
     * Frumstillir TeningurController klasann og býr til fylki af myndum fyrir
     * teninginn
     *
     * @param url ónotað
     * @param rb ónotað
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i = 0; i < 6; i++) {
            ikonar[i] = new Image(getClass().getResourceAsStream("myndir/" + MYNDIR[i] + ".png"));
        }
        // Nota bind í staðinn fyrir að disabla handvirkt 
        jTeningur.disableProperty().bind(teningur.getPropGildi().isEqualTo(-1));
    }

    /**
     * Handler fyrir teninginn. Teningi kastað og viðeigandi mynd birt. Gildið á
     * teningi sent til lúdó controllers
     *
     * @param event ónotað
     */
    @FXML
    private void teningurHandler(ActionEvent event) {
        int i = teningur.kasta();
        ImageView imageView = new ImageView(ikonar[i - 1]);
        rettStaerd(imageView, jTeningur);
        jTeningur.setGraphic(imageView);
        // Óþarfi þegar notað er bind 
        //  afVirkja(true);
        ludoController.setFjReitaAfram(i);
    }

    /**
     * Setur rétta stærð á ImageView hlut miðað við stærð tenings
     *
     * @param imageView
     * @param teningur
     */
    private void rettStaerd(ImageView imageView, Button teningur) {
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(teningur.getWidth());
        imageView.setFitHeight(teningur.getHeight());
    }

    /**
     * Setur teningu í lúdó controller
     *
     * @param aThis
     */
    public void setLudoController(LudoBordController aThis) {
        ludoController = aThis;
    }

    /**
     * Afvirkjar eða virkjar teninginn
     *
     * @param b
     */
    public void afVirkja(boolean b) {
        // Óþarfi þegar notað er bind 
        //     jTeningur.setDisable(b);
        if (b) {
            teningur.setPropGildi(-1);
        }
    }

}
