package sample;

import java.util.HashMap;

public class Cost {
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
}
