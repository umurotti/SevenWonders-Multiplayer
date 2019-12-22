/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.mycompany.cs319deneme3.JaxRsActivator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author umur
 */
public class DeckGenerator {
//    private final String DECK_PATH = "/home/umur/Desktop/";
//    private final String DECK_PATH = "/home/ec2-user/";
    
    private final String DECK_PATH_IDENTIFIER = "deckPath";
    
    private final String DECK_EXTENSION = ".json";
    private final String CARD_NAME_ATTRIBUTE = "name";
    private final String CARD_TYPE_SIMPLE = "SimpleCard";
    private final String CARD_TYPE_DISCOUNT = "DiscountCard";
    private final String CARD_TYPE_CUMULATIVE_COIN = "CumulativeCoinCard";
    private final String CARD_TYPE_MAGIC = "MagicCard";
    
    private String deckPath;
    private ObjectMapper mapper;
    private File fl;
    
    private HashMap<String, String> cardNameToType;
    
    public DeckGenerator() throws FileNotFoundException, IOException {
        
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(JaxRsActivator.PROPERTY_PATH));
        } catch (Exception ex) {
            Logger.getLogger(DeckGenerator.class.getName()).log(Level.SEVERE, "Error loading /etc/7wonders.properties", ex);
        }
        
            deckPath = props.getProperty(DECK_PATH_IDENTIFIER, "/home/ebedek");
            mapper = new ObjectMapper();
            
            cardNameToType = new HashMap<>();
            
            ///AGE I///
            cardNameToType.put("Lumber Yard", CARD_TYPE_SIMPLE);
            cardNameToType.put("Stone Pit", CARD_TYPE_SIMPLE);
            cardNameToType.put("Clay Pool", CARD_TYPE_SIMPLE);
            cardNameToType.put("Ore Vein", CARD_TYPE_SIMPLE);
            cardNameToType.put("Tree Farm", CARD_TYPE_SIMPLE);
            cardNameToType.put("Excavation", CARD_TYPE_SIMPLE);
            cardNameToType.put("Clay Pit", CARD_TYPE_SIMPLE);
            cardNameToType.put("Timber Yard", CARD_TYPE_SIMPLE);
            cardNameToType.put("Forest Cave", CARD_TYPE_SIMPLE);
            cardNameToType.put("Mine", CARD_TYPE_SIMPLE);
            cardNameToType.put("Loom", CARD_TYPE_SIMPLE);
            cardNameToType.put("Glassworks", CARD_TYPE_SIMPLE);
            cardNameToType.put("Press", CARD_TYPE_SIMPLE);
            cardNameToType.put("Pawnshop", CARD_TYPE_SIMPLE);
            cardNameToType.put("Baths", CARD_TYPE_SIMPLE);
            cardNameToType.put("Altar", CARD_TYPE_SIMPLE);
            cardNameToType.put("Theater", CARD_TYPE_SIMPLE);
            cardNameToType.put("Tavern", CARD_TYPE_SIMPLE);
            cardNameToType.put("Stockade", CARD_TYPE_SIMPLE);
            cardNameToType.put("Barracks", CARD_TYPE_SIMPLE);
            cardNameToType.put("Guard Tower", CARD_TYPE_SIMPLE);
            cardNameToType.put("Apothecary", CARD_TYPE_SIMPLE);
            cardNameToType.put("Workshop", CARD_TYPE_SIMPLE);
            cardNameToType.put("Scriptorium", CARD_TYPE_SIMPLE);
            cardNameToType.put("East Trading Post", CARD_TYPE_DISCOUNT);
            cardNameToType.put("West Trading Post", CARD_TYPE_DISCOUNT);
            cardNameToType.put("Marketplace", CARD_TYPE_DISCOUNT);
            
            ///AGE II / AGE I//
            cardNameToType.put("Sawmill", CARD_TYPE_SIMPLE);
            cardNameToType.put("Quarry", CARD_TYPE_SIMPLE);
            cardNameToType.put("Brickyard", CARD_TYPE_SIMPLE);
            cardNameToType.put("Foundry", CARD_TYPE_SIMPLE);
            //cardNameToType.put("Loom", CARD_TYPE_SIMPLE);
            //cardNameToType.put("Glassworks", CARD_TYPE_SIMPLE);
            //cardNameToType.put("Press", CARD_TYPE_SIMPLE);
            cardNameToType.put("Aqueduct", CARD_TYPE_SIMPLE);
            cardNameToType.put("Temple", CARD_TYPE_SIMPLE);
            cardNameToType.put("Statue", CARD_TYPE_SIMPLE);
            cardNameToType.put("Courthouse", CARD_TYPE_SIMPLE);
            cardNameToType.put("Forum", CARD_TYPE_SIMPLE);
            cardNameToType.put("Carvansary", CARD_TYPE_SIMPLE);
            cardNameToType.put("Walls", CARD_TYPE_SIMPLE);
            cardNameToType.put("Training Ground", CARD_TYPE_SIMPLE);
            cardNameToType.put("Stables", CARD_TYPE_SIMPLE);
            cardNameToType.put("Archery Range", CARD_TYPE_SIMPLE);
            cardNameToType.put("Dispensary", CARD_TYPE_SIMPLE);
            cardNameToType.put("Laboratory", CARD_TYPE_SIMPLE);
            cardNameToType.put("Library", CARD_TYPE_SIMPLE);
            cardNameToType.put("School", CARD_TYPE_SIMPLE);
            cardNameToType.put("Vineyard", CARD_TYPE_CUMULATIVE_COIN);
            cardNameToType.put("Bazar", CARD_TYPE_CUMULATIVE_COIN);
            
            ///AGE III //
            cardNameToType.put("Pantheon", CARD_TYPE_SIMPLE);
            cardNameToType.put("Gardens", CARD_TYPE_SIMPLE);
            cardNameToType.put("Town Hall", CARD_TYPE_SIMPLE);
            cardNameToType.put("Palace", CARD_TYPE_SIMPLE);
            cardNameToType.put("Senate", CARD_TYPE_SIMPLE);
            //
            cardNameToType.put("Haven", CARD_TYPE_CUMULATIVE_COIN);
            cardNameToType.put("Lighthouse", CARD_TYPE_CUMULATIVE_COIN);
            cardNameToType.put("Chamber Of Commerce", CARD_TYPE_CUMULATIVE_COIN);
            cardNameToType.put("Arena", CARD_TYPE_CUMULATIVE_COIN);
            //
            cardNameToType.put("Fortifications", CARD_TYPE_SIMPLE);
            cardNameToType.put("Circus", CARD_TYPE_SIMPLE);
            cardNameToType.put("Arsenal", CARD_TYPE_SIMPLE);
            cardNameToType.put("Siege Workshop", CARD_TYPE_SIMPLE);
            cardNameToType.put("Lodge", CARD_TYPE_SIMPLE);
            cardNameToType.put("Observatory", CARD_TYPE_SIMPLE);
            cardNameToType.put("University", CARD_TYPE_SIMPLE);
            cardNameToType.put("Academy", CARD_TYPE_SIMPLE);
            cardNameToType.put("Study", CARD_TYPE_SIMPLE);
            //
            cardNameToType.put("Workers Guild", CARD_TYPE_CUMULATIVE_COIN);
            cardNameToType.put("Craftmens Guild", CARD_TYPE_CUMULATIVE_COIN);
            cardNameToType.put("Traders Guild", CARD_TYPE_CUMULATIVE_COIN);
            cardNameToType.put("Philosophers Guild", CARD_TYPE_CUMULATIVE_COIN);
            cardNameToType.put("Spy Guild", CARD_TYPE_CUMULATIVE_COIN);
            cardNameToType.put("Strategy Guild", CARD_TYPE_CUMULATIVE_COIN);
            cardNameToType.put("Shipowners Guild", CARD_TYPE_CUMULATIVE_COIN);
            //
            cardNameToType.put("Scientists Guild", CARD_TYPE_SIMPLE);
            cardNameToType.put("Magistrates Guild", CARD_TYPE_CUMULATIVE_COIN);
            cardNameToType.put("Builders Guild", CARD_TYPE_CUMULATIVE_COIN);
            
	
