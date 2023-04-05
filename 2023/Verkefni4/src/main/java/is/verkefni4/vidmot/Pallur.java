package is.verkefni4.vidmot;

import javafx.scene.shape.Rectangle;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Pallur sem er eins konar ferhyrningur og útfærir Leikhlutur interface
 *****************************************************************************/
public class Pallur extends Rectangle implements Leikhlutur {

    public Pallur() {
        FXML_Lestur.lesa(this, "pallur-view.fxml");

    }

    @Override
    public void afram() {
        Leikbord leikbord = (Leikbord) getParent(); // ná í foreldrið
        Bolti bolti = leikbord.getBolti(); // ná í boltann á leikborðinu
        if (getY() < 0) {   // er pallurinn kominn efst
            athugaBoltinnEfst(leikbord, bolti);
        } else
            setY(getY() - 1); // færa pallinn upp um eitt skref
      }

    /**
     * Pallurinn er efst. Ef bolti fylgir pallinum
     * @param l
     * @param bolti boltinn sem verið er að athuga hvort er á palli
     */
    private void athugaBoltinnEfst(Leikbord l, Bolti bolti) {
        if (bolti.erAPalli(this)) { // Ef boltinn á þessum palli
            l.hendaBoltaAfPalli(this); // henda boltanum af palli
            bolti.setY(l.getHeight() - bolti.getFitHeight()*2); // setja boltann neðst
            bolti.setX(bolti.getFitWidth()/2); // og í vinstra hornið
        }
        setY(l.getHeight() - getHeight()); // staðsetja pallinn neðst
    }
}
