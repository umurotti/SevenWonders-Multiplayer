/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.util.*;

public class HandContainer {
    Map<String, List<Card>> container;
    public HandContainer(Card[][]hands, int noOfPlayers, List<String> names)
    {
        container = new HashMap<>();
        if(hands != null) {
            for(int a =0; a< noOfPlayers; a++)
            {
                List<Card> hold  = Arrays.asList(hands[a]);
                container.put(names.get(a),hold);
            }
        }
    }
  
    public HandContainer()
    {
        container = new HashMap<>();
 
    }

    public Map<String, List<Card>> getContainer() {
        return container;
    }

    public void setContainer(Map<String, List<Card>> container) {
        this.container = container;
    }
}