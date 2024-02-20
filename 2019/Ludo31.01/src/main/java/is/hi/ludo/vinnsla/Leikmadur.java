
package is.hi.ludo.vinnsla;

/**
 * Leikmaður hefur auðkenni (id), veit hve mörg peð eru í heimahöfn, hve mörg peð
 * eru í marki. Leikmaður veit hver teljari er fyrir eina peðið sem hann á. 
 * Leikmaður veit hver upphafsreitur er á lúdóborði og lokareitur.
 * @author Ebba Þóra Hvannberg ebba@hi.is 

 * Háskóli Íslands
 */
public interface Leikmadur {

    /**
     * Færir eina peð leikmanns áfram um fjReitaAfram
     * @param fjReitaAfram
     */
    void afram(int fjReitaAfram);

    /**
     * Fjöldi peða á borði
     * @return
     */
    int getABordi();

      // Getters og setters
    
    int getFjoldiIHeimahofn();

    int getFjoldiIMarki();

  
    int getId();

    int getUpphafsReitur();
    
    void setFjoldiIHeimahofn(int fjoldiIHeimahofn);

    void setFjoldiIMarki(int fjoldiIMarki);

    /**
     * Hækkar fjölda peða í heimahöfn um einn
     */
    void iHeimahofn();

    /**
     * Hækkar fjölda peða í marki um einn
     * @return skilar fjölda peða í marki
     */
    int iMark();

    /**
     * Segir til um hvort eina peð leikmanns er komið í mark
     * @return true ef eina peð leikmanns er komið í mark annars false
     */
    boolean kominnIMark();

    

    /**
     * Setur peð úr heimahöfn og á reit 0 ef eitthvert peð er eftir
     * Núllstillir núverandi reit peðs
     * @return -1 ef ekkert peð er í heimahöfn annars fjölda í heimahöfn
     * eftir að peð hefur verið fært úr heimahöfn. Núverandi reitur eina peðs
     * leikmanns er upphafsreitur.
     */
    int urHeimahofn();
    
}
