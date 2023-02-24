package is.vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Main klasinn fyrir verkefni 1 - Les inn lýsingu á notendaviðmóti úr .fxml
 *  skrá og birtir gluggann
 *
 *
 *****************************************************************************/

public class ReiknivelApplication extends Application {

    // endurskrifað fall frá Application klasanum
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ReiknivelApplication.class.getResource("reiknivel-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load()); // sjálfgefin stærð
        stage.setTitle("Reiknivél");    // titill á glugga
        stage.setScene(scene);
        stage.show();
    }

    // main fall sem kallað er á þegar forritið er ræst
    public static void main(String[] args) {
        launch();
    }
}