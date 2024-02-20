/*
Ebba Þóra Hvannberg ebba@hi.is
 */

package is.hi.ludo.vinnsla;

import java.util.Random;
import javafx.beans.property.SimpleIntegerProperty;


/**
 * Útfærir einn tening sem hefur hliðar 1-6. Hægt að kasta tening. 
 * 
 * @author Ebba Þóra Hvannberg ebba@hi.is 
 * Háskóli Íslands
 */
public class Teningur {
    private int teningur=6; // Teningur er 6 í byrjun
    private SimpleIntegerProperty propGildi = new SimpleIntegerProperty(6);

    
    private Random rand = new Random();
    static final int MAX = 6;
    static final int MIN = 1;

      
    /**
     * Býr til random tölu
     * @return random tölu 
     */
    private int naestaRandomTala() {
        int randomNum = rand.nextInt((MAX - MIN) + 1) + MIN;
        //
        // randomNum = 6;
        return randomNum;
    }

    /**
     * Kastar teningnum og skilar gildi hans
     */
    public int kasta () {
          teningur = naestaRandomTala();
          propGildi.set(teningur);
          return teningur;
    }
    
    public int getTeningur() {
        return teningur;
    }
    
    public void setPropGildi(int i) {
        propGildi.set(i);
    }
    
    public SimpleIntegerProperty getPropGildi() {
        return propGildi;
    }
}
