/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public abstract class Card {
    private final Cost cost;
    private final String color;
    private final String name;
    private final int minPlayerNo;
    private final List<String> freeBuildings;

    abstract void play(WonderBoard wb, String selection, HashMap<String, WonderBoard> wonderboards);

    public Card(String name, String color, Cost cost, int minPlayerNo, List<String> freeBuildings) {
        this.cost = cost;
        this.color = color;
        this.name = name;
        this.minPlayerNo = minPlayerNo;
        this.freeBuildings = freeBuildings;
    }
    
    public Card() {
        this.cost = null;
        this.color = null;
        this.name = null;
        this.minPlayerNo = 0;
        this.freeBuildings = null;
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
    public int getMinPlayerNo() {
        return this.minPlayerNo;
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
        return Objects.equals(cost, card.cost) && Objects.equals(color, card.color) && Objects.equals(name, card.name) && minPlayerNo == card.minPlayerNo && Objects.equals(freeBuildings, card.freeBuildings);
    }
    @Override
    public int hashCode() {
        return Objects.hash(cost, color, name, minPlayerNo, freeBuildings);
    }
    @Override
    public String toString() {
        return "{" +
            " cost='" + getCost() + "'" +
            ", color='" + getColor() + "'" +
            ", name='" + getName() + "'" +
            ", minNoOfPlayers='" + getMinPlayerNo() + "'" +
            ", freeBuildings='" + getFreeBuildings() + "'" +
            "}";
    }
    
}