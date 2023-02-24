package is.pipemania.vinnsla;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Vinnsluklasi fyrir leikborðið. Inniheldur 5x5 fylki af pípum. Merkir fyrstu pípuna (lindina)
 *  Getur sagt hvort vatn flæði um pípulögn og heldur utan um stigin.
 *
 *
 *****************************************************************************/

public class Pipemania {
    private static final int F = 5;

    private final Pipa[][] pipulogn = new Pipa[F][F]; // pípulögnin

    private Hnit lindin; // til að ég viti hvar ég á að byrja flæðið

    private final IntegerProperty stig = new SimpleIntegerProperty();   // stigin

    // nýjasta pípan sem var lögð í pípulögnina
    private final ObjectProperty<Pipa> nuverandiPipa = new SimpleObjectProperty<Pipa>();

    // þaðan sem við tökum nýjar pípur
    private final Pipukista pipukista;


    // gefur pípu aðliggjandi pípu og passar að fara ekki út fyrir pípulögnina
    private final ReiknaAdliggjandi[] adliggjandi = {
            (i, j) -> (j - 1) >= 0 ? pipulogn[i][j - 1] : null,
            (i, j) -> (i - 1) >= 0 ? pipulogn[i - 1][j] : null,
            (i, j) -> (j + 1) < pipulogn[0].length ? pipulogn[i][j + 1] : null,
            (i, j) -> (i + 1) < pipulogn.length ? pipulogn[i + 1][j] : null};

    // gefur aðliggjandi hnit pípu
    private final ReiknaHnit[] hnitin = {
            (i, j) -> new Hnit(i, j - 1),
            (i, j) -> new Hnit(i - 1, j),
            (i, j) -> new Hnit(i, j + 1),
            (i, j) -> new Hnit(i + 1, j)};

    public Pipemania(int s) {
        pipukista = new Pipukista(s - 1);
    }

    /**
     * Athugar flæði í pípulögninni frá lindinni og uppfærir stigin til samræmis
     */
    public void flaedir() {
        stig.set(1);
        if (lindin != null) // vatn flæðir út um úttakið og er því einátta
            flaedir(lindin);
    }

    /**
     * Athugar hvort það flæðir frá pípu í (i,j) yfir í næsta reit við hliðina á skv. ut eða inn leiðinni
     * uppfærir stigin til samræmis
     */
    public void flaedir(Hnit h) {
        int i = h.i();
        int j = h.j();
        // ut endinn
        Pipa r = adliggjandi[pipulogn[i][j].getUt().ordinal()].reiknaAdliggjandi(i, j);
        // vatn rennur um pipuna nema ekki merkja þá fyrstu sem flæði svo við yfirskrifum ekki merkingu
        if (pipulogn[i][j] != fyrstaPipa(lindin))
            pipulogn[i][j].setOpin(true);
        // Ef aðliggjandi pípa er ekki núll og það flæðir í hana höldum þá áfram
        if (r != null && pipulogn[i][j].flaedir(r)) {
            stig.set(getStig() + 1);
            // r verður fyrsta stakið í næstu pípu - náum í hnitin á því
            flaedir(hnitin[pipulogn[i][j].getUt().ordinal()].hnit(i, j));
        }
    }

    private Pipa fyrstaPipa(Hnit fyrstaHnit) {
        return pipulogn[fyrstaHnit.i][fyrstaHnit.j];
    }

    /**
     * Bý til nýja pípu og setur í grid-ið í (i,j)
     *
     * @param i röðin: staðsetning í gridi
     * @param j dálkurinn: staðsetning í gridi
     */
    public void setNaestaPipa(int i, int j) {
        Pipa p = naestaPipaUrKistu();
        pipulogn[i][j] = p;
        setNuverandiPipa(p);

        if (lindin == null) {
            lindin = new Hnit(i, j);
        }
    }

    //get og setterar
    public ObjectProperty<Pipa> nuverandiPipaProperty() {
        return nuverandiPipa;
    }

    public void setNuverandiPipa(Pipa nuverandiPipa) {
        this.nuverandiPipa.set(nuverandiPipa);
    }

    public int getStig() {
        return stig.get();
    }

    public IntegerProperty stigProperty() {
        return stig;
    }


    private Pipa naestaPipaUrKistu() {
        return pipukista.naestaPipa();
    }


    public ObservableList<Pipa> getPipukista() {
        return pipukista.getKista();
    }

    // klasi sem heldur utan um Hnit - væri líka hægt að nota Point klasann
    public static class Hnit {
        private final int i;
        private final int j;

        public Hnit(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int i() {
            return i;
        }

        public int j() {
            return j;
        }

        public String toString() {
            return "i:" + i + " j:" + j;
        }
    }


    // mjög lítið prófunaraktygi
    public static void main(String[] args) {
        Pipemania pM = new Pipemania(5);

    }
}
