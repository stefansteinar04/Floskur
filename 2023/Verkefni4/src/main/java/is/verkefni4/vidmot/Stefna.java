package is.verkefni4.vidmot;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  :  enum fyrir Stefnu boltans
 *****************************************************************************/
public enum Stefna {
    VINSTRI(180),
    HAEGRI(0),
    NIDUR (270);

    private final int gradur;
    Stefna(int s) {
         gradur = s;
    }
    public int getGradur() {
        return gradur;
    }
}
