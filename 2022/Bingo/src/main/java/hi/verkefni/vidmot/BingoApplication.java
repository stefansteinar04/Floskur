package hi.verkefni.vidmot;
/* *****************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Aðalapplication klasi fyrir Bingó forritið.
 *  Les inn notendaviðmótið fyrir bingóspjald
 *
 *
 *****************************************************************************/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BingoApplication extends Application {
    /**
     * Aðalnotendaviðmót lesið inn, titillinn settur og glugginn birtur
     * @param stage aðalglugginn
     * @throws IOException ef bingo-view.fxml skráin er ekki til
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                BingoApplication.class.getResource("bingo-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Bingo");
        stage.setScene(scene);
        stage.show();   // birta gluggann
    }

    public static void main(String[] args) {
        launch();
    }
}
