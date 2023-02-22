/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Aðalklasi fyrir snákleikinn. Meðhöndlar örvatakka á senuna
 *
 *
 *****************************************************************************/
package is.vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SnakurApplication extends Application {
    public static final String SNAKE = "Snake"; // Nafn á leiknum

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Ræsir forritið. Setur upp örvatakka filter á senuna og biður controller að setja upp leikjalykkjuna
     *
     * @param primaryStage aðalglugginn
     * @throws Exception ónotað
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("snakurAdal-view..fxml"));
        Parent root = loader.load();
        SnakurController sc = loader.getController();
        primaryStage.setTitle(SNAKE);
        Scene s = new Scene(root, 500, 400);
        sc.orvatakkar();          // tengir atburð frá örvatökkum við fall í controller
        primaryStage.setScene(s);
        primaryStage.show();
        sc.hefjaLeik();
    }


}
