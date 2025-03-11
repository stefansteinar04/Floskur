package vinnsla;

import javafx.collections.ObservableList;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Java interface fyrir að lesa og skrifa hluti í skrá
 *
 *
 *******************************************************************************/
public interface Skra<T> {

    /**
     * Les hluti af taginu T úr skrá með heitinu s
     *
     * @param s heiti á skrá
     * @return lista af hlutum af klasanum T
     */
    ObservableList<T> lesa(String s);

    /**
     * Skrifar hluti á listi í skrá með heitinu s
     *
     * @param listi listi af hlutum
     * @param s     heiti á skrá
     */
    void skrifa(ObservableList<T> listi, String s);
}
