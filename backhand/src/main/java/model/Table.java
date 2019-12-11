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
    private int noOfActions; // how many people played in that turn
    private Deck age1Deck;
//    private Deck age2Deck;+
//    private Deck age3Deck;+
    //private Deck magicCardDeck;+
    private List<Card> discardedCards;
    private Card [][] hand;
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
    private HandContainer trans;
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
        trans = new HandContainer();
        
//        this.age2Deck = age2Deck;
//        this.age3Deck = age3Deck;

        //hand initialize edilecek
    }

    public void playerJoined(String playerID, Socket socket) throws Exception
    {
        if(noOfPlayers <7)
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
        else
        {
             throw new Exception("room is full");
        }
    }
    public void init(int noOfPlayer, Deck[] decks)
    {
        int hold = noOfPlayer * 7;
        playable1 = new Card[hold];
        playable1 = decks[0].prepareCards(noOfPlayer);
//        playable2 = new Card[noOfPlayer *7];
//        playable2 = decks[1].prepareCards(noOfPlayers);
//        playable3 = new Card[noOfPlayer *7];
//        playable3 = decks[2].prepareCards(noOfPlayers);
    }
    public void startTable()
    {
         addWonder();
        //assign neighbours
        for(int a =0; a<noOfPlayers -1;a++)
        {
            WonderBoard wb1 =  wonders.get(playerIDs.get(a));
            WonderBoard wb2 =  wonders.get(playerIDs.get(a +1));

                wb1.setLeftNeighbor(wb2.getName());
                wb2.setRightNeighbor(wb1.getName());

        }
            WonderBoard wb1 =  wonders.get(playerIDs.get(0));
            WonderBoard wb2 =  wonders.get(playerIDs.get(noOfPlayers-1));
                wb2.setLeftNeighbor(wb1.getName());
                wb1.setRightNeighbor(wb2.getName());
                System.out.println(wb1);
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
        Collections.shuffle(shuffle1);
        shuffle1.toArray(playable1);
//        shuffle2.toArray(playable2);
//        shuffle3.toArray(playable3);
        int count = -1;
        int hold = 0;
        for(int a = 0; a < noOfPlayers*7; a++)
        {
            if(a % 7 == 0 || hold == 7)
            {
                count++;
                hold = 0;
            }
            hand[count][hold++] = playable1[a];
        }
        
    }
    public void rollDice()
    {}
    public void addWonder()
    {
        //assign players to wonders
        for(int a = 0; a< noOfPlayers;a++)
        {
      
            wonders.put(playerIDs.get(a),new WonderBoard(playerIDs.get(a)));
        }
    }
    public void pickMagicCard(String wonderID)
    {}
    public void changeHand()
    {}

    public void diceRollRequest(String wonderID)
    {}
    public void notifyPlayers()
    {}
    public void playAge()
    {}

    public void lockAction(CardAction action){
        this.getWonders().get(action.getWonderID()).setLockedAction(action);
    }
    
    public boolean isPossible(CardAction action) {
        WonderBoard wb = this.getWonders().get(action.getWonderID());
        HashMap<String, Integer> wbSources = wb.getSources();
        HashMap<String, Integer> leftTrade = action.getLeftTrade();
        HashMap<String, Integer> rightTrade = action.getRightTrade();
        
        int choice = action.getChoice();
        if (choice == 0) { // Discard is always possible
            return true;
        }
        if (choice == 1) {  // Card building reqires checking
            HashMap<String, Card> builtCards = wb.getBuiltCards();
            Card card = this.getHands()[wb.getHandNo()][action.getCardNo()];
            if (builtCards.containsKey(card.getName())){ // If the card is already built, return false.
                return false;
            }
            if (builtCards.containsKey(card.getFreeBuildings())) { // If the free building exist in the Wonder, return true.
                return true;
            }
            Cost cost = card.getCost();
            return costCheck(cost, wbSources, leftTrade, rightTrade);
        }
        if (choice == 2) {  // Wonder Stage building requires cost checking
            if (wb.getCurrentStage() == 3) {
                return false;
            }
            Cost cost = wb.getStageCosts()[wb.getCurrentStage()];
            if(costCheck(cost, wbSources, leftTrade, rightTrade)) {
                lockAction(action);
                noOfActions++;
                
                if(noOfActions == noOfPlayers) {
                    playTurn();
                }
                return true;
            }
            else
                return false;
        }
        return false;
    }    

    private boolean costCheck(Cost cost, HashMap<String, Integer> wbSources, HashMap<String, Integer> leftTrade, HashMap<String, Integer> rightTrade){
        HashMap<String, Integer> costs = cost.getCost();
        if(!(cost.getCost().keySet().containsAll(leftTrade.keySet()) ) || !(cost.getCost().keySet().containsAll(rightTrade.keySet()) ) ) {
            return false;
        }
        for (String material : costs.keySet()){
            // If the Wonder already has materials for the card, The Trades map for that material should be zero or key should not exist.
            if ((wbSources.get(material) >= costs.get(material))) {
                if ((leftTrade.containsKey(material) && (leftTrade.get(material) != 0)) ||
                (rightTrade.containsKey(material) && (rightTrade.get(material) != 0))) {
                    return false;
                }
            }
            // If the Wonder does not have enough materials, its materials plus the trade materials should equal to cost.
            else {
                if(leftTrade.containsKey(material) && rightTrade.containsKey(material)){
                    if (wbSources.get(material) + leftTrade.get(material) + rightTrade.get(material) != costs.get(material)){
                        return false;
                    }
                }
                else if(leftTrade.containsKey(material)){
                    if ((wbSources.get(material) + leftTrade.get(material)) != costs.get(material)) {
                        return false;
                    }
                }
                else if(rightTrade.containsKey(material)){
                    if ((wbSources.get(material) + rightTrade.get(material)) != costs.get(material)) {
                        return false;
                    }
                }
                else {
                    return false;
                }
            }
        }
        return true;
      }


    public void playTurn()
    {
        HashMap<String, WonderBoard> wonders = this.getWonders();
        for (String wbID : wonders.keySet()){
            this.playAction(wonders.get(wbID));
            wonders.get(wbID).setHandNo((wonders.get(wbID).getHandNo() + 1)%7);
        }
        turn++;
        noOfActions = 0;
    }

    private void playAction(WonderBoard wb){
        CardAction action = wb.getLockedAction();
        int choice = action.getChoice();

        // Get the hands, handNo of Wonder and the the played Card.
        Card [][] hands = this.getHands();
        int wbHandNo = wb.getHandNo();
        int cardNo = action.getCardNo();

        // Discard
        if (choice == 0) {
            // Update te sources of the WonderBoard.
            HashMap<String, Integer> wbSources = wb.getSources();
            wbSources.put("coin", wbSources.get("coin") + 3);
            wb.setSources(wbSources);
        }

        // Build Card
        if (choice == 1) {
            // Get the card from hand.
            Card card = hands[wbHandNo][cardNo];
            card.play(wb, "");
        }

        // Build Wonder stage. For this iteration our stages only give +3 VP, 0 VP, +7 VP.
        if (choice == 2) {
            int currentStage = wb.getCurrentStage() + 1;
            wb.setCurrentStage(currentStage);
            HashMap<String, Integer> wbSources = wb.getSources();
            if (currentStage == 1){
                wbSources.put("victoryPoint", wbSources.get("victoryPoint") + 3);
            }
            if (currentStage == 2){
            }
            if (currentStage == 3){
                wbSources.put("victoryPoint", wbSources.get("victoryPoint") + 7);
            }
            wb.setSources(wbSources);
        }

        // Remove the card from the hand and update hands.
        hands[wbHandNo][cardNo] = null;
        this.setHand(hands);
    }
    public Card[][] getHands() {
        return hand; 
    }
    
    
    public void setHand(Card[][] hand) {
        this.hand = hand;
    }

    
    public HashMap<String, WonderBoard> getWonders() {
        return wonders; 
    }
    public HandContainer getTransfer() {
        trans = new HandContainer(hand,noOfPlayers,playerIDs);
        return trans;
    }
}
