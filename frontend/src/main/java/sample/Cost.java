package sample;

import java.util.HashMap;

public class Cost {
    HashMap<String, String> cost;

    Cost(){

    }
    Cost(HashMap<String,String> cost){
        this.cost = cost;
    }
    public void setCost(HashMap<String, String> cost) {
        this.cost = cost;
    }

    public HashMap<String, String> getCost() {
        return cost;
    }
}
