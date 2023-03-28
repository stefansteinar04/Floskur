package is.vidmot;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextInputDialog;

import java.io.IOException;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Gluggi fyrir innskráningu
 *
 *
 *****************************************************************************/
public class LoginDialog  {

    public TextInputDialog upphafsstilla () {
        TextInputDialog t = lesaLoginDialog();
        t.setTitle("");
        t.setGraphic(null);
        DialogPane pane = t.getDialogPane();
        ObservableList<ButtonType> types = pane.getButtonTypes();
        types.set(0, new ButtonType("Í lagi", ButtonBar.ButtonData.OK_DONE));
        types.set(1, new ButtonType("Hætta við", ButtonBar.ButtonData.CANCEL_CLOSE));
        return t;
    }

    private TextInputDialog lesaLoginDialog() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        try {
            fxmlLoader.setController(this);
            return fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
