package vinnsla;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Vinnsluklasi fyrir strengi
 *
 *****************************************************************************/

public class Strengir {
    private String texti;

    public Strengir() {

    }

    /**
     * setur texta í vinnsluhlutann
     *
     * @param texti texti
     */
    public void setTexti(String texti) {
        this.texti = texti;
    }

    /**
     * Leitar að leitarord í textanum og skilar staðsetningu leitarorðsins
     *
     * @param leitarord leitarorðið
     * @return staðsetningu ef leitarorðið finnst en annars -1
     * @throws NullPointerException undantekning ef texti er null
     */
    public int leita(String leitarord) throws NullPointerException {
        if (texti == null) {
            throw new NullPointerException();
        } else {
            return texti.indexOf(leitarord);
        }
    }

    /**
     * telur fjölda orða í texti og skilar fjöldanum.
     *
     * @return fjöldi orða
     * @throws NullPointerException undantekning ef texti er null
     */
    public int fjoldiOrda() throws NullPointerException {
        if (texti == null) {
            throw new NullPointerException();
        } else {
            return texti.split(" ").length;
        }

    }
}
