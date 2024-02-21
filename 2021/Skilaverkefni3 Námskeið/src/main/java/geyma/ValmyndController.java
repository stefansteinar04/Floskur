package geyma;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.layout.Pane;
import vidmot.AdalController;
import vidmot.NyrNemandiController;
import vinnsla.Nemandi;
import vinnsla.Nemendaskra;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ValmyndController implements Initializable {

    public Menu fxMenu1;
    private NyrNemandiController nyrNemandiController;
    private AdalController adalController;

    private Nemendaskra skra = new Nemendaskra();

    private boolean breyttSkra=false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            nyrNemandiController = hladaDialog();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Hleður inn dialog fyrir Nemanda
     *
     * @return controller fyrir dialoginn
     * @throws java.io.IOException // ef fxml skráin finnst ekki
     */
    private NyrNemandiController hladaDialog() throws java.io.IOException {
        FXMLLoader dLoader = new FXMLLoader(getClass().getResource("vidmot/Nemandi.fxml"));
        dLoader.load();
        return dLoader.getController();
    }

    /**
     * Býr til nýjan nemanda
     *
     * @param actionEvent
     */
    public void nyrNemandiHandler(ActionEvent actionEvent) {
        Nemandi n = nyrNemandiController.nyrNemandi();
        System.out.println(n);
        if (n != null) {
            adalController.baetaVidNemanda(n);
            breyttSkra = true;
        }
    }


    public void setControllers(AdalController adalController) {
        this.adalController = adalController;
    }


    public void lesaHandler(ActionEvent actionEvent) {
        adalController.setListi(skra.lesa("buu"));
        breyttSkra=false;
    }

    public void breytaHandler(ActionEvent actionEvent) {
        Nemandi nem = adalController.getNuverandi();
        if (nyrNemandiController.breytaNemandi(nem))
            breyttSkra=true;
    }

    public void eydaHandler(ActionEvent actionEvent) {
        Nemandi nem = adalController.getNuverandi();
        if (erStadfest (nem)) {
            adalController.eyda(nem);
            breyttSkra = true;
        }
    }

    private boolean erStadfest(Nemandi nem) {
        return true;
    }

    public void finnaHandler(ActionEvent actionEvent) {
        Nemandi nem = adalController.getNuverandi();

    }

    public void skrifaHandler(ActionEvent actionEvent) {
        breyttSkra=false;
        skra.skrifa(adalController.getListi(),"buu");
    }
}
