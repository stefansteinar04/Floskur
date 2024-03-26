package is.vidmot;
/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *  Viðmótsforritun 2024
 *
 *  Viðmótsklasi fyrir Leikborð. Hefur grafara og gull. Er tengdur við leikinn.
 *  Er Framhlið (Facade) fyrir vinnsluna, grafarann og gullið.
 *
 *  Framleiðir gull, færir grafara áfram, athuga hvort grafari grefur gull, ákveður
 *  hvort á að framleiða meira gull. Flest virkni er áframsend annað
 *****************************************************************************/

import is.vinnsla.Leikur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Leikbord extends Pane {

      // viðmótshlutir
    @FXML
    private Grafari fxGrafari;  // Grafarinn

    private ObservableList<Gull> gullid = FXCollections.observableArrayList(); // gullið

    // vinnslan
    private Leikur leikur;

    public Leikbord() {
        FXML_Lestur.lesa(this, "leikbord-view.fxml");
    }


    private void framleidaGull() {
        Gull g = new Gull((int) getWidth(),
                (int)getHeight());
        getChildren().add(g);
        gullid.add(g);
    }


    /**
     * Færir grafara áfram, athugar hvort hann grefur gull og hækkar stigin til samræmis
     */
    public void afram() {
        fxGrafari.afram();
        if (erGrefurGull()) {
            leikur.haekkaStigin();
        }
    }

    /**
     * Athugar hvort grafari grefur gull og fjarlægir gullið af borðinu
     *
     * @return
     */
    private boolean erGrefurGull() {
        for (Gull g : gullid) {
            if (g.intersects(fxGrafari.getBoundsInParent())) {
                gullid.remove(g);
                getChildren().remove(g);
                return true;
            }
        }
        return false;
    }

    /**
     * Ákveður hvort eigi að framleiða meira gull
     */
    public void meiraGull() {
        if (leikur.erMeiraGull()) {
            framleidaGull();
        }
    }

    /**
     * Hefja nýjan leik. Allt gullið tekið af borðinu
     */

    public void nyrLeikur() {
        getChildren().removeAll(gullid);
        gullid.removeAll();
        leikur.nyrLeikur();
    }


    // set og get aðferðir
    public void setLeikur(Leikur leikur) {
        this.leikur = leikur;
    }

    public void setStefna(int gradur) {
        fxGrafari.setStefna(gradur);
    }
}