//SPY GUILD	    CumulativeCoinCard 
//STRATEGY GUILD      CumulativeCoinCard 
//SHIPOWNERS GUILD    CumulativeCoinCard 	
//SCIENTISTS GUILD    SimpleCard
//MAGISTRATES GUILD   CumulativeCoinCard 
//BUILDERS GUILD      CumulativeCoinCard 
//SAWMILL             SimpleCard 
//QUARRY	 	    SimpleCard 	
//BRICKYARD   	    SimpleCard
//FOUNDRY		    SimpleCard
//LOOM		    SimpleCard
//GLASSWORKS	    SimpleCard 
//PRESS		    SimpleCard
//AQUEDUCT	    SimpleCard 
//TEMPLE		    SimpleCard
//STATUE		    SimpleCard	
//COURTHOUSE	    SimpleCard
//FORUM	 	    SimpleCard
//CARAVANSARY	    SimpleCard
//VINEYARD	    CumulativeCoinCard
//BAZAR		    CumulativeCoinCard
//WALLS		    SimpleCard	
//TRAINING GROUND	    SimpleCard
//STABLES	   	    SimpleCard	
//ARCHERY RANGE       SimpleCard
//DISPENSARY	    SimpleCard
//LABORATORY	    SimpleCard
//LIBRARY             SimpleCard
//SCHOOL 	   	    SimpleCard
//        ListIterator<Card> 
//                iterator = readValues.listIterator(); 
//        System.out.println("-------------------");
//        while(iterator.hasNext()) {
//            System.out.println(iterator.next().toString());
//        }
        
    }
    
    public Deck generateDeck(String deckType) throws IOException {
        //Gson gson = new Gson();
        //JsonReader reader = new JsonReader(new FileReader(DECK_PATH + deckType + DECK_EXTENSION));
        //List<Card> cards = mapper.readValue(new File(DECK_PATH + deckType + DECK_EXTENSION), new TypeReference<List<SimpleCard>>() { });
        //JsonElement jelement = new JsonParser().parse(DECK_PATH + deckType + DECK_EXTENSION);
        JsonArray jArray = (JsonArray) new JsonParser().parse(new FileReader(deckPath + deckType + DECK_EXTENSION));
                //jelement.getAsJsonArray();
        Gson gson = new Gson();
        
        List<Card> cards = new ArrayList<>();
                
        for(int i = 0; i < jArray.size(); i++) {
            String  cardName = (String) jArray.get(i).getAsJsonObject().get(CARD_NAME_ATTRIBUTE).toString().replaceAll("\"", "");
        
            Card tmp = null;
            System.out.println(cardName);
            if(cardNameToType.get(cardName).equals(CARD_TYPE_SIMPLE)) {
                 tmp = gson.fromJson(jArray.get(i), SimpleCard.class);
            } else if (cardNameToType.get(cardName).equals(CARD_TYPE_DISCOUNT)) {
                 tmp = gson.fromJson(jArray.get(i), DiscountCard.class);
            } else if (cardNameToType.get(cardName).equals(CARD_TYPE_CUMULATIVE_COIN)) {
                 tmp = gson.fromJson(jArray.get(i), CumulativeCard.class);
            } else if (cardNameToType.get(cardName).equals(CARD_TYPE_MAGIC)) {
                 tmp = gson.fromJson(jArray.get(i), SimpleCard.class);
            } else {
                System.out.println("Unrecognized Card Type for the card: " + cardName);
            }
            
            cards.add(tmp);
            
        }
        
        ListIterator<Card> iterator = cards.listIterator();
        System.out.println("-------------------" + deckType + "---------------");
        while(iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
        Deck deck = new Deck(cards);
        return deck;
    }
    
}
