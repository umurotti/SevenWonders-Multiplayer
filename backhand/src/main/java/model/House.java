/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Singleton;

/**
 *
 * @author umur
 */

class ConnectionAccepter implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("deneme " + i);
        }//To change body of generated methods, choose Tools | Templates.
    }
    
}
public class House {
    private final String AGE_ONE = "Age1Cards";
    private final String AGE_TWO = "Age2Cards";
    private final String AGE_THREE = "Age3Cards";
    private final String MAGIC_CARD = "MagicCards";
    
    private static House obj = null;
    private static Long lockThis = new Long(0);
    
    private Map<String, Table> tables;
    private Map<String, Table> waitingTables;
    private Map<String, Table> inplayTables;
    private Map<String, Deck> decks;
    //
    private HashMap<String, WonderBoard> wonderboardObjects;
    //
    private DeckGenerator deckFactory;
    private WonderBoardGenerator wonderboardFactory;
    
    private House() throws IOException {
        tables = new HashMap<>();
        waitingTables = new HashMap<>();
        inplayTables = new HashMap<>();
        decks = new HashMap<>();
        deckFactory = new DeckGenerator();
        wonderboardFactory = new WonderBoardGenerator();
        
        decks.put(AGE_ONE, deckFactory.generateDeck(AGE_ONE));
        decks.put(AGE_TWO, deckFactory.generateDeck(AGE_TWO));
        decks.put(AGE_THREE, deckFactory.generateDeck(AGE_THREE));
        decks.put(MAGIC_CARD, deckFactory.generateDeck(MAGIC_CARD));
        
        wonderboardObjects = wonderboardFactory.generateWonderBoards();
//        SimpleCard x = new SimpleCard(true);
//        DiscountCard y = new DiscountCard("asdf", "", null, 0, null, "", "");
//        CumulativeCard z = new CumulativeCard("", "", null, 0, null, "", null, 0, 0);
        
//        System.out.println("SimpleCard:");
//        System.out.println(parseJSON(x));
//        
//        System.out.println("DiscountCard:");
//        System.out.println(parseJSON(y));
//        
//        System.out.println("CumulativeCard:");
//        System.out.println(parseJSON(z));
        
        
        
        
        Thread acceptor = new Thread(new ConnectionAccepter());
        acceptor.setDaemon(true);
        acceptor.start();
    }
    
    public static House getInstance() throws IOException 
    {
        
        if (obj == null) 
        { 
            // To make thread safe 
            synchronized (lockThis) 
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
            Table table = new Table(tableID, ownerID, decks.get(AGE_ONE), decks.get(AGE_TWO), decks.get(AGE_THREE), decks.get(MAGIC_CARD), wonderboardObjects );
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
    
    public boolean startTable(String tableID) throws CloneNotSupportedException {
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

    public Map<String, Table> getTables() {
        return tables;
    }
            
    
    public void deneme() throws IOException {
        DeckGenerator deneme = new DeckGenerator();
    }
    
    private String generateTableID() {
        return "alo";
    }
}
