package is.vidmot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.Random;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Eitursnákur. Útlit lesið úr .fxml skrá. Hægt að færa áfram
 *
 *
 *****************************************************************************/
public class EiturSnakur extends Rectangle {

    // fastar
    private static final String FXML_SKRA = "eiturSnakur-view.fxml";
    private static final Random random = new Random();

    // viðmótshlutir
    @FXML
    private int OFFSET;         // hvað eitursnákur færist mikið, annað hvort x eða y þegar snákur færist


    public EiturSnakur() {
        lesa(FXML_SKRA);
    }

    /**
     * Setja eitursnák á borð b á random stað
     * @param b leikjaborð
     */
    public void setjaABord (SnakurBord b) {
        setX(random.nextInt((int) (b.getWidth() - getWidth())));
        setY(random.nextInt((int)( b.getHeight() - getHeight())));
        b.getChildren().add(this);
    }

    /**
     * Lesa inn útlit úr fxml skrá
     * @param fxmlSkra nafn á fxml skrá
     */
    protected void lesa(String fxmlSkra) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlSkra));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Eitursnákur færist um OFFSET miðað við núverandi stefnu.
     */
    public void afram() {
        SnakurBord p = (SnakurBord) this.getParent();
       setX((int)(getX() + p.getWidth() + (int) Math.cos(Math.toRadians(getRotate())) * OFFSET) % (int)p.getWidth());
       setY((int)(getY() + p.getHeight() - (int) Math.sin(Math.toRadians(getRotate())) * OFFSET) % (int)p.getHeight());
    }
}
