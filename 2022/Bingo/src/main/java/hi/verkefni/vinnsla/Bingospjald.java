package hi.verkefni.vinnsla;
/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Vinnsluklasi sem geymir stöðuna á bingóspjaldi. Hver reitur hefur heiltölu. Ef
 *  heiltalan er -1 þá er búið að velja reitinn.
 *
 *
 *****************************************************************************/
public class Bingospjald implements BingospjaldInterface {

    // fastar
    private  static final int MAX=75; // stærsta talan á bingóspjaldi
    private static final int MIN=1;   // minnsta talan á bingóspjaldi
    private static final int AREIT = -1; // talan hefur verið lesin upp

    private final int[][] spjald;   // táknar bingóspjaldið

    /**
     * Smíðar bingóspjald af stærðinni n x m.
     * Bingóspjald er táknað sem tvívítt fylki
     * @param n fjöldi lína
     * @param m fjöldi dálka
     */
    public Bingospjald(int n, int m) {
        spjald = new int[n][m];
    }

    /**
     * Talan á reit (i,j) hefur verið lesin upp. Reiturinn er merktur sem AREIT (-1) í fylkinu.
     * Forskilyrði - (i,j) er innan marka bingóspjaldsins - óþarfi að tékka sérstaklega
     * @param i lína
     * @param j dálkur
     */
    public void aReit(int i, int j) {
        spjald[i][j] = AREIT;
    }

    /**
     * Frumstillir bingóspjald með tölum af handahófi
     *
     * @return skilar fylkinu með gögnum bingóspjaldsins
     */
    public int[][] nyttSpjald() {
        boolean [] erASpjaldi = new boolean[MAX+1];
        for (int i = 0; i < spjald.length; i++) // raðir
            for (int j = 0; j < spjald[0].length; j++) {    //dálkar
                int t;
                do {
                    t = nyTala();
                } while (erASpjaldi[t]);    // ef talan hefur komið fyrir þurfum við nýja random tölu
                erASpjaldi[t]=true;         // talan hefur komið fyrir
                spjald[i][j] = t;
            }
        return spjald;
    }

    /**
     * Ný tala af handahófi á bilinu MIN til og með MAX
     * @return tala af handahófi
     */
    private int nyTala() {
        return (int)(Math.random()*MAX)+MIN;
    }

    /**
     * Athugar hvort það er bingó í hornalínu spjaldsins
     * @return true ef það er bingo annars false
     */
    public boolean erBingo() {
        for (int i = 0; i < spjald[0].length; i++) // raðir
            if (spjald[i][i] != AREIT)  // getum hætt strax og tala hefur ekki verið valin
                return false;
        return true;
    }
}
