/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Snákur. útlit lesið úr .fxml skrá. Getur athugað hvort hann étur fæðu,
 *  lendir í árekstri við eitursnák. Getur vaxið.
 *
 *
 *****************************************************************************/

package is.vidmot;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;


public class Snakur extends EiturSnakur {

    // Fastar
     private static final String SNAKUR_VIEW_FXML = "snakur-view.fxml";

    // Viðmótshlutir
    @FXML
    private double STAEKKUN;  // hvað snákurinn stækkar mikið þegar fæða er étinn


    /**
     * Almennari smiður með hvaða lit sem er - hér er verið að sýna að hægt er að hafa tvo smiði
     */
    public Snakur(SnakurBord b) {         // kallar á smið yfirklasans (hér EiturSnakur)
        lesa(SNAKUR_VIEW_FXML);
    }


    /**
     * Snákurinn vex - lengist um STAEKKUN
     */
    public void vaxa() {
        setWidth(getWidth() + STAEKKUN);
        System.out.println (" vaxa "+getY());
    }


    /**
     * Setja upp handler fyrir snák - til að hægt sé að setja leik í bið
     */
    public void setjaUppBid() {
        EventHandler<MouseEvent> mus = mouseEvent -> ((SnakurBord) getParent()).getController().bida();
        setOnMousePressed(mus);
    }

    /**
     * Athugar hvort snákur rekst á eitursnák
     *
     * @param d eitursnákur
     * @return satt ef árekstur annars false
     */
    public boolean erArekstur(EiturSnakur d) {
        return getBoundsInParent().intersects(d.getBoundsInParent());
    }

    /**
     * Athugar hvort snákur etur (skarast við) fæðu.
     *
     * @param f fæða
     * @return satt ef snákur étur fæðu annars false
     */
    public boolean erEtur(Faeda f) {
        return getBoundsInParent().intersects(f.getBoundsInParent());
    }
}
