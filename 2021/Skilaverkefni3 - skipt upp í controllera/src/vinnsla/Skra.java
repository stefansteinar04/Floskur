package vinnsla;

import javafx.collections.ObservableList;

public interface Skra <T> {

    public ObservableList<T> lesa (String s);

    public void skrifa (ObservableList<T> listi, String s);
}
