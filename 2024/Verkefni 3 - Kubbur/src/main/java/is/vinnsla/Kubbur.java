package is.vinnsla;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import java.util.Random;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *  Vðmótsforritun 2024
 *
 *  Lýsing  : Vinnsluklasi fyrir Kubbur
 *  Hefur random borð af stykkjum
 *  heldur utan um stig í leik og hvort honum er lokið eða í gangi
 *
 *****************************************************************************/


public class Kubbur {

    // fastar
    public static final int RADIR = 3;
    public static final int DALKAR = 3;


    // stigin í leiknum
    private final SimpleStringProperty stigProperty = new SimpleStringProperty();

    // fjöldi reita sem eru rétt giskaðir
    private final IntegerProperty fjoldiReitaProperty = new SimpleIntegerProperty(0);


    // nöfn á myndunum - notað fyrir StyleClass
    private final String[][] mynd = {{"nullnull", "nulleinn", "nulltveir"},
            {"einnnull", "einneinn", "einntveir"},
            {"tveirnull", "tveireinn", "tveirtveir"}};

    // random hlutur til að rugla stykkjunum
    private final Random random = new Random();

    // Platan með random stykkjum
    private Stykki[][] kubburBord;

    // Fylki í stykkjunum í röð til að hægt sé að fletta upp í þeim
    private  Stykki[] kubburRod;

    
    /**
     * Smiður fyrir kubb með radir og dalkar
     * Býr til stykkin, kubbaborðið og setur stigin 0
     *
     * @param radir  fjöldi raða
     * @param dalkar fjöldi dálka
     */
    public Kubbur(int radir, int dalkar) {
        // búa til stykkja og setja á random borð og í röð í fylki
        frumstillaKubb(radir, dalkar);
        // engin stig í upphafi
        stigProperty.setValue("0");
    }

    /**
     * Búin til stykki og sett á kubbaborðið í slembiröð og í fylki kubburRod sem er í röð
     *
     * @param radir  fjöldi raða
     * @param dalkar fjöldi dálka
     */
    private void frumstillaKubb(int radir, int dalkar) {
        // búa til tvívítt fylki radir x dalkar
        kubburBord = new Stykki[radir][dalkar];
        // búa til einvítt fylki af stærð radir*dalkar
        kubburRod = new Stykki[radir*dalkar+1];

        // búa til stykkin og setja á random borðið og í röð í fylki
        for (int i = 0; i < radir; i++) {
            for (int j = 0; j < dalkar; j++) {
                // búa til stykki
                Stykki s = new Stykki(mynd[i][j]);
                // setja stykkið á random borðið
                setjaRandom(s);
                // setja stykkið í röð í fylki
                kubburRod[s.getTala()] = s;
            }
        }
    }

    /**
     * Setur stykki á slembinn hátt á random borðið
     *
     * @param stykki stykki sem á að setja
     */
    private void setjaRandom(Stykki stykki) {
        boolean fundinn = false;
        while (!fundinn) {
            int i = random.nextInt(DALKAR); // random röðin
            int j = random.nextInt(RADIR); // random dálkurinn
            if (kubburBord[i][j] == null) { // ef sellan er auð er stykkið sett í selluna
                kubburBord[i][j] = stykki;
                fundinn = true;
            }
        }
    }

    // get aðferðir fyrir stigin og fjölda reita
    public ObservableValue<String> stigProperty() {
        return stigProperty;
    }


    public  IntegerProperty fjoldiReitaProperty() {
        return fjoldiReitaProperty;
    }

    /**
     * Nær í stykkið af einvíða fylkinu
     * @param tala talan sem notandi velur sér
     * @return rétta stykkið
     */
    public Stykki getRadadStykki(int tala) {
        return kubburRod[tala];
    }

    /**
     * Setja stykki á borðið og athuga hvort það er á réttum stað. Ef svo er hækkar stigin um einn
     * og fjöldi reita hækkar um einn
     *
     * @param i      röð á borðinu
     * @param j      dálkur á borðinu
     * @param stykki stykkið sem er sett á borðið
     * @return true notandi giskaði rétt annars false
     */
    public boolean setjaStykki(int i, int j, Stykki stykki) {
        // athuga hvort stykkið er á réttum stað
        boolean rett = kubburBord[i][j].equals(stykki);

        // uppfæra stigin, plús eitt stig fyrir að geta fundið réttan reit fyrir stykkið
        uppfaeraStig(rett);
        uppfaeraFjoldaReita (rett);

        return rett;
    }

    /**
     * Hækka fjölda reita rétt giskaðra reita um einn
     * @param rett er giskað rétt
     */
    private void uppfaeraFjoldaReita(boolean rett) {
        fjoldiReitaProperty.setValue(rett ? fjoldiReitaProperty.get() + 1 : fjoldiReitaProperty.get());
    }

    /**
     * Uppfæra stigin
     * @param rett er giskað rétt
     */
    private void uppfaeraStig(boolean rett) {
        stigProperty.setValue(rett ? String.valueOf((1 + Integer.parseInt(stigProperty.getValue()))) :
                stigProperty.getValue());
           }

    /**
     * nær í stykki á random borðinu
     *
     * @param i röðin
     * @param j dálkurinn
     * @return stykkinu
     */

    public Stykki getBordStykki(int i, int j) {
        return kubburBord[i][j];
    }

    /**
     * Nær í fjölda rétt giskaðra reita
     * @return heiltala
     */
    public int  getFjoldiReita() {
        return fjoldiReitaProperty.getValue();
    }
}
