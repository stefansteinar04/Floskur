package is.pipemania.vinnsla;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Vinnsluklasi fyrir pípu - veit í hvaða átt pípan snýr og hvort vatn flæðir um hana
 *
 *
 *****************************************************************************/
public class Pipa {
    public enum Att {V, N, A, S}

    ;

    private Att inn; // inntak pípunnar
    private Att ut; // úttak pípunnar

    private final BooleanProperty opin = new SimpleBooleanProperty(); // hvort pípan er opin eða ekki

    // get og setterar
    public BooleanProperty opinProperty() {
        return opin;
    }

    public void setOpin(Boolean opin) {
        this.opin.set(opin);
    }


    // einn möguleiki er að pípa viti á hvaða reit hún er en það brýtur aðeins separation of concern
    // við notum því ekki þá leið


    public Att getUt() {
        return ut;
    }

    public Att getInn() {
        return inn;
    }


    public Pipa(Att inn, Att ut) {
        this.inn = inn;
        this.ut = ut;
    }

    /**
     * Athugar hvort vatn flæðir úr þessari pípu í pípu a. Flæði er tvíátta
     *
     * @param a pípa
     * @return true ef pípa flæðir í a
     */
    public boolean flaedir(Pipa a) {
        if ((ut.ordinal() + 2) % 4 == a.inn.ordinal())
            return true;
        else if ((ut.ordinal() + 2) % 4 == a.ut.ordinal()) {
            swap(a);
            return true;
        }
        return false;
    }

    /**
     * Snýr pípu a við þannig að úttak verður inntak og öfugt
     *
     * @param a
     */
    private static void swap(Pipa a) {
        Att tmp = a.ut;
        a.ut = a.inn;
        a.inn = tmp;
    }


    @Override
    public String toString() {
        return "inn: " + inn.toString() + " ut: " + ut.toString();
    }

    // stutt prófunaraktygi
    public static void main(String args[]) {
        Pipa p1 = new Pipa(Att.V, Att.S);
        Pipa p2 = new Pipa(Att.N, Att.A);
        System.out.println(p1 + " " + p2 + p1.flaedir(p2));
        p1 = new Pipa(Att.V, Att.S);
        p2 = new Pipa(Att.V, Att.S);
        System.out.println(p1 + " " + p2 + p1.flaedir(p2));
    }
}
