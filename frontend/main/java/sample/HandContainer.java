package sample;

import java.util.*;

public class HandContainer {
    Map<String, List<Card>> container;
    public HandContainer(){
        container = new HashMap<String,List<Card>>();
    }
    public HandContainer(Card[][]hands, int noOfPlayers, ArrayList<String> names)
    {
        container = new HashMap<String,List<Card>>();
        for(int a =0; a< noOfPlayers; a++)
        {
            List<Card> hold  = Arrays.asList(hands[a]);
            container.put(names.get(a),hold);
        }
    }

    public Map<String, List<Card>> getContainer() {
        return container;
    }

    public void setContainer(Map<String, List<Card>> container) {
        this.container = container;
    }
}