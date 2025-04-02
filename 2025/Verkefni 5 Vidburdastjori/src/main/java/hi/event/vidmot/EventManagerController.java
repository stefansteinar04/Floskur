package hi.event.vidmot;

import hi.event.vinnsla.EventModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;

import java.util.Optional;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Controller fyrir aðalviðmótið. Heldur bara utan um Staflann
 *  fyrir Viðburðina og lista af viðburðumm
 *
 *****************************************************************************/

public class EventManagerController {

    public static final String OPNA = "Opna";
    public static final String HEITIÐ_Á_VIÐBURÐINUM = "Settu inn heitið á viðburðinum";
    @FXML
    private StackPane fxEventViews;

    private EventView currentView;  // núverandi viðburður í viðmótinu
    private ObservableList<EventModel> list =   // listi af viðburðum
            FXCollections.observableArrayList();

    /**
     * Frumstilling eftir að hlutur hefur verið búinn til. Setur fyrsta viðbuðinn fremstan
     */
    public void initialize() {
        currentView = (EventView) fxEventViews.getChildren().getFirst();
    }

    /**
     * Vistar viðburðinn í innri geymslu
     */
    public void vista() {
        if (currentView != null) {
            list.add(currentView.getEventModel());
        }
    }

    /**
     * Eyðir viðburðinum úr viðmótinu og úr innri geymslunni.
     * Birtir þann viðburð sem síðast var bætt við
     */
    public void eyda() {
        fxEventViews.getChildren().remove(currentView); // eyða í útlitinu
        list.remove(currentView.getEventModel()); // eyða viðburðinum í vinnslunni
        // setja þann næst aftasta sem núverandi viðburð
        if (!fxEventViews.getChildren().isEmpty()) {
            currentView = ((EventView) fxEventViews
                    .getChildren().getLast()); // aftasti verður fremstur í glugganum
            switchView(currentView);
        }
    }

    /**
     * Opnar léttan texta dialog og biður notanda um heiti á viðburði og birtir hann
     */
    public void opna() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(OPNA);
        dialog.setContentText(HEITIÐ_Á_VIÐBURÐINUM);
        Optional<String> option = dialog.showAndWait();
        if (option.isPresent()) {
            // Finna viðburðinn
            Optional<EventModel> eventModel = finnaVidburd(option.get());
            if (eventModel.isPresent()) {
                // Finna GUI viðburðinn
                if (finnaView(eventModel.get()).isPresent()) {
                    currentView = finnaView(eventModel.get()).get();
                }
                else {
                    // Búa til GUI viðburðinn ef hann var ekki til
                    currentView = new EventView(eventModel.get());
                    fxEventViews.getChildren().add(currentView);
                }
                // Skipta yfir í viðburðinn sem fannst
                switchView(currentView);
            }
        }
    }



    /**
     * Býr til nýjan viðburð og setur hann fremstan
     */
    public void nyr() {
        currentView = new EventView();
        fxEventViews.getChildren().add(currentView);
        switchView(currentView);
    }

    /**
     * Lokar viðburðinum og eyðir honum úr notendaviðmótinu
     * Viðburðurinn sem var bætt við síðast verður sýnilegru
     */
    public void loka() {
        // eyða currentView
        fxEventViews.getChildren().remove(currentView);
        if (!fxEventViews.getChildren().isEmpty()) {
            switchView(fxEventViews.getChildren().getLast());
            currentView = (EventView) fxEventViews.getChildren().getLast();
        }
        else {
            currentView = null;
        }
    }

    // Hjálparaðferðir

    /**
     * prívat aðferð sem finnur viðmót af viðburði eftir viðburðinum
     * @param eventModel viðburðurinn
     * @return viðmóti af viðburði
     */
    private Optional<EventView> finnaView(EventModel eventModel) {
        for (Node e : fxEventViews.getChildren()) {
            if (((EventView) e).getEventModel() == eventModel) {
                return Optional.of((EventView) e);
            }
        }
        return Optional.empty();
    }

    /**
     * Finnur viðburð samkvæmt heiti viðburðar
     * @param heiti heiti viðburðar
     * @return viðburðurinn
     */
    private Optional<EventModel> finnaVidburd(String heiti) {
        for (EventModel event : list) {
            if (event.heitiProperty().get().equals(heiti)) {
                return Optional.of(event);
            }
        }
        return Optional.empty();
    }

    /** Hjálparaðferð sem gerir viðmótsnóðu sýnilega
     *
     * @param targetView viðmótsnóðan sem á að vera sýnileg
     */
    private void switchView(Node targetView) {
        for (Node node : fxEventViews.getChildren()) {
            node.setVisible(false); // Fela alla viðburðina
        }
        targetView.setVisible(true); // Sýna gluggann sem er fremst núna
        targetView.setFocusTraversable(true);
    }

    /**
     * Ekki hluti af verkefni en sýnt hér til fróðleiks
     *
     * @param source
     */
    public void breytaLit(RadioMenuItem source) {
        System.out.println(source.getText());
        // athuga
        if (source.getText().equals("Svartur")) {
            fxEventViews.getStyleClass().remove("hvitur");
            fxEventViews.getStyleClass().remove("svartLetur");
            fxEventViews.getStyleClass().add(source.getText().toLowerCase());
            fxEventViews.getStyleClass().add("hvittLetur");
        }
        else if (source.getText().equals("Hvítur")) {
            fxEventViews.getStyleClass().remove("svartur");
            fxEventViews.getStyleClass().remove("hvittLetur");
            fxEventViews.getStyleClass().add("svartLetur");
            fxEventViews.getStyleClass().add("hvitur");
        }
    }

}
