package is.vinnsla;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ListChangeListener;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Vinnsluklasi fyrir körfu. Karfa er eins konar matseðill en
 *  hefur heildarverð
 *
 *
 *****************************************************************************/
public class Karfa extends Matsedill {

    private final IntegerProperty heildarVerd = new SimpleIntegerProperty();

    public Karfa() {
        veitingar.addListener((ListChangeListener<? super Veitingar>) c -> {
            while (c.next()) {
                if (c.wasAdded()) { // var staki bætt við
                    heildarVerd.set(heildarVerd.get() + c.getAddedSubList().get(0).getVerd());
                } else if (c.wasRemoved()) { // var staki eytt
                    heildarVerd.set(heildarVerd.get() - c.getRemoved().get(0).getVerd());
                }
            }
        });
    }


        public IntegerProperty heildarVerdProperty() {
            return heildarVerd;
        }


    // prófunaraktygi
        public static void main(String[] args) {
        Karfa k = new Karfa();
        k.veitingar.add(new Veitingar("brauð", 100));
        k.veitingar.add(new Veitingar("snúður", 200));
        System.out.println ("heildarverð "+k.heildarVerd.get());
        k.veitingar.remove (1,2);
        System.out.println ("heildarverð "+k.heildarVerd.get());
    }
}
