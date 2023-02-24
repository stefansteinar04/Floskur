package is.vidmot;

import is.vinnsla.Reiknivel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Controller / stýriklasi fyrir reiknivélina. Hefur handlera fyrir
 *  tölur, virkja, jafnt og og hreinsa aðgerðina. Hefur tengingu við vinnsluklasa
 *  og tilviksbreytu með einum viðmótshlut, þ.e. úttaksglugga reiknivélar
 *
 *
 *****************************************************************************/

public class ReiknivelController {

    // Viðmótsbreytur
    @FXML
    private TextField fxUttak;

    // Tengsl við vinnslu
    private Reiknivel reiknivel;


    /**
     * Frumstillir controller-inn -
     */
    public void initialize() {
        reiknivel = new Reiknivel();
    }

    /**
     * Handler fyrir töluhnappana í notendaviðmótinu. Nær í töluna, uppfærir vinnsluna og síðan
     * uppfærir notendaviðmót
     * @param actionEvent atburðurinn sem viðmótshluturinn fékk
     */
    public void inntakTalaActionPerformed(ActionEvent actionEvent) {
        // dæmigerð uppbygging handlera
        // Ná í gögn úr viðmóti
        int tala = Integer.parseInt(((Node)actionEvent.getSource()).getId());
        // Vinnslan er uppfærð og e.t.v. fengið svar til baka
        reiknivel.setTala(tala);
        // Uppfæra stöðu notendaviðmóts
        fxUttak.setText(fxUttak.getText()+tala);
    }

    /**
     *  Handler fyrir C hnappinn. Núllstillir vinnsluna og gerir úttaksgluggann auðan
     * @param actionEvent atburðurinn sem viðmótshluturinn fékk
     */
    public void hreinsaHandler(ActionEvent actionEvent) {
        reiknivel.hreinsa();
        fxUttak.setText("");
    }

    /**
     * Handler fyrir jafnt og aðgerðina í notendaviðmótinu. Fær niðurstöðu reikninga frá vinnslu og
     * birtir í úttaksglugga
     * @param actionEvent atburðurinn sem viðmótshluturinn fékk
     */
    public void jafntogHandler(ActionEvent actionEvent) {
        fxUttak.setText(reiknivel.jafntOg()+"");
    }

    /**
     * Handler fyrir alla virkja hnappana, +,-,*,/ Lætur vinnsluna vita um virkjann
     * og uppfærir úttaksgluggann
     * @param actionEvent atburðurinn sem viðmótshluturinn fékk
     */
    public void virkiHandler(ActionEvent actionEvent) {
        // virkiNr er 10, 11, 12, 13
        int virkiNr = Integer.parseInt(((Node)actionEvent.getSource()).getId());
        reiknivel.setVirki(virkiNr-10);
        fxUttak.setText(fxUttak.getText()+ ((Button)actionEvent.getSource()).getText());
    }
}