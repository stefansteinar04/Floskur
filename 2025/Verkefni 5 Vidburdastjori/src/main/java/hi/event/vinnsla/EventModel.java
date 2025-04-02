package hi.event.vinnsla;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;

import java.time.LocalDate;
import java.time.LocalTime;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *  Lýsing  : Vinnslu (Model) klasi fyrir viðburði
 *
 *
 *****************************************************************************/
public class EventModel {

    private static ObservableList<Flokkur> allirFlokkar = FXCollections.observableArrayList(
            Flokkur.values()
    );
    // heiti viðburðar
    private final SimpleStringProperty heitiProperty = new SimpleStringProperty("Lorem ipsum");
    // dagsetning viðburðar
    private final SimpleObjectProperty<LocalDate> dateProperty
            = new SimpleObjectProperty<>(LocalDate.now());
    // tími viðburðar
    private final SimpleObjectProperty<LocalTime> timeProperty
            = new SimpleObjectProperty<>(LocalTime.now());
    // flokkur viðburðar
    private final SimpleObjectProperty<Flokkur> flokkurProperty
            = new SimpleObjectProperty<>(Flokkur.FJOLSKYLDA);
    // kynningarmyndband viðburðar
    private final SimpleObjectProperty<Media> mediaProperty = new SimpleObjectProperty<>();



    /**
     * skilar heiti viðburðar
     *
     * @return heiti viðburðar
     */
    public SimpleStringProperty heitiProperty() {
        return heitiProperty;
    }

    /**
     * skilar dagsetningu viðburðar
     *
     * @return dagsetning
     */
    public SimpleObjectProperty<LocalDate> dateProperty() {
        return dateProperty;
    }

    /**
     * skilar tíma viðburðar
     *
     * @return tími
     */
    public SimpleObjectProperty<LocalTime> timeProperty() {
        return timeProperty;
    }

    /**
     * skilar flokki viðburðar
     *
     * @return flokkur viðburðar
     */
    public SimpleObjectProperty<Flokkur> flokkurProperty() {
        return flokkurProperty;
    }

    /**
     * Skilar videói viðburðar
     *
     * @return videó
     */
    public SimpleObjectProperty<Media> mediaProperty() {
        return mediaProperty;
    }

    /**
     * skilar öllum flokkum viðburða
     *
     * @return allir flokkar viðburða
     */
    public static ObservableList<Flokkur> getAllirFlokkar() {
        return allirFlokkar;
    }
}

