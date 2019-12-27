/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author umur
 */
public class Cost implements Cloneable {
        private HashMap<String,Integer> cost;

    public Cost(HashMap<String, Integer> cost) {
        this.cost = cost;
    }

    public Cost() {
    }

    public HashMap<String, Integer> getCost() {
        return cost;
    }
    
    public void setCost(HashMap<String, Integer> cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Cost{" + "cost=" + cost + '}';
    }

    @Override
    protected Cost clone() throws CloneNotSupportedException {
        Cost copy = new Cost();
        HashMap<String, Integer> toPut = new HashMap<>();
        for(Map.Entry<String, Integer> entry : this.cost.entrySet()) {
            toPut.put(entry.getKey(), entry.getValue());
        }
        copy.setCost(cost);
        return copy;
    }
    
    

}
