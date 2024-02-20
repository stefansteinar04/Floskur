package is.hi.ludo.utlit;

import is.hi.ludo.vinnsla.LeikmadurImp;
import is.hi.ludo.vinnsla.LudoBordImp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Aðalcontrollerinn fyrir lúdóborðið 
 * @author Ebba Þóra Hvannberg ebba@hi.is
 */
public class LudoBordController implements Initializable {

    LeikmadurImp leikmadur;        // Núverandi leikmaður
    @FXML
    TeningurController teningurController;  // tenging í teningscontroller

    private Button hnapparBord[];          // lúdóborð fyrir reitina 
    private LudoBordImp ludoBord;           // vinnsluhlutur fyrir lúdóborð
    private final String litur[] = {"red", "green"};
    private Label[] mork;                   // mörkin fyrir leikmenn
    private Button[] heimaHafnir;           // heimahafnir fyrir leikmenn 

    @FXML
    private Button b11;
    @FXML
    private Button b17;
    @FXML
    private Button b16;
    @FXML
    private Button b9;
    @FXML
    private Button b14;
    @FXML
    private Button b15;
    @FXML
    private Button b12;
    @FXML
    private Button b23;
    @FXML
    private Button b6;
    @FXML
    private Button b7;
    @FXML
    private Button b8;
    @FXML
    private Button b10;
    @FXML
    private Button b13;
    @FXML
    private Button jGH;
    @FXML
    private Label gMark;
    @FXML
    private Label rMark;
    @FXML
    private Button jRH;
    @FXML
    private Button b4;
    @FXML
    private Button b3;
    @FXML
    private Button b2;
    @FXML
    private Button b1;
    @FXML
    private Button b0;
    @FXML
    private Button b18;
    @FXML
    private Button b19;
    @FXML
    private Button b20;
    @FXML
    private Button b21;
    @FXML
    private Button b22;
    @FXML
    private Button b5;
    @FXML
    private Label hverGerir;

    private final SimpleIntegerProperty propFjoldiReitaAfram = new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty[] propIHeimahofn = new SimpleIntegerProperty[2];
    private final SimpleIntegerProperty propIxLeikmadur = new SimpleIntegerProperty(0);

    /**
     * Frumstillir controller, setur tengingar í teningscontroller, setur upp
     * vinnslu, setur upp properties fyrir heimahafnir og leikmann
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        teningurController.setLudoController(this);
        ludoBord = new LudoBordImp(2);
        propIHeimahofn[0] = ludoBord.getLeikmadur(0).getPropFjoldiIHeimahofn();
        propIHeimahofn[1] = ludoBord.getLeikmadur(1).getPropFjoldiIHeimahofn();
        leikmadur = ludoBord.getNuverandiLeikmadur();
        propIxLeikmadur.set(leikmadur.getId());
        // Viðmótshlutir settir í fylki til hægðarauka
        hnapparBord = new Button[]{
            b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16,
            b17, b18, b19, b20, b21, b22, b23
        };
        mork = new Label[]{rMark, gMark};
        heimaHafnir = new Button[]{jRH, jGH};

//  Bind og properties - hér þarf að passa að breyturnar skipti ekki um hlut
//  annars þarf að endur-binda
//  Ekki má virkja eða afvirkja handvirkt, þ.e. fyrir utan bind setninguna
        heimaHafnir[1].disableProperty().bind(propFjoldiReitaAfram.isEqualTo(6).
                and(propIHeimahofn[1].isEqualTo(1).and(propIxLeikmadur.isEqualTo(1))).not());
        heimaHafnir[0].disableProperty().bind(propFjoldiReitaAfram.isEqualTo(6).
                and(propIHeimahofn[0].isEqualTo(1).and(propIxLeikmadur.isEqualTo(0))).not());

    }

    /**
     * Handler fyrir það þegar leikmaður vill leika peði áfram um
     * propFjoldiReitaAfram. athugað hvort færsla veldur árekstri og unnið úr
     * því. Annars er peðið fært áfram
     *
     * @param event
     */
    @FXML
    private void reiturHandler(ActionEvent event) {
        Button b = (Button) event.getSource();
        afReit(b);
        int i = Integer.valueOf(b.getId());
        int afangi = ludoBord.afangi(i, propFjoldiReitaAfram.get());
        LeikmadurImp leikmadurIArekstri = ludoBord.iArekstri(leikmadur, afangi);
        vinnaUrArekstri(leikmadurIArekstri);
        int j = ludoBord.leika(leikmadur, i, propFjoldiReitaAfram.get());
        afangaReitur(j);

        // setja fjöldi reita áfram í 0 til að heimahöfn næsta leikmanns virkist ekki
        propFjoldiReitaAfram.set(0);
    }

