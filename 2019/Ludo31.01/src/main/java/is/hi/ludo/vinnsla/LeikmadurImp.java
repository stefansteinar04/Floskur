
package is.hi.ludo.vinnsla;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Leikmaður hefur auðkenni (id), veit hve mörg peð eru í heimahöfn, hve mörg
 * peð eru í marki. Leikmaður veit hver teljari er fyrir eina peðið sem hann á.
 * Leikmaður veit hver upphafsreitur er á lúdóborði og lokareitur.
 *
 * @author Ebba Þóra Hvannberg ebba@hi.is  
 * Háskóli Íslands
 */
public class LeikmadurImp implements Leikmadur {

    private final int FJOLDIPEDA = 1;    // fjöldi peða 
    private final int id;                // auðkenni leikmanns

    private int fjoldiIMarki;           // fjöldi peða sem eru komin í mark
    private int nuverandiReitur;        // afstæður reitur á borði 0-23

    private final int upphafsReitur;    // uppphafsreitur á lúdóborði 
    private final int lokaReitur;       // lokareitur (23 fyrir 2 leikmenn)

    // Properties
    private final SimpleIntegerProperty propId;
    private final SimpleIntegerProperty propFjoldiIHeimahofn
            = new SimpleIntegerProperty(FJOLDIPEDA);

    /**
     * Býr til leikmann með auðkenni id, upphafsriet upphaf og fjölda reita loka
     *
     * @param id
     * @param upphaf
     * @param loka
     */
    public LeikmadurImp(int id, int upphaf, int loka) {
        this.id = id;
        propId = new SimpleIntegerProperty(id);
        upphafsReitur = upphaf;
        lokaReitur = loka;
    }

    // Getters og setters 
    @Override
    public int getId() {
        return id;
    }

    public SimpleIntegerProperty getPropId() {
        return propId;
    }

    @Override
    public int getFjoldiIHeimahofn() {
        return propFjoldiIHeimahofn.get();
    }

    @Override
    public void setFjoldiIHeimahofn(int fjoldiIHeimahofn) {
        this.propFjoldiIHeimahofn.set(fjoldiIHeimahofn);
    }

    public SimpleIntegerProperty getPropFjoldiIHeimahofn() {
        return propFjoldiIHeimahofn;
    }

    @Override
    public int getFjoldiIMarki() {
        return fjoldiIMarki;
    }

    @Override
    public void setFjoldiIMarki(int fjoldiIMarki) {
        this.fjoldiIMarki = fjoldiIMarki;
    }

    @Override
    public int getUpphafsReitur() {
        return upphafsReitur;
    }

    /**
     * Fjöldi peða á borði
     *
     * @return
     */
    @Override
    public int getABordi() {
        return FJOLDIPEDA - fjoldiIMarki - propFjoldiIHeimahofn.get();
    }

    /**
     * Setur peð úr heimahöfn og á reit 0 ef eitthvert peð er eftir Núllstillir
     * núverandi reit peðs
     *
     * @return -1 ef ekkert peð er í heimahöfn annars fjölda í heimahöfn eftir
     * að peð hefur verið fært úr heimahöfn. Núverandi reitur eina peðs
     * leikmanns er upphafsreitur.
     */
    @Override
    public int urHeimahofn() {
        if (propFjoldiIHeimahofn.get() > 0) {
            propFjoldiIHeimahofn.set(propFjoldiIHeimahofn.get() - 1);
            nuverandiReitur = 0;
            return propFjoldiIHeimahofn.get();
        } else {
            return -1;
        }
    }

    /**
     * Hækkar fjölda peða í marki um einn
     *
     * @return
     */
    @Override
    public int iMark() {
        fjoldiIMarki = fjoldiIMarki + 1;
        return fjoldiIMarki;
    }

    /**
     * Færir eina peð leikmanns áfram um fjReitaAfram
     *
     * @param fjReitaAfram
     */
    @Override
    public void afram(int fjReitaAfram) {
        nuverandiReitur = nuverandiReitur + fjReitaAfram;
    }

    /**
     * Segir til um hvort eina peð leikmanns er komið í mark
     *
     * @return true ef eina peð leikmanns er komið í mark annars false
     */
    @Override
    public boolean kominnIMark() {
        return nuverandiReitur > lokaReitur;
    }

    /**
     * Hækkar fjölda peða í heimahöfn um einn
     */
    @Override
    public void iHeimahofn() {
        propFjoldiIHeimahofn.set(propFjoldiIHeimahofn.get() + 1);
    }
}
