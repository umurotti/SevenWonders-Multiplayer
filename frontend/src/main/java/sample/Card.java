package sample;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Card implements Serializable {
    private Cost cost;
    private String color;
    private String name;
    private String freeBuildings;
    private int minPlayerNo;
    private HashMap<String,Integer> benefits;


    public Card(Cost cost, String color, String name, String freeBuildings, int minNoOfPlayer, HashMap<String, Integer> benefits) {
        this.cost = cost;
        this.color = color;
        this.name = name;
        this.freeBuildings = "";
        this.minPlayerNo = minNoOfPlayer;
        this.benefits = benefits;
    }

    public Card() {
        benefits = new HashMap<String, Integer>();
    }




    public int getMinPlayerNo() {
        return minPlayerNo;
    }


    public Map<String, Integer> getBenefits() {
        return benefits;
    }

    public void setBenefits(HashMap<String, Integer> benefits) {
        this.benefits = benefits;
    }

    public void setMinPlayerNo(int minPlayerNo) {
        this.minPlayerNo = minPlayerNo;
    }

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFreeBuildings() {
        return freeBuildings;
    }
    public void setFreeBuildings(String freeBuildings) {
        this.freeBuildings = freeBuildings;
    }

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
        wbCards.put(this.name, this);
        wb.setBuiltCards(wbCards);
    }

    @Override
    public String toString() {
        return "Card{" + "cost=" + cost + ", color=" + color + ", name=" + name +  ", benefits=" + benefits + ", minNoOfPlayer=" + minPlayerNo + '}';
    }
}
