package is.verkefni4.vidmot;


import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import static javafx.application.Application.launch;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Aðalleikborðið. Inniheldur bolta og palla.
 *****************************************************************************/
public class Leikbord extends Pane implements Leikhlutur{

    @FXML
    private Bolti fxBolti;  // boltinn
    private ObservableList<Node> pallar = FXCollections.observableArrayList(); // til þæginda

    public Leikbord() {
        upphafsstilla();
    }

    private void upphafsstilla() {
        FXML_Lestur.lesa(this, "leikbord-view.fxml"); // lesa fxml skrána
        for (int i = 1; i < getChildren().size(); i++) { // setja pallana í pallar breytuna til hægðarauka
            pallar.add(getChildren().get(i));
        }
        fxBolti.setPallur(null); // boltinn er ekki á neinum palli
    }


    /**
     * Færir boltann áfram. Ef boltinn er á palli má færa sig til vinstri eða hægri
     */
    public void afram() {
        if (fxBolti.getPallur() == null) { // bolti er ekki á neinum palli
            fxBolti.afram(); // eitt skref vinstri eða hægri en svo ...
            fxBolti.setStefna(Stefna.NIDUR); // niður
        } else {
            fxBolti.aframAPalli(); // má færa bolta til vinstri eða hægri
        }

    }

    /**
     * Færir palla upp á y ás og athugar hvort bolti er á einhverjum palli
     */
    public void aframPallar() {
        for (Node p : pallar) { // færa alla palla áfram
            ((Leikhlutur) p).afram();
            athugaBoltiAPalli((Pallur) p); // athuga hvort boltinn hefur komist á pall eða er að fara af honum
        }
    }

    /**
     * Athuga hvort bolti kemst á pall p og ef svo bindur boltann við pallinn.
     * Ef bolti var á palli p en ekki lengur er honum hent af palli
     *
     * @param p pallur
     */
    private void athugaBoltiAPalli(Pallur p) {
        if (fxBolti.getBoundsInParent().intersects(p.getBoundsInParent())) { // er hún á palli p  núna
            if (!fxBolti.erAPalli(p)) { // bolti var  að komast á pallinn
                setjaBoltaAPall(p); // setja bolta á pall
            }
        } else if (fxBolti.erAPalli(p)) { // fór af pallinum - aftengja
            hendaBoltaAfPalli(p);   // henda bolta af palli
        }
    }

    /**
     * Setja bolta á pall. Tengir boltann við pallinn og bolti er á einhverjum palli
     * Stefnan er niður
     *
     * @param p pallur
     */
    private void setjaBoltaAPall(Pallur p) {
        fxBolti.setPallur(p);   // segja á hvaða palli boltinn er
        fxBolti.yProperty().bind(Bindings
                .createDoubleBinding(() -> p.yProperty().get() - 25,
                        p.yProperty()));

    }

    /**
     * Henda bolta af palli. Bolti aftengdur palli. Bolti er ekki á neinum palli
     *
     * @param p pallur
     */
    public void hendaBoltaAfPalli(Node p) {
        fxBolti.setPallur(null); // af palli
        fxBolti.yProperty().unbind(); // ekki fylgja pallinum lengur
    }


    public Bolti getBolti() {
        return fxBolti;
    }

    /**
     * Athuga hvort boltinn er fallinn á botninn
     *
     * @return true ef boltinn er fallinn á botninn
     */
    public boolean erBoltiABotni() {
        return fxBolti.erBoltiABotni(this);
    }

    /**
     * Nýr leikur. Pöllum eytt og upphafsstillt
     */
    public void nyrLeikur() {
        getChildren().clear();  // fjarlægja bolta og palla
        pallar = FXCollections.observableArrayList(); // núllstilla breytu til hægðarauka
        upphafsstilla();    // upphafsstilla eins og í byrjun
    }
}
