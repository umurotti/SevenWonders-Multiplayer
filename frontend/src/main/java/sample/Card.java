package sample;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Card implements Serializable {
    private Cost cost;
    private String color;
    private String name;
    private List<String> freeBuildings;
    private int minPlayerNo;


    public Card(Cost cost, String color, String name, List<String> freeBuildings, int minNoOfPlayer, HashMap<String, Integer> benefits) {
        this.cost = cost;
        this.color = color;
        this.name = name;
        this.freeBuildings = freeBuildings;
        this.minPlayerNo = minNoOfPlayer;
    }

    public Card() {

    }

    public int getMinPlayerNo() {
        return minPlayerNo;
    }


 /*   public Map<String, Integer> getBenefits() {
        return benefits;
    }*/

   /* public void setBenefits(HashMap<String, Integer> benefits) {
        this.benefits = benefits;
    }*/

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

    public List<String> getFreeBuildings() {
        return freeBuildings;
    }
    public void setFreeBuildings(List<String> freeBuildings) {
        this.freeBuildings = freeBuildings;
    }



    @Override
    public String toString() {
        return "Card{" + "cost=" + cost + ", color=" + color + ", name=" + name +   ", minNoOfPlayer=" + minPlayerNo + '}';
    }
}
