package is.vidmot;
/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *  Viðmótsforritun 2024
 *
 *  Viðmótsklasi fyrir gull
 *  Gull veit lítið
 *
 *****************************************************************************/

import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Gull extends Rectangle {

    private static final Random rand = new Random(); // slembigjafi

    /**
     * Smiður fyrir gull. Les inn notendaviðmótið
     */
    public Gull() {
        FXML_Lestur.lesa(this, "gull-view.fxml");
    }

    // smiður fyrir gull í random staðsetningu á fleti sem er breidd x haed
    public Gull(int breidd, int haed) {
        this(); // kallar á smiðinn sem er án parametra
        setX(rand.nextInt((int) (breidd - getWidth())));
        setY(rand.nextInt((int) (haed - getHeight())));
    }
}
