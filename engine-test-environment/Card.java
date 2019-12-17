import java.util.List;

abstract class Card {
    private final Cost cost;
    private final String color;
    private final String name;
    private final int minNoOfPlayers;
    private final List<String> freeBuildings;

    abstract boolean play(WonderBoard wb);

    public Card(String name, String color, Cost cost, int minNoOfPlayers, List<String> freeBuildings) {
        this.cost = cost;
        this.color = color;
        this.name = name;
        this.minNoOfPlayers = minNoOfPlayers;
        this.freeBuildings = freeBuildings;
    }

    public Cost getCost() {
        return this.cost;
    }
    public String getColor() {
        return this.color;
    }
    public String getName() {
        return this.name;
    }
    public int getMinNoOfPlayers() {
        return this.minNoOfPlayers;
    }
    public List<String> getFreeBuildings() {
        return this.freeBuildings;
    }
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Card)) {
            return false;
        }
        Card card = (Card) o;
        return Objects.equals(cost, card.cost) && Objects.equals(color, card.color) && Objects.equals(name, card.name) && minNoOfPlayers == card.minNoOfPlayers && Objects.equals(freeBuildings, card.freeBuildings);
    }
    @Override
    public int hashCode() {
        return Objects.hash(cost, color, name, minNoOfPlayers, freeBuildings);
    }
    @Override
    public String toString() {
        return "{" +
            " cost='" + getCost() + "'" +
            ", color='" + getColor() + "'" +
            ", name='" + getName() + "'" +
            ", minNoOfPlayers='" + getMinNoOfPlayers() + "'" +
            ", freeBuildings='" + getFreeBuildings() + "'" +
            "}";
    }
    
}