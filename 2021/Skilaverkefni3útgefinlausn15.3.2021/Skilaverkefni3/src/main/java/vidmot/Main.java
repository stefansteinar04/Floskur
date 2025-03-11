/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Aðalklasi forritsins. Kallað á start. Ræsir fyrsta gluggann
 *
 *
 *****************************************************************************/
package vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static final String CSS_SKRA = "CRUD.css";

    /**
     * Hleður inn notendaviðmóti fyrir fyrsta gluggann og setur titil á hann
     *
     * @param primaryStage aðalglugginn
     * @throws Exception ónotað
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("AdalGluggi.fxml"));
        primaryStage.setTitle("Nemendaskrá");
        Scene s = new Scene(root, 400, 274);
        primaryStage.setScene(s);
        // Stylesheet bætt við - meira til sýnis
        s.getStylesheets().add(getClass().getResource(CSS_SKRA).toExternalForm());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
