package is.pipemania.vidmot;

import is.pipemania.vinnsla.Pipa;
import is.pipemania.vinnsla.Pipemania;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.util.Objects;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Controller fyrir pipemania verkefnið. Stýrir
 *
 *
 *****************************************************************************/
public class PipemaniaController {

    // fastar
    private final static int S = 5;
    private static final String LINDIN = "fyrsti";
    private static final String FLAEDIR = "flaedir";
    public static final String BUTTON = "button";
    public static final String POSITION = "position";

    // tilviksbreytur í notendaviðmóti
    @FXML
    private Label fxStig;
    @FXML
    private GridPane fxButar;


    // tengsl við vinnsluna
    Pipemania mania;

    //

    private Button lindHnappur; // fyrsta hnit í pípulögninni
    private Button nyjastiHnappur;

    public PipemaniaController() {
        // Búa til vinnsluna
        mania = new Pipemania(S);
    }

    /**
     * Frumstillir viðmótshlutinn.
     */
    public void initialize() {

        ObservableList<Pipa> q = mania.getPipukista();
        birtaMyndir(q);
        // Sjá um að listinn af nýju pípunum uppfærist
        q.addListener((ListChangeListener<Pipa>) change -> {
            birtaMyndir(mania.getPipukista()); // birta myndirnar
        });

        // uppfæra listenera (lambda fallið) þegar nýrri pípu er bætt við á grid-ið
        mania.nuverandiPipaProperty().addListener((observable, oldValue, newValue) -> {

            // skipta um styleclass
            uppfaeraStilHnappur(newValue, nyjastiHnappur);
            merkjaLindarHnapp(oldValue);    // merkja lindarhnappinn sérstaklega

            reglaGraennHnappur(nyjastiHnappur, newValue); // hnappur gerður grænn ef pípa er opin

        }); // endar  mania.nuverandiPipaProperty().addListener

        // Stigin eru uppfærð í notendaviðmóti þegar þau breytast í vinnslunni
        fxStig.textProperty().bind(mania.stigProperty().asString());
    }

    private void merkjaLindarHnapp(Pipa oldValue) {
        if (oldValue == null || Objects.equals(GridPane.getRowIndex(nyjastiHnappur), GridPane.getRowIndex(lindHnappur)) &&
                Objects.equals(GridPane.getColumnIndex(nyjastiHnappur), GridPane.getColumnIndex(lindHnappur))) {// fyrsti reiturinn
            nyjastiHnappur.getStyleClass().add(LINDIN);
            lindHnappur = oldValue == null ? nyjastiHnappur : lindHnappur;
        }
    }

    /**
     * Sett upp regla að hnappur verði grænn þegar pípa er opin
     *
     * @param b hnappurinn sem ýtt var á
     * @param p pípan sem er á hnappnum
     */
    private void reglaGraennHnappur(Button b, Pipa p) {
        p.opinProperty().addListener((obs, o, n) -> {
            if (n)
                b.getStyleClass().add(FLAEDIR);
        });
    }

    /**
     * Uppfærir stíl á viðmótshlut (hnappi) þannig að hann sé í samræmi við p
     *
     * @param p       pípan sem hefur áhrif á stílinn
     * @param hnappur hnappurinn sem þarf að uppfæra stílinn á
     */
    private void uppfaeraStilHnappur(Pipa p, Button hnappur) {
        hnappur.getStyleClass().clear();
        hnappur.getStyleClass().add(BUTTON);
        hnappur.getStyleClass().add(POSITION);
        hnappur.getStyleClass().add(getStilKlasi(p));
    }

    /**
     * Handler fyrir að velja reit á leikborði
     *
     * @param actionEvent atburðurinn
     */
    @FXML
    protected void fxVeljaReit(ActionEvent actionEvent) {
        // ná í hnappinn
        nyjastiHnappur = (Button) actionEvent.getSource();
        // ná í staðsetninguna
        int j = GridPane.getColumnIndex(nyjastiHnappur);
        int i = GridPane.getRowIndex(nyjastiHnappur);
        // finna næstu pípu úr kistunni og setja á leikborðið
        mania.setNaestaPipa(i, j);
    }

    /**
     * Handler fyrir að ákveða lengd flæðis í pípulögn
     *
     * @param actionEvent
     */
    @FXML
    private void fxAthFlaedir(ActionEvent actionEvent) {
        mania.flaedir();
    }

    /**
     * Birtir myndir í pípukistu
     *
     * @param q
     */
    private void birtaMyndir(ObservableList<Pipa> q) {
        int i = 3;
        for (Pipa p : q) {
            fxButar.getChildren().get(i).getStyleClass().clear();
            fxButar.getChildren().get(i).getStyleClass().add(POSITION);
            fxButar.getChildren().get(i--).getStyleClass().add(getStilKlasiKista(p));
        }
    }

    private String getStilKlasiKista(Pipa p) {
        return "k" + getStilKlasi(p);
    }

    /**
     * Nær í viðeigandi style class fyrir píputegundina
     *
     * @param p pípan
     * @return nafnið á styleclass
     */
    private String getStilKlasi(Pipa p) {
        return p.getInn().name() + p.getUt().name();
    }


}