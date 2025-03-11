package hi.verkefni.vidmot;
/* ****************************************************************************
 Nafn    : Ebba Þóra Hvannberg
 T-póstur: ebba@hi.is

 Lýsing  : Stýriklasi fyrir Bingó forritið. Hefur samskipti við vinnsluna
 Býr til nýtt bingóspjald. Hefur atburðahandler fyrir hnappana á bingó
 spjaldinu

 */

import hi.verkefni.vinnsla.Bingospjald;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class BingoController implements Initializable {

    // fastar
    private static final int LINUR = 5; // fjöldi lína
    private static final int DALKAR = 5; // fjöldi dálka á spjaldi

    // vinnsla
    private final Bingospjald bingo = new Bingospjald(LINUR, DALKAR); // bingóspjald

    // viðmótshlutir - samsvarandi breytur í .fxml skránni
    @FXML
    private Label fxBingoVinningur; // segir til hvort það er komið bingó
    @FXML
    private GridPane fxGrid;    // spjaldið


    /**
     * Frumstillir viðmótið fyrir bingóspjald með nýju bingóspjaldi sem hefur tölur af
     * handahófi frá 1 til 75
     * @param url ónotað
     * @param resourceBundle ónotað
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int [][] tolur = bingo.nyttSpjald();    // ná í random tölur úr vinnslu
        for (int i=0; i<tolur.length; i++)
            for (int j=0; j<tolur[0].length; j++)
                // vörpun gagna úr vinnslu í viðmótið
                ((Button)fxGrid.getChildren().
                        get(DALKAR*(i+1)+j)).setText(tolur[i][j]+"");
    }

    /**
     * Atburðahandler fyrir að velja reit á bingóspjaldi. Hnappur gerður óvirkur, lögun hans
     * breytt í hring. Ef komið er bingó þá er viðeigandi texti birtur
     * @param actionEvent atburðurinn frá hnappnum
     */
    public void bingoHandler(ActionEvent actionEvent) {
        Button b = ((Button)actionEvent.getSource()); // hvaða hnapp var ýtt á
        int i= GridPane.getRowIndex(b)-1;   // hvar er hnappurinn í grid-inu
        int j = GridPane.getColumnIndex(b);
        bingo.aReit (i,j);                  // merkja við á spjaldinu að talan hafi verið lesin
        Circle c = new Circle(b.getWidth()/2); // breyta lögun hnappsins og bakgrunnur
        b.setShape(c);
        b.setBackground (new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        if (bingo.erBingo())                // er komið bingó
            fxBingoVinningur.setText("B i n g o");
        b.setDisable(true);                 // hnappur gerður óvirkur
    }
}
