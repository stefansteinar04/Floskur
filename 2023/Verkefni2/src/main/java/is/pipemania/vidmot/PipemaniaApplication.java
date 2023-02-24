package is.pipemania.vidmot;

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
public class PipemaniaApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PipemaniaApplication.class.getResource("pipemania-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Pipe mania");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}