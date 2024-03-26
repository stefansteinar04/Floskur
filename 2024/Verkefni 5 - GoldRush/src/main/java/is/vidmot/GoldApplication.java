package is.vidmot;
/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *  Viðmótsforritun 2024
 *
 *  Application klasi fyrir Goldrush forritið
 *****************************************************************************/
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GoldApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GoldApplication.class.getResource("goldrush-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 420, 440);
        stage.setTitle("Gold rush ");
        stage.setScene(scene);
        ((GoldController)fxmlLoader.getController()).orvatakkar();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}