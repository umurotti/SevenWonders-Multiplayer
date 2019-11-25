package model;

import java.net.Socket;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;


/** 
 * @author Akin_Parkan, OmerFarukKurklu
 * @version 0.2
*/

public class Table {
    private String tableID;
    private int noOfPlayers;
    private int age;
    private int turn;
    private Deck age1Deck;
//  private Deck age2Deck;
//  private Deck age3Deck;
//  private Deck magicCardDeck;
    private List<Card> discardedCards;
    private Card [][]hand;
    private List<WonderBoard> diceRollers;
    private String diceRollWinner;
    private Card diceRollCard;
//  private ScoreBoard scoreboard
    private LinkedBlockingQueue<Event> eventQueue;
    private Map<String, Socket> playerChannel;
    private HashMap<String, WonderBoard> wonders;
    private String owner;
    private boolean rollDice;
    private List<String> playerIDs;
    Card[] playable1;
//  Card[] playable2;
//  Card[] playable3;
//  private DeckFactory deckFactory;
//  private TableNotifier notifier


    public Table(String tableID, String owner, Deck age1Deck/*, Deck age2Deck, Deck age3Deck*/) {
        this.tableID = tableID;
        this.noOfPlayers = 1;
        this.owner = owner;
        age = 1;
        playerIDs = new ArrayList<>();
        playerIDs.add(owner);
        this.age1Deck = age1Deck;
        wonders = new HashMap<>();
//      this.age2Deck = age2Deck;
//      this.age3Deck = age3Deck;
//      hand initialize edilecek
    }

    public Table() {
    }

    public Table(String tableID, int noOfPlayers, int age, int turn, Deck age1Deck, List<Card> discardedCards, Card[][] hand, List<WonderBoard> diceRollers, String diceRollWinner, Card diceRollCard, LinkedBlockingQueue<Event> eventQueue, Map<String,Socket> playerChannel, HashMap<String,WonderBoard> wonders, String owner, boolean rollDice, List<String> playerIDs, Card[] playable1) {
        this.tableID = tableID;
        this.noOfPlayers = noOfPlayers;
        this.age = age;
        this.turn = turn;
        this.age1Deck = age1Deck;
        this.discardedCards = discardedCards;
        this.hand = hand;
        this.diceRollers = diceRollers;
        this.diceRollWinner = diceRollWinner;
        this.diceRollCard = diceRollCard;
        this.eventQueue = eventQueue;
        this.playerChannel = playerChannel;
        this.wonders = wonders;
        this.owner = owner;
        this.rollDice = rollDice;
        this.playerIDs = playerIDs;
        this.playable1 = playable1;
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
//      playable2 = new Card[noOfPlayer *7];
//      playable2 = decks[1].prepareCards(noOfPlayers);
//      playable3 = new Card[noOfPlayer *7];
//      playable3 = decks[2].prepareCards(noOfPlayers);
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
//      wonders.get(playerIDs.get(0)).setLeftNeighbor(wonders.get(playerIDs.get((noOfPlayers -1 ))));
//      wonders.get(playerIDs.get(noOfPlayers -1)).setRightNeighbor(wonders.get(playerIDs.get(0)));
        Deck []decks = new Deck[3];
        decks[0] = this.age1Deck;
//      decks[1] = this.age2Deck;
//      decks[2] = this.age3Deck;
        init(noOfPlayers,decks);
        hand = new Card[noOfPlayers][7];
//      continue to hand distrib
        List<Card> shuffle1 = Arrays.asList(playable1);
//      List<Card> shuffle2 = Arrays.asList(playable2);
//      List<Card> shuffle3 = Arrays.asList(playable3);
        shuffle1.toArray(playable1);
//      shuffle2.toArray(playable2);
//      shuffle3.toArray(playable3);
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
    public void rollDice(){
    }
    public void addWonder(){
    }
    public void pickMagicCard(String wonderID){
    }
    public void changeHand(){
    }
    public getHandRequest(String wonderID){
       return null;
    }
    public WonderBoard viewStateRequest(){
        return null;
    }
    public void diceRollRequest(String wonderID){
    }
    public void notifyPlayers(){
    }
    public void playAge(){
    }

    public void lockAction(Action action){
        this.getWonders().get(action.getWonderID()).setLockedAction(action);
    }
    
    public boolean isPossible(Action action) {
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
            if (builtCards.containsKey(card.getFreeBuilding())) { // If the free building exist in the Wonder, return true.
                return true;
            }
            Cost cost = card.getCost();
            return costCheck(cost, wbSources, leftTrade, rightTrade);
        }
        if (choice == 2) {  // Wonder Stage building requires cost checking
            if (wb.getCurrentStage() == 3) {
                return false;
            }
            Cost cost = wb.getStageCosts()[wb.getCurrentStage() + 1];
            return costCheck(cost, wbSources, leftTrade, rightTrade);
        }
        return false;
    }    

