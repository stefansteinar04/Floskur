/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Vinnsluklasi fyrir snákaleikinn. Bætir við stigum og heldur utan um stigatöflu
 *
 *
 *****************************************************************************/
package is.vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Leikur {

    private final ObservableList<Integer> stigatafla = FXCollections.observableArrayList(); // stigatafla
    private int stigin = 0;   // núverandi stig í einum leik

    /**
     * Hækkar stigin um einn
     */
    public void vinningur() {
        stigin++;
    }

    /**
     * Leik er lokið og stigum bætt við stigatölu
     */
    public void leikLokid() {
        stigatafla.add(stigin);
        stigin = 0;
    }

    /**
     * Skilar stigatöflunni
     *
     * @return stigataflan
     */
    public ObservableList<Integer> getStigin() {
        return stigatafla;
    }
}