    /**
     * Ef peð komst ekki í mark er það fært á reit j og næsti leikmaður gerir og
     * teningur virkjaður. Ef peð komst í mark er það fært til bókar og
     * leikmaður hefur unnið
     *
     * @param j -1 ef peð komst í mark en annars 0-fjöldi reita-1
     */
    private void afangaReitur(int j) {
        if (j != -1) {
            aReit(hnapparBord[j]);
            naestiGerir();
            synaHverGerir();
            // Virkja tening
            teningurController.afVirkja(false);
        } else {
            iMark();
            // Aðeins eitt peð - fyrstur í mark vinnur
            hverGerir.setText("Þú vinnur - Leik er lokið");
        }
    }

    /**
     * Ef leikmaður lendir í árekstri við leikmadurIArekstri þá er sá sendur í
     * heimahöfn
     *
     * @param leikmadurIArekstri
     */
    private void vinnaUrArekstri(LeikmadurImp leikmadurIArekstri) {
        if (leikmadurIArekstri != null) {
            leikmadurIArekstri.iHeimahofn();
            setHeimahofn(leikmadurIArekstri);
        }
    }

    /**
     * Næsti leikmaður á leik. Uppfærum property fyrir id leikmanns
     */
    protected void naestiGerir() {
        leikmadur = ludoBord.naesti();
        propIxLeikmadur.set(leikmadur.getId());
    }

    /**
     * Sýnir í notendaviðmóti hvaða leikmaður gerir
     */
    private void synaHverGerir() {
        hverGerir.setStyle("-fx-background-color:"
                + litur[leikmadur.getId()]);
    }

    /**
     * Gerir öll peð leikmanns virk
     */
    private void geraOllPedVirk() {
        int[] oll = ludoBord.getOllPed(leikmadur);
        for (int i : oll) {
            hnapparBord[i].setDisable(false);
        }
    }

    /**
     * Færir peð á reit b
     *
     * @param b reitur sem færa á peð á
     */
    private void aReit(Button b) {
        b.setStyle("-fx-background-color: " + litur[leikmadur.getId()]);
    }

    /**
     * Færir peð b af reit og gerir þann reit óvirkan
     *
     * @param b reitur sem færa á peð af
     */
    private void afReit(Button b) {
        b.setStyle("-fx-background-color: " + "#D3D3D3");
        b.setDisable(true);
    }

    /**
     * Uppfærir fjölda peða í marki í viðmóti
     */
    protected void iMark() {
        int m = leikmadur.getFjoldiIMarki();
        mork[leikmadur.getId()].setText("" + m);
    }

    /**
     * Handler fyrir heimahöfn. Notandi ýtir á hnapp til að setja peð úr
     * heimahöfn og á heimareit. Heimahöfn er gerð óvirk. Næsti á að gera
     *
     * @param event atburður frá annarri hvorri heimahöfninni
     */
    @FXML
    private void heimaHofnHandler(ActionEvent event) {
        Button b = (Button) event.getSource();
        int l = Integer.valueOf(b.getId());
        LeikmadurImp leik = ludoBord.getLeikmadur(l);
        int a = ludoBord.aHeimareit(leik);

        leik.urHeimahofn();
        propIHeimahofn[leik.getId()].set(leik.getFjoldiIHeimahofn());

        aReit(hnapparBord[a]);
        setHeimahofn(leik);

        // setja fjöldi reita áfram í 0 til að heimahöfn næsta leikmanns virkist ekki
        propFjoldiReitaAfram.set(0);

        // Notum bind í staðinn
        // Afvirkja heimahöfnina 
        //       b.setDisable (true);
        // Næsti á að gera og teningurinn er virkjaður
        naestiGerir();
        synaHverGerir();
        teningurController.afVirkja(false);
    }

    /**
     * Teningi hefur verið kastað og fjöldi reita áfram er i. Ef hægt er að
     * virkja heimahöfn er það gert Annars ef ekkert peð er á borði fær næsti
     * leikmaður að gera. Annars eru öll peð leikmanns gerð virk
     *
     * @param i fjöldi reita
     */
    void setFjReitaAfram(int i) {
        propFjoldiReitaAfram.set(i);
        if (!virkjaHeimaHofn(i) && leikmadur.getABordi() == 0) {
            naestiGerir();
            synaHverGerir();
            teningurController.afVirkja(false);
        } else {
            geraOllPedVirk();
        }
    }

    /**
     * Uppfærir fjolda í heimahöfn leikmanns leikmadur
     *
     * @param leikmadur
     */
    private void setHeimahofn(LeikmadurImp leikmadur) {
        heimaHafnir[leikmadur.getId()].setText(String.valueOf(leikmadur.getFjoldiIHeimahofn()));
    }

    /**
     * Athugað hvort hægt er að virkja heimahöfn miðað við teningakast i. Skilar
     * true ef teningakast er 6 og eitthvert peð er í heimahöfn.
     *
     * @param i gildi á teningi
     * @return true ef virkja á heimahöfn annars false
     */
    private boolean virkjaHeimaHofn(int i) {
        if (i == 6 && leikmadur.getFjoldiIHeimahofn() > 0) {
            // Notum bind í staðinn 
            //          heimaHafnir[leikmadur.getId()].setDisable(false);
            return true;
        }
        return false;
    }
}