    private boolean costCheck(Cost cost, HashMap<String, Integer> wbSources, HashMap<String, Integer> leftTrade, HashMap<String, Integer> rightTrade){
        HashMap<String, Integer> costs = cost.getCost();
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
    }

    private void playAction(WonderBoard wb){
        Action action = wb.getLockedAction();
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

    public String getTableID() {
        return this.tableID;
    }

    public void setTableID(String tableID) {
        this.tableID = tableID;
    }

    public int getNoOfPlayers() {
        return this.noOfPlayers;
    }

    public void setNoOfPlayers(int noOfPlayers) {
        this.noOfPlayers = noOfPlayers;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTurn() {
        return this.turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public Deck getAge1Deck() {
        return this.age1Deck;
    }

    public void setAge1Deck(Deck age1Deck) {
        this.age1Deck = age1Deck;
    }

    public List<Card> getDiscardedCards() {
        return this.discardedCards;
    }

    public void setDiscardedCards(List<Card> discardedCards) {
        this.discardedCards = discardedCards;
    }

    public Card[][] getHand() {
        return this.hand;
    }

    public void setHand(Card[][] hand) {
        this.hand = hand;
    }

    public List<WonderBoard> getDiceRollers() {
        return this.diceRollers;
    }

    public void setDiceRollers(List<WonderBoard> diceRollers) {
        this.diceRollers = diceRollers;
    }

    public String getDiceRollWinner() {
        return this.diceRollWinner;
    }

    public void setDiceRollWinner(String diceRollWinner) {
        this.diceRollWinner = diceRollWinner;
    }

    public Card getDiceRollCard() {
        return this.diceRollCard;
    }

    public void setDiceRollCard(Card diceRollCard) {
        this.diceRollCard = diceRollCard;
    }

    public LinkedBlockingQueue<Event> getEventQueue() {
        return this.eventQueue;
    }

    public void setEventQueue(LinkedBlockingQueue<Event> eventQueue) {
        this.eventQueue = eventQueue;
    }

    public Map<String,Socket> getPlayerChannel() {
        return this.playerChannel;
    }

    public void setPlayerChannel(Map<String,Socket> playerChannel) {
        this.playerChannel = playerChannel;
    }

    public HashMap<String,WonderBoard> getWonders() {
        return this.wonders;
    }

    public void setWonders(HashMap<String,WonderBoard> wonders) {
        this.wonders = wonders;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean isRollDice() {
        return this.rollDice;
    }

    public boolean getRollDice() {
        return this.rollDice;
    }

    public void setRollDice(boolean rollDice) {
        this.rollDice = rollDice;
    }

    public List<String> getPlayerIDs() {
        return this.playerIDs;
    }

    public void setPlayerIDs(List<String> playerIDs) {
        this.playerIDs = playerIDs;
    }

    public Card[] getPlayable1() {
        return this.playable1;
    }

    public void setPlayable1(Card[] playable1) {
        this.playable1 = playable1;
    }

    public Table tableID(String tableID) {
        this.tableID = tableID;
        return this;
    }

    public Table noOfPlayers(int noOfPlayers) {
        this.noOfPlayers = noOfPlayers;
        return this;
    }

    public Table age(int age) {
        this.age = age;
        return this;
    }

    public Table turn(int turn) {
        this.turn = turn;
        return this;
    }

    public Table age1Deck(Deck age1Deck) {
        this.age1Deck = age1Deck;
        return this;
    }

    public Table discardedCards(List<Card> discardedCards) {
        this.discardedCards = discardedCards;
        return this;
    }

    public Table hand(Card[][] hand) {
        this.hand = hand;
        return this;
    }

    public Table diceRollers(List<WonderBoard> diceRollers) {
        this.diceRollers = diceRollers;
        return this;
    }

    public Table diceRollWinner(String diceRollWinner) {
        this.diceRollWinner = diceRollWinner;
        return this;
    }

    public Table diceRollCard(Card diceRollCard) {
        this.diceRollCard = diceRollCard;
        return this;
    }

    public Table eventQueue(LinkedBlockingQueue<Event> eventQueue) {
        this.eventQueue = eventQueue;
        return this;
    }

    public Table playerChannel(Map<String,Socket> playerChannel) {
        this.playerChannel = playerChannel;
        return this;
    }

    public Table wonders(HashMap<String,WonderBoard> wonders) {
        this.wonders = wonders;
        return this;
    }

    public Table owner(String owner) {
        this.owner = owner;
        return this;
    }

    public Table playerIDs(List<String> playerIDs) {
        this.playerIDs = playerIDs;
        return this;
    }

    public Table playable1(Card[] playable1) {
        this.playable1 = playable1;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Table)) {
            return false;
        }
        Table table = (Table) o;
        return Objects.equals(tableID, table.tableID) && noOfPlayers == table.noOfPlayers && age == table.age && turn == table.turn && Objects.equals(age1Deck, table.age1Deck) && Objects.equals(discardedCards, table.discardedCards) && Objects.equals(hand, table.hand) && Objects.equals(diceRollers, table.diceRollers) && Objects.equals(diceRollWinner, table.diceRollWinner) && Objects.equals(diceRollCard, table.diceRollCard) && Objects.equals(eventQueue, table.eventQueue) && Objects.equals(playerChannel, table.playerChannel) && Objects.equals(wonders, table.wonders) && Objects.equals(owner, table.owner) && rollDice == table.rollDice && Objects.equals(playerIDs, table.playerIDs) && Objects.equals(playable1, table.playable1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableID, noOfPlayers, age, turn, age1Deck, discardedCards, hand, diceRollers, diceRollWinner, diceRollCard, eventQueue, playerChannel, wonders, owner, rollDice, playerIDs, playable1);
    }

    @Override
    public String toString() {
        return "{" +
            " tableID='" + getTableID() + "'" +
            ", noOfPlayers='" + getNoOfPlayers() + "'" +
            ", age='" + getAge() + "'" +
            ", turn='" + getTurn() + "'" +
            ", age1Deck='" + getAge1Deck() + "'" +
            ", discardedCards='" + getDiscardedCards() + "'" +
            ", hand='" + getHand() + "'" +
            ", diceRollers='" + getDiceRollers() + "'" +
            ", diceRollWinner='" + getDiceRollWinner() + "'" +
            ", diceRollCard='" + getDiceRollCard() + "'" +
            ", eventQueue='" + getEventQueue() + "'" +
            ", playerChannel='" + getPlayerChannel() + "'" +
            ", wonders='" + getWonders() + "'" +
            ", owner='" + getOwner() + "'" +
            ", rollDice='" + isRollDice() + "'" +
            ", playerIDs='" + getPlayerIDs() + "'" +
            ", playable1='" + getPlayable1() + "'" +
            "}";
    }

}
