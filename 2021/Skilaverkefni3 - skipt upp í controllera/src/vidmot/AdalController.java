package vidmot;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import vinnsla.Nemandi;

import java.net.URL;
import java.util.ResourceBundle;

public class AdalController implements Initializable {
    @FXML

    private ListView<Nemandi> fxNemendalisti;


    @FXML
    private ValmyndController fxValmyndController;   // id á valmyndinni include er fxValmynd (Controller er svo skeytt við)


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println ("valmyndController "+fxValmyndController);
        fxValmyndController.setControllers(this);
    }

    public Nemandi getNuverandi() {
        return fxNemendalisti.getSelectionModel().getSelectedItem();
    }

    public void baetaVidNemanda(Nemandi n) {
        fxNemendalisti.getItems().add(n);
    }

    public void setListi(ObservableList<Nemandi> l) {
        fxNemendalisti.setItems(l);
    }

    public void eyda(Nemandi nem) {
        fxNemendalisti.getItems().remove(nem);
    }

    public ObservableList<Nemandi> getListi() {
        return fxNemendalisti.getItems();
    }

}

