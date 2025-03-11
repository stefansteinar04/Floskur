package vinnsla;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  :
 *
 *
 *****************************************************************************/
public class Nemandi {
    private String nafn;
    private String tölvupóstfang;


    private Svid svid;
    private Deild deild;
    private Namsleid namsleid;

    public Nemandi(String n, String t, Svid s, Deild d, Namsleid nl) {
        nafn=n;
        tölvupóstfang=t;
        svid =s;
        deild=d;
        namsleid=nl;
    }

    public void setNafn(String nafn) {
        this.nafn = nafn;
    }

    public void setTölvupóstfang(String tölvupóstfang) {
        this.tölvupóstfang = tölvupóstfang;
    }

    public void setSvid(Svid svid) {
        this.svid = svid;
    }

    public void setDeild(Deild deild) {
        this.deild = deild;
    }

    public void setNamsleid(Namsleid namsleid) {
        this.namsleid = namsleid;
    }

    public String getNafn() {
        return nafn;
    }

    public String getTölvupóstfang() {
        return tölvupóstfang;
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

    public String toString () {
        return nafn+" "+namsleid.name();
    }


}
