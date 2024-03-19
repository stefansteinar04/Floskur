package vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Main klasi fyrir Strengir application. Sér um að lesa inn notendaviðmótslýsingu og ræsa forritið
 *
 *****************************************************************************/
public class StrengirApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StrengirApplication.class.getResource("strengir-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 240);
        stage.setResizable(false);
        stage.setTitle("Strengir");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}