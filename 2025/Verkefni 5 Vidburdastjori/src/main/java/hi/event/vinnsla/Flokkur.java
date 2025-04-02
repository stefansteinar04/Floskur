package hi.event.vinnsla;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *  Lýsing  : Vinnsluklasi fyrir flokk af viðburðum
 *
 *
 *****************************************************************************/
public enum Flokkur {

    FRAEDSLA("Fræðsla"),
    SKEMMTUN("Skemmtun"),
    FJOLSKYLDA("Fjolskylda");

    private final String name;

    Flokkur(String name) {
        this.name = name;
    }

    /**
     * skilar nafni flokksins
     * @return nafn flokksins
     */
    public String toString() {
        return name;
    }
}
