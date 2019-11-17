/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Objects;
import java.util.HashMap;

/**
 * @author AkinParkan, OmerFarukKurklu
 * @version 0.2
 */

// This is not the Interface Class Card. This is basically the SimpleCard class for simplicity in demo 1.
public class Card {
    private Cost cost;
    private String color;
    private String name;
    private String freeBuilding;
    private HashMap<String,Integer> benefits; // String: name of the resource, Integer: amount of the resource
    private int minNoOfPlayer;


    public Card() {
    }

    public Card(Cost cost, String color, String name, String freeBuilding, HashMap<String,Integer> benefits, int minNoOfPlayer) {
        this.cost = cost;
        this.color = color;
        this.name = name;
        this.freeBuilding = freeBuilding;
        this.benefits = benefits;
        this.minNoOfPlayer = minNoOfPlayer;
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

    public Cost getCost() {
        return this.cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFreeBuilding() {
        return this.freeBuilding;
    }

    public void setFreeBuilding(String freeBuilding) {
        this.freeBuilding = freeBuilding;
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

    public Card cost(Cost cost) {
        this.cost = cost;
        return this;
    }

    public Card color(String color) {
        this.color = color;
        return this;
    }

    public Card name(String name) {
        this.name = name;
        return this;
    }

    public Card freeBuildings(String freeBuilding) {
        this.freeBuilding = freeBuilding;
        return this;
    }

    public Card benefits(HashMap<String,Integer> benefits) {
        this.benefits = benefits;
        return this;
    }

    public Card minNoOfPlayer(int minNoOfPlayer) {
        this.minNoOfPlayer = minNoOfPlayer;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Card)) {
            return false;
        }
        Card card = (Card) o;
        return Objects.equals(cost, card.cost) && Objects.equals(color, card.color) && Objects.equals(name, card.name) && Objects.equals(freeBuilding, card.freeBuilding) && Objects.equals(benefits, card.benefits) && minNoOfPlayer == card.minNoOfPlayer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cost, color, name, freeBuilding, benefits, minNoOfPlayer);
    }

    @Override
    public String toString() {
        return "{" +
            " cost='" + getCost() + "'" +
            ", color='" + getColor() + "'" +
            ", name='" + getName() + "'" +
            ", freeBuildings='" + getFreeBuilding() + "'" +
            ", benefits='" + getBenefits() + "'" +
            ", minNoOfPlayer='" + getMinNoOfPlayer() + "'" +
            "}";
    }
}
