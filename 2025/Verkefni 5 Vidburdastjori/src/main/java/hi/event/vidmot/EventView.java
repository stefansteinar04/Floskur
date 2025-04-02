package hi.event.vidmot;

import hi.event.vinnsla.EventModel;
import hi.event.vinnsla.Flokkur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Viðmótsklasi fyrir EventModel. Er custom component. Þetta er controllerinn
 *  fyrir viðmótið
 *
 *
 *****************************************************************************/
public class EventView extends VBox { // Erfir frá VBox
    @FXML
    private DatePicker fxDate; // dagsetning viðburðar

    @FXML
    private ComboBox<Flokkur> fxFlokkur; // flokkur viðburðar

    @FXML
    private TextField fxHeiti; // heiti viðburðar

    @FXML
    private Spinner<Integer> fxTime; // tíminn

    @FXML
    private KynningController kynningController; // controller fyrir media spilun

    @FXML
    private FileChooser fxFileChooser; // velja skrá fyrir viðmótið

    @FXML
    private EventModel eventModel = new EventModel(); // tenging yfir í vinnsluna

    /**
     * Smiður sem les inn notendaviðmótið - Custom component
     */
    public EventView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("event-view.fxml"));
        fxmlLoader.setRoot(this);   // rótin á viðmótstrénu sett hér
        fxmlLoader.setController(this); // controllerinn settur hér en ekki í .fxml skránni
     // muna að setja þetta með
        fxmlLoader.setClassLoader(getClass().getClassLoader());
        try {
            fxmlLoader.load();          // viðmótstréð lesið inn (þ.e. .fxml skráin)
        }
        catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Smiður sem býr til viðbmótshlut með tilteknum viðburði
     * @param eventModel viðburðurinn
     */
    public EventView(EventModel eventModel) {
        this();
        this.eventModel = eventModel;
        initialize(); // þarf að kalla á sérstaklega
    }

    /**
     * Frumstillir controllerinn eftir að hann hefur verið smíðaður.
     * setur upp tengingar á milli viðmóts og vinnslu
     * setur upp viðburðaflokkana
     */
    public void initialize() {
        fxFlokkur.setItems(EventModel.getAllirFlokkar());
        fxHeiti.textProperty().bindBidirectional(eventModel.heitiProperty());
        fxFlokkur.valueProperty().bindBidirectional(eventModel.flokkurProperty());
        fxDate.valueProperty().bindBidirectional(eventModel.dateProperty());
        kynningController.setEventView (this);
        setjaUppTimann();
        kynningController.setAlignment();
    }

    private void setjaUppTimann() {

        // búinn til Spinner
        fxTime.setValueFactory(new SpinnerValueFactory
                .IntegerSpinnerValueFactory(0, 23, 12));

        // Þegar fxTime breytist -> Uppfærum eventModel
        fxTime.valueProperty().addListener((obs, oldVal, newVal) ->
                eventModel.timeProperty().set(LocalTime.of(newVal, 0))
        );

        // Þegar eventModel breytist -> Uppfærum Label (fxTime)
        eventModel.timeProperty().addListener((obs, oldTime, newTime) ->
                fxTime.getValueFactory().setValue(newTime.getHour()));
    }


    @FXML
    void opnaSkra(ActionEvent event) {
        File file = fxFileChooser.showOpenDialog(null);
        if (file != null) {
            Media media = new Media(file.toURI().toString());
            eventModel.mediaProperty().set(media);
        }
        else {
            System.out.println ("skrá fannst ekki");
        }
    }

    /**
     * Hjálparaðferð til að skoða viðmmótshlutinn
     * @return
     */
    @Override
    public String toString() {
        return "EventView{" +
                ", fxHeiti=" + fxHeiti.getText() +
                "} " + super.toString();
    }

    /**
     * get aðferðir til að skila vinnsluhlutanum
     * @return viðburðurinn fyrir þennan viðmótshlut
     */
    public EventModel getEventModel() {
        return eventModel;
    }
}
