/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Controller klai fyrir aðalgluggann. Stýring fyrir valmyndir (menu)
 *
 *
 *****************************************************************************/
package vidmot;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import vinnsla.Nemandi;
import vinnsla.Nemendaskra;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdalController implements Initializable {



    // Fastar - höfum þá private þannig að við vitum ef einhver klasi notar þá og verður háður þeim

    private static final String GOGN_NEMENDUR = "gogn/Nemendur.txt";
    private static final String ADVORUNLESA = "Þú hefur gert breytingar - viltu lesa nemendaskrá?";
    private static final String VILTU_EYDA = "Viltu eyða ";
    private static final String EYDA = "Eyða";
    private static final String NAFNFORRITS = "S3";
    private static final String HAETTA = "Hætta";
    private static final String VILTU_HAETTA = "Viltu hætta?";
    private static final String LEITA = "Leita";
    private static final String FANNST_EKKI = " fannst ekki";
    private static final String NAFN_NEMANDA = "Nafn nemanda";
    private static final String UTTAKSSKRA = "gogn/NemendurUttak.txt" ;

    @FXML
    private ListView<Nemandi> fxNemendalisti;           // Viðmótshlutur fyrir nemendalista

    private NyrNemandiController nyrNemandiController;  // tenging yfir í controller til að endurnýta dialog
    private SkodaNemandiController skodaNemandiController; // tenging yfir í skoðacontroller

    private final Nemendaskra skra = new Nemendaskra();   // tenging í vinnsluna sem les og skrifar nemendagögnin

    private boolean breyttSkra = false; // segir til um hvort breytingar hafa orðið á gögnum síðan skrá var lesin

    /**
     * Frumstillir controller. Tveir dialogar eru hlaðnir inn og fengnir controllerar fyrir þá.
     *
     * @param url            ónotað
     * @param resourceBundle ónotað
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // hlaða inn dialoga úr .fxml skrá og geya controllera fyrir þá
        try {
            nyrNemandiController = hladaDialog();
            skodaNemandiController = hladaSkoda();
        } catch (IOException e) {       // bregðast við villu
            e.printStackTrace();        // prentað út "dump"
        }
    }

    /**
     * Hleður inn dialog til  að búa til eða breyta Nemanda
     *
     * @return controller fyrir dialoginn
     * @throws java.io.IOException // ef fxml skráin finnst ekki
     */
    private NyrNemandiController hladaDialog() throws java.io.IOException {
        FXMLLoader dLoader = new FXMLLoader(getClass().getResource("Nemandi.fxml"));
        dLoader.load();
        return dLoader.getController();
    }

    /**
     * Hleður inn dialog fyrir að skoða Nemanda
     *
     * @return controller fyrir dialoginn
     * @throws java.io.IOException // ef fxml skráin finnst ekki
     */
    private SkodaNemandiController hladaSkoda() throws java.io.IOException {
        FXMLLoader dLoader = new FXMLLoader(getClass().getResource("SkodaNemandi.fxml"));
        dLoader.load();
        return dLoader.getController();
    }

    /**
     * Tengir nemendalista við lista viðmótshlutinn
     *
     * @param l nemendalisti
     */
    public void setListi(ObservableList<Nemandi> l) {
        fxNemendalisti.setItems(l);
    }

    /**
     * Skilar lista af nemendum
     *
     * @return nemendalisti
     */
    public ObservableList<Nemandi> getListi() {
        return fxNemendalisti.getItems();
    }

    /**
     * Skilar nemanda sem er valinn
     *
     * @return valinn nemandi
     */
    public Nemandi getNuverandi() {
        return fxNemendalisti.getSelectionModel().getSelectedItem();
    }

    /**
     * Bætir við nemanda á nemendalista
     *
     * @param n nemandi sem á að bæta við
     */
    public void baetaVidNemanda(Nemandi n) {
        fxNemendalisti.getItems().add(n);
    }


    /**
     * Eyðir nemanda af nemendalista
     *
     * @param nem nemandinn sem á að eyða
     */
    public void eyda(Nemandi nem) {
        fxNemendalisti.getItems().remove(nem);
    }


    /**
     * Atburðahandler fyrir að lesa inn nemendur úr skrá ef notandi hefur staðfest
     * Gögn eru merkt sem óbreytt. Fyrsti nemandi er valinn
     *
     * @param actionEvent ónotað
     */
    public void lesaHandler(ActionEvent actionEvent) {
        if (!breyttSkra || erStadfest("Lesa", ADVORUNLESA)) {
            setListi(skra.lesa(GOGN_NEMENDUR));
            fxNemendalisti.getSelectionModel().select(0);
            breyttSkra = false;
        }
    }

    /**
     * Atburðahandler fyrir breyta í valmynd
     * Breyta upplýsingum um nemanda ef einhver er valinn og nemanda hefur verið breytt
     * Gögn eru merkt sem breytt
     *
     * @param actionEvent ónotað
     */
    public void breytaHandler(ActionEvent actionEvent) {
        Nemandi nem = getNuverandi();
        if (nem != null && nyrNemandiController.breytaNemandi(nem)) {
            breyttSkra = true;
            fxNemendalisti.refresh();   // þarf að refresha listann
        }
    }

    /**
     * Atburðahandler fyrir eyða í valmynd
     * Eyða núverandi nemanda á listanum ef notandi staðfestir
     * Gögn eru merkt sem breytt
     *
     * @param actionEvent ónotað
     */
    public void eydaHandler(ActionEvent actionEvent) {
        Nemandi nem = getNuverandi();
        if (erStadfest(EYDA, VILTU_EYDA + nem.getNafn())) {
            eyda(nem);
            breyttSkra = true;
        }
    }


    /**
     * Opnar staðfestingarglugga og skilar svari
     *
     * @return satt ef má framkvæma aðgerð
     */
    private boolean erStadfest(String haus, String spurning) {
        AdvorunDialog a = new AdvorunDialog(NAFNFORRITS, haus, spurning);
        Optional<ButtonType> svar = a.showAndWait();
        return svar.isPresent() && svar.get().getButtonData() == ButtonBar.ButtonData.OK_DONE;   // Ef svarið er ok
    }


    /**
     * Atburðahandler fyrir að skoða
     * Birta gögn fyrir valinn nemanda í glugga
     *
     * @param actionEvent ónotað
     */
    public void skodaHandler(ActionEvent actionEvent) {
        Nemandi nem = getNuverandi();
        if (nem != null)
            skodaNemandiController.birtaGlugga(nem);
    }

    /**
     * Atburðahandler fyrir að skrifa
     * Skrifa nemendalista í skrá ef einhverjar breytingar hafa orðið
     * Gögn merkt sem óbreytt
     *
     * @param actionEvent ónotað
     */
    public void skrifaHandler(ActionEvent actionEvent) {

        if (breyttSkra)
            skra.skrifa(getListi(), UTTAKSSKRA);
        breyttSkra = false;
    }

    /**
     * Atburðahandler fyrir að búa til nýjan nemanda
     *
     * @param actionEvent ónotað
     */
    public void nyrNemandiHandler(ActionEvent actionEvent) {
        Nemandi n = nyrNemandiController.nyrNemandi();
        if (n != null) {
            baetaVidNemanda(n);
            breyttSkra = true;
        }
    }

    /**
     * Leita að nemanda. Upp kemur dialog og notandi er beðinn um að setja inn nafn
     *
     * @param actionEvent ónotað
     */
    public void leitaHandler(ActionEvent actionEvent) {
        LeitDialog lDialog = new LeitDialog(NAFNFORRITS, LEITA, NAFN_NEMANDA);
        Optional<String> utkoma = lDialog.showAndWait();    // Dialog skilar streng
        if (utkoma.isPresent()) {
            fxNemendalisti.getSelectionModel().select(leita(utkoma.get())); //Leita að nemanda og velja hann á listanum
        }
    }

    /**
     * Leita að nemanda með nafni s á lista. Ef nemandi finnst þá er nemanda skilað.
     * Annars er birtur Aðvörunargluggi og skilað núlli
     *
     * @param s nafn nemanda sem á að leita að
     * @return nemandi ef hann finnst, annars null
     */
    private Nemandi leita(String s) {
        ObservableList<Nemandi> items = fxNemendalisti.getItems();
        for (Nemandi n : items) {               // ítra yfir listann af nemendum
            if (n.getNafn().equals(s))          // bera saman við nafnið
                return n;
        }
        erStadfest(LEITA, s + FANNST_EKKI);  // skilagildi ónotað
        return null;
    }

    /**
     * Handler fyrir hætta valmyndarstak. Notandi er beðinn um að staðfesta að hann vilji hætta.
     * Hættir í forritinu ef staðfest
     *
     * @param actionEvent ónotað
     */
    public void haettaHandler(ActionEvent actionEvent) {
        if (erStadfest(HAETTA, VILTU_HAETTA)) {
            Platform.exit();
            System.exit(0);
        }
    }
}

