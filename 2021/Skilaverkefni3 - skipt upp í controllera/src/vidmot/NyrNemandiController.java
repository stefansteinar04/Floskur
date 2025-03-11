package vidmot;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import vinnsla.Deild;
import vinnsla.Namsleid;
import vinnsla.Nemandi;
import vinnsla.Svid;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  :
 *
 *
 *****************************************************************************/
public class NyrNemandiController implements Initializable {

    private static final String HAUS = "Nemendaskrá";
    private static final String TITILL = "Nýr nemandi";
    private static final Deild[][] DEILDIR = {{Deild.IVT, Deild.UB, Deild.JVD, Deild.LUD, Deild.RAUN, Deild.RT}
            , {Deild.FEL, Deild.HAG, Deild.LAG, Deild.STJ, Deild.FMÞ, Deild.VID}};
    private static final Svid[] SVID = {Svid.VON, Svid.FVS, Svid.HEI, Svid.HUG, Svid.MVS,};
    private static final Namsleid[][][] NAMSLEIDIR = {{{Namsleid.HBV, Namsleid.REI, Namsleid.TÖL}}};
    public static final int EKKERTVALID = -1;

    @FXML
    private Pane fxPane;

    @FXML
    private TextField fxPostur;
    @FXML
    private TextField fxNemandi;
    @FXML
    private ComboBox<Deild> fxDeild;
    @FXML
    private ComboBox<Svid> fxSvid;
    @FXML
    private ComboBox<Namsleid> fxNamsleid;

    private Dialog<Nemandi> dialogNemandi;


    public Nemandi nyrNemandi() {
        // opna gluggann og fá niðurstöðu
        Optional<Nemandi> utkoma = dialogNemandi.showAndWait();
        frumstilla();
        return utkoma.orElse(null);
    }

    private void frumstilla() {
        fxNemandi.setText("");
        fxPostur.setText("");
        fxDeild.getSelectionModel().clearSelection();
        fxSvid.getSelectionModel().clearSelection();
        fxNamsleid.getSelectionModel().clearSelection();
    }

    private void frumstilla(Nemandi nem) {
        fxNemandi.setText(nem.getNafn());
        fxPostur.setText(nem.getTölvupóstfang());
        fxDeild.getSelectionModel().select(nem.getDeild());
        fxSvid.getSelectionModel().select(nem.getSvid());
        fxNamsleid.getSelectionModel().select(nem.getNamsleid());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dialogNemandi = buaTilDialog();
    }

    private Dialog<Nemandi> buaTilDialog() {
        // Innihald dialogs búið til
        DialogPane p = new DialogPane();
        fxPane.setVisible(true);

        // Innihald sett sem fxPane
        p.setContent(fxPane);


        // Umgjörðin búin til - Dialog sem skilar gildi (hlut) af taginu (klasanum) ButtonType
        Dialog<Nemandi> d = new Dialog<>();

        setConverter(d);

        // og innihaldið sett í umgjörðina
        d.setDialogPane(p);

        hnapparILagiHaettaVid(d);
        // textar fyrir haus settir í dialog d
        haus(d);

        // setja í comboboxin
        setjaComboBox();

        return d;
    }

    private void haus(Dialog<Nemandi> d) {
        d.setHeaderText(HAUS);
        d.setTitle(TITILL);
    }

    private void setjaComboBox() {
        fxSvid.getItems().addAll(SVID);
        fxDeild.getItems().addAll(DEILDIR[0]);
        fxNamsleid.getItems().addAll(NAMSLEIDIR[0][0]);
    }

    public void setConverter(Dialog<Nemandi> d) {
        d.setResultConverter(b -> {                                 // b er af taginu ButtonType
            if (b.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return new Nemandi(fxNemandi.getText(),
                        fxPostur.getText(),
                        fxSvid.getSelectionModel().getSelectedItem(),
                        fxDeild.getSelectionModel().getSelectedItem(),
                        fxNamsleid.getSelectionModel().getSelectedItem());
            } else {
                return null;
            }
        });
    }

    /**
     * Býr til tvo hnappa, Í lagi og Hætta við og bætt við dialog d
     *
     * @param d dialogur
     * @return Skilar Í lagi hnappnum
     */
    private void hnapparILagiHaettaVid(Dialog<Nemandi> d) {

        ButtonType iLagi = new ButtonType("Í lagi",
                ButtonBar.ButtonData.OK_DONE);
        d.getDialogPane().getButtonTypes().add(iLagi);
        ButtonType haettaVid = new ButtonType("Hætta við",
                ButtonBar.ButtonData.CANCEL_CLOSE);
        d.getDialogPane().getButtonTypes().add(haettaVid);
    }


    public void svidHandler(ActionEvent actionEvent) {
        int ix = fxSvid.getSelectionModel().getSelectedIndex();
        if (ix == EKKERTVALID) 
            ix=0;
        System.out.println ("svið handler "+ix);
        fxDeild.getItems().clear();
        fxDeild.getItems().addAll(DEILDIR[ix]);
    }

    public void deildHandler(ActionEvent actionEvent) {
        int ixD = fxDeild.getSelectionModel().getSelectedIndex();
        int ixS = fxSvid.getSelectionModel().getSelectedIndex();
      /*  fxNamsleid.getItems().clear();
        fxNamsleid.getItems().addAll(NAMSLEIDIR[ixS][ixD]);*/
    }

    public Nemandi breytaNemandi(Nemandi nem) {
        frumstilla(nem);
        Optional<Nemandi> utkoma = dialogNemandi.showAndWait();
        frumstilla();
        return utkoma.orElse(null);
    }


}
