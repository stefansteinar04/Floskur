/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Gagnaklasi sem getur líka lesið og skrifað upplýsingar um nemanda
 *
 *
 *****************************************************************************/
package vinnsla;

import javafx.application.Platform;

public class Nemandi {
    // fasti
    private static final String OLOGLEGT_INNTAK = "Ólöglegt inntak ";

    private String nafn;
    private String tolvupostfang;
    private Svid svid;
    private Deild deild;
    private Namsleid namsleid;

    public Nemandi(String n, String t, Svid s, Deild d, Namsleid nl) {
        nafn = n;
        tolvupostfang = t;
        svid = s;
        deild = d;
        namsleid = nl;
    }

    /**
     * Smiður sem túlkar (parse) gögn um nemanda í einni línu (streng)
     * @param lina ótúlkuð gögn - gögn eru aðgreind með stafabili
     */
    public Nemandi(String lina) {
        String [] allt = lina.split(" ");
        nafn = allt[0];                     // Hér mætti líka gera villutékk sbr. í dialog
        tolvupostfang = allt[1];
        try {                               // Aðeins gert villutékk á svið, deild og námsleið
            svid = Svid.valueOf(allt[2]);
            deild = Deild.valueOf(allt[3]);
            namsleid = Namsleid.valueOf(allt[4]);
        }
        catch (IllegalArgumentException e) {
            System.out.println (OLOGLEGT_INNTAK +lina);
            Platform.exit();
            System.exit(0);
        }
    }

    public String getNafn() {
        return nafn;
    }

    public String getTolvupostfang() {
        return tolvupostfang;
    }

    public Svid getSvid() {
        return svid;
    }

    public Deild getDeild() {
        return deild;
    }

    public Namsleid getNamsleid() {
        return namsleid;
    }

    /**
     * Segir til um hvernig upplýsingar um nemanda birtast í ListView
     *
     * @return
     */
    public String toString() {
        return nafn + " " + namsleid.name();
    }

    /**
     * Afritar upplýsingar um nemanda n í þennan nemanda
     *
     * @param n nemandi
     */
    public void breyta(Nemandi n) {
        nafn = n.nafn;                   // Athugið að hér má nota n.nafn þó það sé private breyta því við erum í klasanum.
        tolvupostfang = n.tolvupostfang;
        svid = n.svid;
        deild = n.deild;
        namsleid = n.namsleid;
    }

    public String skrifa() {
       return nafn+" "+tolvupostfang+" "+svid+" "+deild+" "+namsleid;
    }
}
