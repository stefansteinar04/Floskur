package is.vidmot;

import is.vinnsla.Karfa;
import is.vinnsla.Veitingar;
import is.vinnsla.Vidskiptavinur;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Controller fyrir pöntunarsenuna
 *
 *
 *****************************************************************************/
public class PontunController {

    @FXML
    private Button fxInnskraning; // innskráningarhnappur sem breytist þegar viðskiptavinur er loggaður inn
    @FXML
    private ListView<Veitingar> fxKarfa; // karfa með veitingum

    @FXML
    private Label fxSamtals;    // samtals verð á veitingum í körfu
    @FXML
    private MatsedillView fxMatsedill;  // matseðillinn

    // Vinnsluhlutir
    private final Karfa karfa = new Karfa();
    private Vidskiptavinur vidskiptavinur = null;

    public void initialize() {
        fxKarfa.setItems(karfa.getVeitingar()); // tengja viðmót og vinnslu
        fxSamtals.textProperty().bind(karfa.heildarVerdProperty().asString()); // bindum saman körfu
    }


    /**
     * Greiða fyrir veitingar. Skiptir yfir í greiðslusenu
     *
     * @param actionEvent
     */
    public void fxGreidaHandler(ActionEvent actionEvent) {
        if (vidskiptavinur !=null)
            ViewSwitcher.switchTo(View.GREIDSLA);
    }


    /**
     * Setja valdar veitingar í körfu  ef eitthvað er valið
     *
     * @param actionEvent
     */
    public void fxSetjaKorfuHandler(ActionEvent actionEvent) {
        if (fxMatsedill.getSelectionModel().getSelectedItem() != null)
            fxKarfa.getItems().add(fxMatsedill.getSelectionModel().getSelectedItem());
    }

    /**
     * Taka valdar veitingar úr körfu  ef eitthvað er valið
     *
     * @param actionEvent
     */
    public void fxTakaUrKorfuHandler(ActionEvent actionEvent) {
        if (fxKarfa.getSelectionModel().getSelectedItems() != null)
            fxKarfa.getItems().remove(fxKarfa.getSelectionModel().getSelectedItem());
    }

    /**
     * Ef enginn viðskiptavinur er til þá er skráður nýr viðskiptavinur. Annars er hægt
     * að skrá sig inn
     * @param actionEvent
     */
    public void fxInnskraningHandler(ActionEvent actionEvent) {
        if (vidskiptavinur == null) {
            nyrVidskiptavinur();
        } else {
            skraInn();
        }
    }

    /**
     * Innskrá viðskiptavin
     */
    private  void skraInn() {
        LoginDialog login = new LoginDialog();
        TextInputDialog t = login.upphafsstilla();
        Optional<String> o = t.showAndWait();
        o.ifPresent((value)-> {fxInnskraning.setText("innskráð(ur) "+vidskiptavinur.getNafn());});
    }

    /**
     * Skrá nýjan viðskiptavin
     *
     */
    private void nyrVidskiptavinur() {
        vidskiptavinur = new Vidskiptavinur("", "");
        Optional<Vidskiptavinur> v = new VidskiptavinurDialog(vidskiptavinur).showAndWait();
        v.ifPresent(value -> fxInnskraning.setText(value.getNafn()));
    }

    public Karfa getKarfa() {
        return karfa;
    }

    public Vidskiptavinur getVidskiptavinur() {
        return vidskiptavinur;
    }

    public void taemaKorfu() {
        fxKarfa.getItems().removeAll(fxKarfa.getItems());
    }
}