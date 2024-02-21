/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Controller fyrir dialog sem tekur við upplýsingum um nýskráðan nemanda eða
 *  þegar breyta þarf nemanda
 *
 *****************************************************************************/
package vidmot;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import vinnsla.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static vidmot.Main.CSS_SKRA;

public class NyrNemandiController implements Initializable {

    // Hnappar fyrir dialoga eru hér - notað á nokkrum stöðum AdvorunDialog LeitDialog NyrNemandiController. Gerir aðra klasa háðan þessum klasa.
    // Hefði mátt hanna öðruvísi og hafa hnappana í hverjum klasa fyrir sig. Hönnunarákvörðun
    private static final String I_LAGI = "Í lagi";
    private static final String HAETTA_VID = "Hætta við";
    public static final ButtonType BTYPE = new ButtonType(I_LAGI,
            ButtonBar.ButtonData.OK_DONE);
    public static final ButtonType HTYPE = new ButtonType(HAETTA_VID,
            ButtonBar.ButtonData.CANCEL_CLOSE); // ButtonType er merktur með CANCEL_CLOSE (er enum);

    // Ýmsir fastar
    private static final String HAUS = "Nemendaskrá";
    private static final String TITILL = "Nemandi";
    private static final Svid[] SVID = {Svid.FVS, Svid.HEI};    // Sviðin
    public static final int EKKERTVALID = -1;
    private static final Deild[][] DEILDIR = {      // Deildir sviða
            {Deild.FVS1, Deild.FVS2},
            {Deild.HEI1, Deild.HEI2}
    };
    public static final String GOGN = "gogn/";
    public static final String ENDING = ".txt";
    public static final String VILLUBOD_NAFN = "Nafn verður að byrja á stórum staf";
    public static final String VILLUBOD_POSTUR = "Verður að vera @ í póstfangi";


    // viðmóts tilviksbreytur - breytur fá gildi þegar .fxml skráin er lesin (load)  inn
    @FXML
    private Label fxVillaNafn;      // villuboð fyrir nafn nemanda
    @FXML
    private Label fxVillaPostur;    // villuboð fyrir tölvupóst
    @FXML
    private Pane fxPane;            // pane í dialog
    @FXML
    private TextField fxPostur;     // tölvupóstfang nemanda
    @FXML
    private TextField fxNemandi;    // nafn nemanda
    @FXML
    private ComboBox<Deild> fxDeild;    // deild
    @FXML
    private ComboBox<Svid> fxSvid;      // svið
    @FXML
    private ComboBox<Namsleid> fxNamsleid;  // námsleið

    // aðrar tilviksbreytur
    private Dialog<Nemandi> dialogNemandi;  // dialogur fyrir að skrá nemanda
    private ObservableList<Namsleid>[][] allarNamsleidir; // námsleiðir deilda
    private BooleanBinding loglegurPostur; // binding til að sjá hvort póstur er ólöglegur

    /**
     * Controller frumstilltur. Námsleiðir lesnar inn, dialog búinn til og villureglur settar upp
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lesaInnNamsleidir();                    // lesa inn námsleiðir

        dialogNemandi = buaTilDialog();         // bý til dialog sem er endurnotaður. Hönnunarákvörðun
        // hefði verið hægt að búa til nýjan dialog í hvert skipti
        // sem dialog er opnaður
        // Regla og breytingastjóri fyrir löglegan tölvupóst
        loglegurPostur = loglegurPostur();

        // Regla sett um hvenær Í lagi hnappur er virkur
        iLagiOvirkurRegla(dialogNemandi.getDialogPane(), dialogNemandi.getDialogPane().getButtonTypes().get(0));
        // Breytingastjóri sett fyrir löglegt nafn
        loglegtNafn();
    }

    /**
     * Lesa inn námsleiðir deildar úr skrá og setja í allarNamsleidir
     */
    private void lesaInnNamsleidir() {
        Namsleidskra skra = new Namsleidskra();     // Til að lesa inn allar námsleiðir
        allarNamsleidir = new ObservableList[SVID.length][DEILDIR.length];
        for (int i = 0; i < DEILDIR.length; i++) {
            for (int j = 0; j < DEILDIR[0].length; j++) {
                allarNamsleidir[i][j] = skra.lesa(GOGN + DEILDIR[i][j].name() + ENDING);
            }
        }
    }

