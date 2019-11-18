/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

//import javafx.event.Event;

import java.net.Socket;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
//@Author:Akin_Parkan
public class Table {
    private String tableID;
    private int noOfPlayers;
    private int age;
    private int turn;
    private Deck age1Deck;
//    private Deck age2Deck;
//    private Deck age3Deck;
    //private Deck magicCardDeck;
    private List<Card> discardedCards;
    private Card [][]hand;
    private List<WonderBoard> diceRollers;
    private String diceRollWinner;
    private Card diceRollCard;
    //private ScoreBoard scoreboard
    private LinkedBlockingQueue<Event> eventQueue;
    private Map<String, Socket> playerChannel;
    private HashMap<String, WonderBoard> wonders;
    private String owner;
    private boolean rollDice;
    private List<String> playerIDs;
    Card[] playable1;
//    Card[] playable2;
//    Card[] playable3;
    //private DeckFactory deckFactory;
    //private TableNotifier notifier


    public Table(String tableID, String owner, Deck age1Deck/*, Deck age2Deck, Deck age3Deck*/) {
        this.tableID = tableID;
        this.noOfPlayers = 1;
        this.owner = owner;
        age = 1;
        playerIDs = new ArrayList<>();
        playerIDs.add(owner);
        this.age1Deck = age1Deck;
        wonders = new HashMap<>();
//        this.age2Deck = age2Deck;
//        this.age3Deck = age3Deck;

        //hand initialize edilecek
    }

    public void playerJoined(String playerID, Socket socket) throws Exception
    {

        if(playerIDs.contains(playerID))
        {
            System.out.println("hataa");
            throw new Exception("playerID is taken ");
        }
        else {
            noOfPlayers++;
            playerIDs.add(playerID);
        }
    }
    public void init(int noOfPlayer, Deck[] decks)
    {
        playable1 = new Card[noOfPlayer *7];
        playable1 = decks[0].prepareCards(noOfPlayer);
//        playable2 = new Card[noOfPlayer *7];
//        playable2 = decks[1].prepareCards(noOfPlayers);
//        playable3 = new Card[noOfPlayer *7];
//        playable3 = decks[2].prepareCards(noOfPlayers);
    }
    public void startTable()
    {
        //assign players to wonders
        for(int a = 0; a< noOfPlayers;a++)
        {
      
            wonders.put(playerIDs.get(a),new WonderBoard(playerIDs.get(a)));
        }
        //assign neighbours
//        for(int a =0; a<noOfPlayers;a++)
//        {
////            if(a==0)
////            {
////                wonders.get(playerIDs.get(0)).setLeftNeighbor(wonders.get(playerIDs.get(noOfPlayers-1)));
////                wonders.get(playerIDs.get(noOfPlayers-1)).setRightNeighbor(wonders.get(playerIDs.get(0)));
////            }
//            /*else {
//                wonders.get(playerIDs.get(a)).setLeftNeighbor(wonders.get(playerIDs.get(a - 1)));
//                wonders.get(playerIDs.get(a - 1)).setRightNeighbor(wonders.get(playerIDs.get(a)));
//            }*/
//            wonders.get(playerIDs.get(a)).setRightNeighbor(wonders.get(playerIDs.get((a + 1) % noOfPlayers)));
//            if(a == 0) {
//                 wonders.get(playerIDs.get(a)).setLeftNeighbor(wonders.get(playerIDs.get((noOfPlayers - 1))));
//            } else {
//                wonders.get(playerIDs.get(a)).setLeftNeighbor(wonders.get(playerIDs.get((a - 1) % noOfPlayers)));
//            }
////            wonders.get(playerIDs.get(a)).setRightNeighbor(wonders.get(playerIDs.get((a + 1))));
////            wonders.get(playerIDs.get(a+1)).setLeftNeighbor(wonders.get(playerIDs.get(a)));
//        }
//       wonders.get(playerIDs.get(0)).setLeftNeighbor(wonders.get(playerIDs.get((noOfPlayers -1 ))));
//        wonders.get(playerIDs.get(noOfPlayers -1)).setRightNeighbor(wonders.get(playerIDs.get(0)));
        Deck []decks = new Deck[3];
        decks[0] = this.age1Deck;
//        decks[1] = this.age2Deck;
//        decks[2] = this.age3Deck;
        init(noOfPlayers,decks);
        hand = new Card[noOfPlayers][7];
        //continue to hand distrib
        List<Card> shuffle1 = Arrays.asList(playable1);
//        List<Card> shuffle2 = Arrays.asList(playable2);
//        List<Card> shuffle3 = Arrays.asList(playable3);
        shuffle1.toArray(playable1);
//        shuffle2.toArray(playable2);
//        shuffle3.toArray(playable3);
        int count = -1;
        int hold = 0;
        for(int a = 0; a < noOfPlayers*7; a++)
        {
            if(a % 7 == 0)
            {
                count++;
                hold = 0;
            }
            hand[count][hold++] = playable1[a];
        }
        playTurn();
    }
    public void rollDice()
    {}
    public void addWonder()
    {

    }
    public void pickMagicCard(String wonderID)
    {}
    public void changeHand()
    {}
   // public getHandRequest(String wonderID)
    //{}
  //  public WonderBoard viewStateRequest()
   // {}
    public void diceRollRequest(String wonderID)
    {}
    public void notifyPlayers()
    {}
    public void playAge()
    {}
    public void lockAction(Action action)
    {}
   // public boolean isPossible(Action action)
    //{}
    public void playTurn()
    {
        //% kullanarak cevir
        //play turn
        System.out.println("deneme");
        for (int i = 0; i < 7; i++) {
        System.out.println(hand[0][i].toString());
        }
        System.out.println("under construction ____BY GRUPUCYUZONDOKUZ");
    }

    public Card[][] getHands() {
        return hand; 
    }

    public HashMap<String, WonderBoard> getWonders() {
        return wonders; 
    }
}
