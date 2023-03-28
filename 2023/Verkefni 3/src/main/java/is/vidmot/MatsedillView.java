package is.vidmot;

import is.vinnsla.Matsedill;
import is.vinnsla.Veitingar;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;

import java.io.IOException;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Viðmótshlutur fyrir matseðil af veitingum
 *
 *
 *****************************************************************************/
public class MatsedillView extends ListView<Veitingar> {

    public MatsedillView() {
        lesaVidmot();
        Matsedill m = new Matsedill();
        m.setjaGogn();
        setItems(m.getVeitingar());
    }

    /**
     * Les inn notendaviðmótið - er sérhæfður klasi
     */
    private void lesaVidmot() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("matsedill-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (
                IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
