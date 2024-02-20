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
public class LudoBordImp implements LudoBord {

    private final LeikmadurImp[] leikmenn;         // fylki af leikmönnum
    private LeikmadurImp nuverandiLeikmadur;       // leikmaður sem á að gera
    private final LeikmadurImp bord[] = new LeikmadurImp[24]; // lúdó borðið af peðum

    /**
     * Smiður sem býr til fjLeikmanna og setur núverandi leikmann sem þann
     * leikmann sem er í fyrsta sætinu
     * @param fjLeikmanna fjöldi leikmanna sem á að búa til
     */
    public LudoBordImp(int fjLeikmanna) {
        leikmenn = new LeikmadurImp[fjLeikmanna];
        for (int i = 0; i < fjLeikmanna; i++) {
            leikmenn[i] = new LeikmadurImp(i, i * 12, 23);
        }
        nuverandiLeikmadur = leikmenn[0];
    }

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
    @Override
    public int leika(LeikmadurImp leikmadur, int indexABordi, int fjReitaAfram) {
        leikmadur.afram(fjReitaAfram);
        if (leikmadur.kominnIMark()) {
            leikmadur.iMark();
            return -1;
        } else {
            bord[indexABordi] = null;
            int afangi = afangi(indexABordi, fjReitaAfram);
            bord[afangi] = leikmadur;
            return afangi;
        }
    }

    /**
     * Skilar áfangareit sem er fenginn er með því að leggja saman i og j
     * og taka módulus 24 
     * @param i
     * @param j
     * @return áfangareit
     */
    @Override
    public int afangi(int i, int j) {
        return (i + j) % 24;
    }

    /**
     * Skilar leikmanni ef peð lendir í árekstri við  peð af öðrum lit
     *
     * @param leikmadur
     * @param nrReits
     * @return leikmaðurinn sem á peðið sem er rekist á annars null
     */
    @Override
    public LeikmadurImp iArekstri(LeikmadurImp leikmadur, int nrReits) {
        return bord[nrReits];
    }

    /**
     * Skilar leikmanni sem á að gera næst. Notaður er index frá 0-1 sem vísar á
     * fylki sem inniheldur alla leikmenn. Byrjað er á rauðum og svo koll af
     * kolli
     *
     * @return leikmaður sem á að gera næst
     */
    @Override
    public LeikmadurImp naesti() {
        int i = nuverandiLeikmadur.getId();
        i = (i + 1) % 2;
        nuverandiLeikmadur = leikmenn[i];
        return nuverandiLeikmadur;
    }

    /**
     * Leikmaður leikmadur er settur á heimareit
     *
     * @param leikmadur
     * @return skilar upphafsreit leikmanns
     */
    @Override
    public int aHeimareit(LeikmadurImp leikmadur) {
        bord[leikmadur.getUpphafsReitur()] = leikmadur;
        return leikmadur.getUpphafsReitur();
    }

    /**
     * Nær í indexa á öll peð leikmanns
     *
     * @param leikmadur
     * @return fylki með indexum á öll peð leikmanns
     */
    @Override
    public int[] getOllPed(LeikmadurImp leikmadur) {
        int[] ollPed = new int[leikmadur.getABordi()];
        int i = 0;
        for (int j = 0; j < bord.length; j++) {
            if (bord[j] == leikmadur) {
                ollPed[i++] = j;
            }
        }
        return ollPed;
    }
    
     /**
     * Nær í leikmann l
     *
     * @param l
     * @return leikmaður l
     */
    @Override
    public LeikmadurImp getLeikmadur(int l) {
        return leikmenn[l];
    }
    
    @Override
     public LeikmadurImp getNuverandiLeikmadur() {
        return nuverandiLeikmadur;
    }
}
