/*
Ebba Þóra Hvannberg ebba@hi.is
 */
package is.hi.utlit;

import is.hi.vinnsla.Nidurstodur;
import is.hi.vinnsla.VedursparGogn;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextField;


/**
 *
 * @author Ebba Þóra Hvannberg ebba@hi.is
 * Aðal controller fyrir veðurupplýsingar. Birtir lista af veðurstöðvum sem eru
 * lesnar úr XML skrá og leyfir notanda að velja veðurstöð og skoða
 * veðurspár. Leyfir notanda einnig að afmarka veðurstöðvar með textaleit
 */
public class AdalController implements Initializable {
    @FXML
    private SkodaVedurspaController skodaVedurspaController; 
    @FXML
    private TimaLeitController timaleitController;
    @FXML
    private ListView<Nidurstodur.AllarStodvar.Vedurstod> vedurstodvarView;
    @FXML
    private TextField leitaVedurstod;
    
    private VedursparGogn vedursparStodva;    // Safnklasi sem hefur veðurspár stöðva
                                              // Óafmarkaður listi
    
    private int virkurIndex;                  // index staksins sem síðast var valið
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vedursparStodva = new VedursparGogn();
        // Birta allar stöðvarnar 
        vedursparStodva.birtaVedurstodvar();
        // Ná í veðurstöðvarnar
        vedurstodvarView.setItems(heildarListi(vedursparStodva.
                getAllarStodvar()));
        frumstillaGognHandlerListi();
        skodaVedurspaController
                    .vedurspaBirta(vedurstodvarView.getItems().get(0), 
                            vedurstodvarView.getItems().get(0).getSpaStodvar()
                                    .getVedurspa());
        timaleitController.setjaUppTimaController(this, skodaVedurspaController);
    }    


    /**
     * Búa til ObservableList af veðurstöðvum v
     * @param v veðurstöðvar 
     * @return lista af veðurstöðvum
     */
    private ObservableList<Nidurstodur.AllarStodvar.Vedurstod> heildarListi(ArrayList<Nidurstodur.AllarStodvar.Vedurstod> v) {
            ObservableList<Nidurstodur.AllarStodvar.Vedurstod> obl = FXCollections.observableArrayList(v);
            return obl;
    }    

    /***
     * Frumstillir atburðahandler fyrir lista af veðurstöðvum. Gert til að hægt
     * sé að geyma index-inn
     * 
     */
    private void frumstillaGognHandlerListi() {
        MultipleSelectionModel<Nidurstodur.AllarStodvar.Vedurstod> msl = vedurstodvarView.getSelectionModel();
        msl.selectedItemProperty().addListener(new ChangeListener<Nidurstodur.AllarStodvar.Vedurstod>() {

            @Override
            public void changed(ObservableValue<? extends Nidurstodur.AllarStodvar.Vedurstod> observable, 
                    Nidurstodur.AllarStodvar.Vedurstod oldValue, Nidurstodur.AllarStodvar.Vedurstod newValue) {
                virkurIndex = msl.getSelectedIndex();
            }
        });
    }
    
    /**
     * Handler fyrir að skoða veðurspár fyrir veðurstöð
     * Birtir lista af veðurspám. Setur kls val fyrir combobox sem allan daginn
     * @param event 
     */
    @FXML
    private void skodaHandler(ActionEvent event) {
        if (virkurIndex != -1) {
            skodaVedurspaController
                    .vedurspaBirta(vedurstodvarView.getItems().get(virkurIndex), 
                            vedurstodvarView.getItems().get(virkurIndex)
                                    .getSpaStodvar().getVedurspa());
        }
        timaleitController.allanDaginn();
    }

    /**
     * Handler fyrir að leita að veðurstöðvum sem innihalda leitarstreng úr
     * leitaVedurstod. Uppfærir listann af veðurstöðvum. Valinn veðurstöð er 0
     * @param event 
     */
    @FXML
    private void leitaHandler(ActionEvent event) {
        String l = leitaVedurstod.getText();
        ArrayList<Nidurstodur.AllarStodvar.Vedurstod>  allarStodvar = vedursparStodva.getAllarStodvar();
        ArrayList<Nidurstodur.AllarStodvar.Vedurstod> nidurstada = new ArrayList<>();
        for (Nidurstodur.AllarStodvar.Vedurstod s:allarStodvar) {
            if (s.getName().contains(l)) {
                nidurstada.add(s);
            }
         }
        vedurstodvarView.setItems(heildarListi(nidurstada));
        virkurIndex=0;
    }
    

    /**
     * Skilar veðurspám valdra stöðvar (virkurIndex)
     * @return listi af veðurspám fyrir eina veðurstöð 
     */
    
    ObservableList<Nidurstodur.AllarStodvar.Vedurstod.SpaStodvar.Vedurspa> vedursparValdrarStodvar() {
        return FXCollections.observableArrayList(vedurstodvarView.getItems().get(virkurIndex).getSpaStodvar().getVedurspa());
    }
}
