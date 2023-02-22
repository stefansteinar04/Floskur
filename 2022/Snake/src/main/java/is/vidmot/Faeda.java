/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Fæðan á leiksvæðinu - Athugið hefur mjög litla viðbótarvirkni og engin
 *  viðbótargögn.
 *
 *
 *****************************************************************************/

package is.vidmot;

import javafx.fxml.FXMLLoader;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Random;

public class Faeda extends Circle {
    private static final Random random = new Random();

    public Faeda(SnakurBord b) {
        lesa();
        // setja á random stað - ekki búið að teikna enn

        int x = random.nextInt((int) (b.getWidth() - 2 * getRadius())) + (int) getRadius();
        setCenterX(x);
        int y = random.nextInt((int) (b.getHeight() - 2 * getRadius())) + (int) getRadius();
        setCenterY(y);
    }

    public void lesa() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("faeda-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }
}
