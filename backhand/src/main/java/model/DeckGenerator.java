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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author umur
 */
public class DeckGenerator {
    private final String DECK_PATH = "/home/umur/Desktop/";
//    private final String DECK_PATH = "/home/ec2-user/";
    private final String DECK_EXTENSION = ".json";
    
    private ObjectMapper mapper;
    private File fl;
    
    public DeckGenerator() throws FileNotFoundException, IOException {
        
            mapper = new ObjectMapper();
//        ListIterator<Card> 
//                iterator = readValues.listIterator(); 
//        System.out.println("-------------------");
//        while(iterator.hasNext()) {
//            System.out.println(iterator.next().toString());
//        }
        
    }
    
    public Deck generateDeck(String deckType) throws IOException {
        List<Card> cards = mapper.readValue(new File(DECK_PATH + deckType + DECK_EXTENSION), new TypeReference<List<Card>>() { });
        ListIterator<Card> 
        iterator = cards.listIterator();
        System.out.println("-------------------" + deckType + "---------------");
        while(iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
        Deck deck = new Deck(cards);
        return deck;
    }
    
}
