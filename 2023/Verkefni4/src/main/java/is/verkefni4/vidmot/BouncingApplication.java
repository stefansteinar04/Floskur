package is.verkefni4.vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Application klasi fyrir bouncedown
 *****************************************************************************/
public class BouncingApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Scene s = new Scene(new Pane());
        ViewSwitcher.setScene (s);
        ViewSwitcher.switchTo(View.BOUNCING);
        BouncingController bouncingController = (BouncingController) ViewSwitcher.lookup(View.BOUNCING);
        stage.setTitle("Bounce ball");
        stage.setScene(s);
        stage.show();

        bouncingController.orvatakkar();    // setur upp örvatakka
        bouncingController.hefjaLeik();     // byrjar leikinn
    }


    public static void main(String[] args) {
        launch();
    }
}