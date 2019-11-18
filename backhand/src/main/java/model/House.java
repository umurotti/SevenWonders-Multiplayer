/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Singleton;

/**
 *
 * @author umur
 */
public class House {
    private final String AGE_ONE = "Age1Cards";
    private final String AGE_TWO = "AGE_TWO";
    private final String AGE_THREE = "AGE_THREE";
    
    private volatile static House obj;
    
    private Map<String, Table> tables;
    private Map<String, Table> waitingTables;
    private Map<String, Table> inplayTables;
    private Map<String, Deck> decks;
    
    private DeckFactory deckFactory;
    
    private House() throws IOException {
        tables = new HashMap<>();
        waitingTables = new HashMap<>();
        inplayTables = new HashMap<>();
        decks = new HashMap<>();
        deckFactory = new DeckFactory();
        
        decks.put(AGE_ONE, deckFactory.generateDeck(AGE_ONE));
//        decks.put(AGE_TWO, deckFactory.generateDeck(AGE_TWO));
//        decks.put(AGE_THREE, deckFactory.generateDeck(AGE_THREE));
    }
    
    public static House getInstance() throws IOException 
    {
        if (obj == null) 
        { 
            // To make thread safe 
            synchronized (Singleton.class) 
            { 
                // check again as multiple threads 
                // can reach above step 
                if (obj==null) 
                    obj = new House();
            } 
        } 
        return obj; 
    }
    
    public boolean createTable(String ownerID, String tableID) {
            if(!tables.containsKey(tableID)) {
            Table table = new Table(tableID, ownerID, decks.get(AGE_ONE)/*, decks.get(AGE_TWO), decks.get(AGE_THREE)*/ );
            tables.put(tableID, table);
            waitingTables.put(tableID, table);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean deleteTable(String tableID) {
        tables.remove(tableID);
        return true;
    }
    
    public boolean joinTable(String tableID, String playerID) {
//        tables.get(tableID).playerJoined(playerID, null);
        return true;
    }
    
    public boolean startTable(String tableID) {
        tables.get(tableID).startTable();
        inplayTables.put(tableID, tables.get(tableID));
        waitingTables.remove(tableID);
        return true;
    }
    
    public HashMap<String, Table> getInPlayTables() {
        return  (HashMap<String, Table>) inplayTables;
    }
    
    public HashMap<String, Table> getWaitingTables() {
        return  (HashMap<String, Table>) waitingTables;
    }
            
            public void deneme() throws IOException {
        DeckFactory deneme = new DeckFactory();
    }
    
    private String generateTableID() {
        return "alo";
    }
}
