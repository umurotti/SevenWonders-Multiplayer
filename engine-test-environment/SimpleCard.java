import java.util.Objects;
import java.util.HashMap;

/**
 * @author OmerFarukKurklu
 * @version 0.1
 */

public class SimpleCard extends Card{

    private HashMap<String,Integer> benefits; // String: name of the resource, Integer: amount of the resource
    private int minNoOfPlayer;

    public SimpleCard(Cost cost, String color, String name, List<String> freeBuildings, HashMap<String,Integer> benefits, int minNoOfPlayers) {
        super(cost, color, name, minNoOfPlayers, freeBuildings);
        this.freeBuilding = freeBuilding;
        this.benefits = benefits;
    }

    @Override
    public void play(WonderBoard wb, String selection)
    {   
        // Update te sources of the WonderBoard.
        HashMap<String, Integer> wbSources = wb.getSources();
        for(String sourceName : benefits.keySet()) {
            if(wbSources.containsKey(sourceName)){
                wbSources.put(sourceName, wbSources.get(sourceName) + benefits.get(sourceName));
            }
        }
        wb.setSources(wbSources);

        // Put the card into the builtCards of the WonderBoard.
        HashMap<String,Card> wbCards = wb.getBuiltCards();
        wbCards.put(super.getName(), this);
        wb.setBuiltCards(wbCards);
    }

    public HashMap<String,Integer> getBenefits() {
        return this.benefits;
    }

    public void setBenefits(HashMap<String,Integer> benefits) {
        this.benefits = benefits;
    }

    public int getMinNoOfPlayer() {
        return this.minNoOfPlayer;
    }

    public void setMinNoOfPlayer(int minNoOfPlayer) {
        this.minNoOfPlayer = minNoOfPlayer;
    }

    public SimpleCard benefits(HashMap<String,Integer> benefits) {
        this.benefits = benefits;
        return this;
    }

    public SimpleCard minNoOfPlayer(int minNoOfPlayer) {
        this.minNoOfPlayer = minNoOfPlayer;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SimpleCard)) {
            return false;
        }
        SimpleCard simpleCard = (SimpleCard) o;
        return Objects.equals(benefits, simpleCard.benefits) && minNoOfPlayer == simpleCard.minNoOfPlayer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(benefits, minNoOfPlayer);
    }

    @Override
    public String toString() {
        return "{" +
            " benefits='" + getBenefits() + "'" +
            ", minNoOfPlayer='" + getMinNoOfPlayer() + "'" +
            "}";
    }

}
