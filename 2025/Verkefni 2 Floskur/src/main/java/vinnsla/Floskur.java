/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Stýriklasi fyrir Flöskur. Notandi getur sett inn fjöldi flaska,
 *  dósa, hreinsað og fengið
 *  greitt fyrir flöskur og dósir
 *
 *
 *****************************************************************************/

package vinnsla;

public class Floskur {
    private static final int FLASKA_ISK = 15;
    private static final int DOS_ISK = 10;
    private int fjoldiFloskur;
    private int fjoldiDosir;
    private int virdiFloskur;
    private int virdiDosir;
    private int samtalsFjoldi;
    private int samtalsVirdi;
    private int heildFjoldi;
    private int heildVirdi;


    /** setur flöskur í vinnsluna og uppfærir virðið og samtals
     * @param floskur
     */
    public void setFjoldiFloskur(int floskur) {
        fjoldiFloskur = floskur;
        virdiFloskur = fjoldiFloskur * FLASKA_ISK;
        samtals();
    }

    /**
     * Skilar virði flaska
     *
     * @return virði flaska
     */
    public int getISKFloskur() {
        return virdiFloskur;
    }

    /**
     * Setur fjölda dósa og uppfærir virði dósa
     *
     * @param dosir
     */
    public void setFjoldiDosir(int dosir) {
        fjoldiDosir = dosir;
        virdiDosir = fjoldiDosir * DOS_ISK;
        samtals();
    }

    /**
     * Skilar virði flaska
     *
     * @return virði flaska
     */
    public int getISKDosir() {
        return virdiDosir;
    }

    /**
     * núllar fjölda og virði dósa og flaska
     */
    public void hreinsa() {
        fjoldiDosir = 0;
        fjoldiFloskur = 0;
        virdiDosir = 0;
        virdiFloskur = 0;
        samtalsFjoldi = 0;
        samtalsVirdi = 0;
    }

    /**
     * skilar fjölda dósa
     *
     * @return fjöldi dósa
     */
    public int getFjoldiDosir() {
        return fjoldiDosir;
    }

    /**
     * skilar fjölda flaska
     *
     * @return fjölda flaska
     */
    public int getFjoldiFloskur() {
        return fjoldiFloskur;
    }

    /**
     * skilar samtals fjölda dósa og flaska
     *
     * @return fjölda dósa og flaska
     */
    public int getSamtalsFjoldi() {
        return samtalsFjoldi;
    }

    /**
     * skilar samtals virði
     *
     * @return samtals virði
     */
    public int getSamtalsVirdi() {
        return samtalsVirdi;
    }

    /**
     * samtals fjöldi og virði bætast við heildarfjölda og greiðslu
     * fjöldi og virði er núllað
     */
    public void greida() {
        heildFjoldi += samtalsFjoldi;
        heildVirdi +=samtalsVirdi;
        hreinsa();
    }

    /**
     * skilar heildarfjölda sem safnað hefur verið
     * @return heildar fjöldi
     */
    public int getHeildFjoldi() {
        return heildFjoldi;
    }

    /**
     *  skilar heildarvirði sem safnað hefur verið
     * @return heildarvirði
     */
    public int getHeildVirdi() {
        return heildVirdi;
    }

    /**
     * Reiknar út samtals fjölda flaska og dósa og virði þeirra samanlangt
     */
    private void samtals() {
        samtalsFjoldi = fjoldiFloskur + fjoldiDosir;
        samtalsVirdi = virdiDosir+virdiFloskur;
    }
}