    /**
     * Setur upp breytingastjóra fyrir hvað gerist þegar nafn breytist
     * ATH - hér er ekki búið til binding fyrir löglegt nafn en það mætti gera
     */
    private void loglegtNafn() {
        // tengjum breytingastjóra við fxNemandi til að ákveða hvað á að gera þegar það fær nýtt gildi
        // hér hefði líka mátt gera StringBinding í stað breytingastjóra
        fxNemandi.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                String s = (String) t1;
                if (s.length() > 0 && !Character.isUpperCase(s.charAt(0))) {
                    fxVillaNafn.setText(VILLUBOD_NAFN);
                    fxNemandi.requestFocus();
                } else
                    fxVillaNafn.setText("");
            }
        });
    }


    /**
     * Í lagi hnappurinn iLagi er óvirkur nema komin séu gagnagildi fyrir öll svið í dialog
     *
     * @param p     dialogurinn
     * @param iLagi hnappurinn
     */
    private void iLagiOvirkurRegla(DialogPane p, ButtonType iLagi) {
        final Node stadfestingHnappur = p.lookupButton(iLagi);
        stadfestingHnappur.disableProperty()
                .bind(fxNemandi.textProperty().isEmpty()    // nafn nemanda ekki tómt
                        .or(fxPostur.textProperty().isEmpty())          // póstur er ekki tómt
                        .or(loglegurPostur)         // póstur er löglegur
                        .or(fxSvid.getSelectionModel().selectedItemProperty().isNull()) // eitthvað valið í fxSvid
                        .or(fxDeild.getSelectionModel().selectedItemProperty().isNull()) // eitthvað valið í fxDeild
                        .or(fxNamsleid.getSelectionModel().selectedItemProperty().isNull()) // eitthvað valið í fxNamsleid
                );
    }

    /**
     * Setur upp binding sem segir til um hvort póstsvið er ólöglegt. Setur einnig breytingastjóra á binding
     * sem stýrir villuskilaboðum
     *
     * @return binding
     */
    private BooleanBinding loglegurPostur() {
        BooleanBinding binding = Bindings.createBooleanBinding(
                () -> (new SimpleBooleanProperty(fxPostur.getText().contains("@")).not().get()
                ), // fyrri parameterinn er lambda fallið sem er keyrt til að fá út gildið á boolean
                fxPostur.textProperty());         //  seinni parameterinn er breytan sem er fylgst með (observable)

        binding.addListener(new ChangeListener<Boolean>() { // Tengjum breytingastjóra við binding til að ákveða hvað á að
            // gera þegar gildið á binding breytist
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (t1) {                               // ef nýja gildið er ósatt
                    fxVillaPostur.setText(VILLUBOD_POSTUR);
                    fxPostur.requestFocus();
                } else                                    // ef nýja gildið er satt þá er allt í lagi
                    fxVillaPostur.setText(" ");
            }
        });
        return binding;
    }

    /**
     * Opnar dialog fyrir nemanda og fær niðurstöðu
     *
     * @return Nemandi ef notandi hefur valið í lagi, annars null
     */
    public Nemandi nyrNemandi() {
        // opna gluggann og fá niðurstöðu
        Optional<Nemandi> utkoma = dialogNemandi.showAndWait(); // Niðurstaðan er af taginu Nemandi og resultConverter notaður
        frumstilla();
        return utkoma.orElse(null);
    }

    /**
     * Frumstillir dialog sem tóman
     */
    private void frumstilla() {
        fxNemandi.setText("");
        fxPostur.setText("");
        fxDeild.getSelectionModel().clearSelection();
        fxSvid.getSelectionModel().clearSelection();
        fxNamsleid.getSelectionModel().clearSelection();
        fxVillaNafn.setText("");
    }

    /**
     * Frumstillir dialog með nemanda nem
     *
     * @param nem gögn nemanda
     */
    private void frumstilla(Nemandi nem) {
        fxNemandi.setText(nem.getNafn());
        fxPostur.setText(nem.getTolvupostfang());
        fxSvid.getSelectionModel().select(nem.getSvid());
        fxDeild.getSelectionModel().select(nem.getDeild());
        fxNamsleid.getSelectionModel().select(nem.getNamsleid());
    }


    private Dialog<Nemandi> buaTilDialog() {
        // Innihald dialogs búið til
        DialogPane p = new DialogPane();
        fxPane.setVisible(true);

        // Innihald dialogs sett sem fxPane
        p.setContent(fxPane);

        // Umgjörðin búin til - Dialog sem skilar gildi (hlut) af taginu (klasanum) ButtonType
        Dialog<Nemandi> d = new Dialog<>();
        // Ákveður hvernig gögn í dialog er breytt í venjulegan java hlut
        setConverter(d);
        // og innihaldið sett í umgjörðina
        d.setDialogPane(p);
        // Hnappar búnir til
        hnapparILagiHaettaVid(d);
        // textar fyrir haus settir í dialog d
        haus(d);
        // setja gögn í comboboxin
        setjaComboBox();

        return d;
    }

    /**
     * setur haus á dialog
     */
    private void haus(Dialog<Nemandi> d) {
        d.setHeaderText(HAUS);
        d.setTitle(TITILL);
    }

    /**
     * setur gögn í öll comboboxin. Upphafsstillt sem fyrsta svið, deildir þess og námsleiðir þess
     */
    private void setjaComboBox() {
        fxSvid.getItems().addAll(SVID);
        fxDeild.getItems().addAll(DEILDIR[0]);
        fxNamsleid.getItems().addAll(allarNamsleidir[0][0]);
    }

    /**
     * Umbreytir gögnum í dialog í Nemandi
     *
     * @param d dialog
     */
    public void setConverter(Dialog<Nemandi> d) {
        d.setResultConverter(b -> {                                 // b er af taginu ButtonType
            if (b.getButtonData() == ButtonBar.ButtonData.OK_DONE) {    // ef ýtt á hnappinn Í lagi
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
     * Bætir við tveimur hnöppum í dialoginn
     *
     * @param d dialogur
     */
    private void hnapparILagiHaettaVid(Dialog<Nemandi> d) {
        d.getDialogPane().getButtonTypes().add(BTYPE);
        d.getDialogPane().getButtonTypes().add(HTYPE);
    }

    /**
     * Handler fyrir Svið
     *
     * @param actionEvent ónotað
     */
    public void svidHandler(ActionEvent actionEvent) {
        int ix = fxSvid.getSelectionModel().getSelectedIndex();
        if (ix == EKKERTVALID)      // ef ekkert er valið þá er fyrsta sviðið valið
            ix = 0;
        fxDeild.getItems().clear(); // þurrka út öll item
        fxDeild.getItems().addAll(DEILDIR[ix]); // bæta við deildum
    }

    /**
     * Handler fyrir deild
     *
     * @param actionEvent
     */
    public void deildHandler(ActionEvent actionEvent) {
        int ixD = fxDeild.getSelectionModel().getSelectedIndex();
        int ixS = fxSvid.getSelectionModel().getSelectedIndex();
        if (ixD == EKKERTVALID) {
            ixD = 0;
        }
        fxNamsleid.setItems(allarNamsleidir[ixS][ixD]);
    }

    /**
     * Opnar dialog til að breyta nemanda nem. Setur breyttar upplýsingar í nem ef
     * breytingar urðu.
     *
     * @param nem nemandi
     * @return skilar null ef engin breyting varð annars nem
     */
    public boolean breytaNemandi(Nemandi nem) {
        frumstilla(nem);
        Optional<Nemandi> utkoma = dialogNemandi.showAndWait();
        frumstilla();
        Nemandi n = utkoma.orElse(null);

        // setja breyttar upplýsingar í nemanda - athuga að búa ekki til nýjan nemanda heldur breyta nem
        boolean breyting = false;
        if (n != null) {    // Ef einhverjar breytingar voru gerðar - þ.e. ýtt á Í lagi
            nem.breyta(n);
            breyting = true;
        }
        return breyting;
    }
}
