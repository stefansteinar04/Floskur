/*
Ebba Þóra Hvannberg ebba@hi.is
 */
package is.hi.ludo.vinnsla;

/**
 * Vinnsluklasi fyrir lúdó borð. Inniheldur leikmenn, núverandi leikmann
 * og lúdó borð með 24 reitum. Lúdóborð veit hvaða peð eru á borðinu. Klasi sér um að 
 * leika peði fram um fjölda reita og athuga hvort peð er komið í mark; 
 * athuga hvort hefur orðið árekstur; setja peð á upphafsreit; ná í öll peð
 * leikmanns. 
 * 
 * @author Ebba Þóra Hvannberg ebba@hi.is
 */
public interface LudoBord {

    /**
     * Leikmaður leikmadur er settur á heimareit
     *
     * @param leikmadur
     * @return skilar upphafsreit leikmanns
     */
    int aHeimareit(LeikmadurImp leikmadur);

    /**
     * Skilar áfangareit sem er fenginn er með því að leggja saman i og j
     * og taka módulus 24
     * @param i
     * @param j
     * @return áfangareit
     */
    int afangi(int i, int j);

    /**
     * Nær í leikmann l
     *
     * @param l
     * @return leikmaður l
     */
    LeikmadurImp getLeikmadur(int l);

    LeikmadurImp getNuverandiLeikmadur();

    /**
     * Nær í indexa á öll peð leikmanns
     *
     * @param leikmadur
     * @return fylki með indexum á öll peð leikmanns
     */
    int[] getOllPed(LeikmadurImp leikmadur);

    /**
     * Skilar leikmanni ef peð lendir í árekstri við  peð af öðrum lit
     *
     * @param leikmadur
     * @param nrReits
     * @return leikmaðurinn sem á peðið sem er rekist á annars null
     */
    LeikmadurImp iArekstri(LeikmadurImp leikmadur, int nrReits);

    /**
     * Leikur peði leikmanns leikmadur frá indexABordi fjReitaAfram og skilar
     * áfangareit á borði. Skilar -1 ef leikmaður er kominn í mark. Forskilyrði
     * er að það sé ekki leikmaður fyrir á borði
     *
     * @param leikmadur leikmaður sem er að gera
     * @param indexABordi núverandi index á borði
     * @param fjReitaAfram fjölda reita áfram sem á að fara
     * @return -1 ef leikmaður er kominn í mark en annars áfangareit á borði
     */
    int leika(LeikmadurImp leikmadur, int indexABordi, int fjReitaAfram);

    /**
     * Skilar leikmanni sem á að gera næst. Notaður er index frá 0-1 sem vísar á
     * fylki sem inniheldur báða leikmenn. Byrjað er á rauðum og svo koll af
     * kolli
     *
     * @return leikmaður sem á að gera næst
     */
    LeikmadurImp naesti();
    
}
