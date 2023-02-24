package is.vinnsla;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Vinnsluklasi fyrir reiknivél. Reiknivélin hefur reikniaðgerðir +, -
 *  * og /. Ef deilt er með núlli er útkoman 0. Tölur geta haft marga tölustafi.
 *  Það var nóg að hafa einfaldar segðir þ.e. með tveimur gildum og einum virkja
 *  3 + 4 = 7 Þó getur þessi reiknivél ráðið við lengri segðir en tekur ekki tillit
 *  til forgangsröðun aðgerðar. Þannig er 2+3*2 10 en ekki 8.
 *  Einnig getur þessi reiknivél notað útkomu segðar sem inntaksgildi í næstu segð.
 *  Það var ekki gerð krafa um þetta
 *
 *****************************************************************************/


public class Reiknivel {


    // Takið eftir að virkjar breytan er klasabreyta, eitt minnishólf fyrir öll tilvik klasans
    // er final vegna þess að það er ekki sett nýtt fylki í breytuna. Hins vegar gætu stökin
    // í fylkinu breyst - þó svo að það gerist ekki í þessu forriti
    private static final Reikna[] virkjar = { // fylki með fjórum lambdaföllum
            Integer::sum,       //innbyggt fall sem reiknar út summu tveggja heiltalna. Tvöfaldi tvípunkturinn
            // merkir að sum er tilvísun í aðferð (method reference) og það má nota nafnið til að kalla á aðferð
            (x, y) -> x - y,
            (x, y) -> x * y,
            (x, y) -> y == 0 ? 0 : x / y // skilar 0 ef deilt er með núlli, annars heiltöludeiling
    };

    // tilviksbreytur
    private int utkoma = 0; //útkoma reikninga
    private int tala = 0;   // tala í segð

    private Reikna virki = virkjar[0]; // breyta inniheldur núverandi virkja í segð


    // setur tölu í reiknivélina
    public void setTala(int tala) {
        this.tala = this.tala * 10 + tala;
    }

    // núllstillir reiknivélina
    public void hreinsa() {
        utkoma = 0;
        tala = 0;
    }

    /**
     * Þegar kemur virki þá er reiknað út úr núverandi segð og nýr virki settur
     *
     * @param virkiNr númer virkja 0,1,2,3
     */
    public void setVirki(int virkiNr) {
        utkoma = reikna();
        tala = 0;
        virki = virkjar[virkiNr];
    }


    /**
     * Reiknar út úr segðinni og núverandi virki frumstilltur sem +
     *
     * @return útkomunni úr segðinni
     */
    public int jafntOg() {
        tala = reikna();
        virki = virkjar[0];
        utkoma = 0;
        return tala;
    }

    // Best að setja private aðferðir neðst - höfum aðferðir private ef aðrir klasar þurfa ekki að kalla á þær

    /**
     * Reiknað út úr segðinni
     *
     * @return skilar útkomunni úr segðinni
     */
    private int reikna() {
        return virki.reikna(utkoma, tala); // kallað á lambda fallið fyrir núverandi virkja
    }

}
