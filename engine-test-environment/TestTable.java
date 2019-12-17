import java.util.HashMap;

class TestTable{

    private Card [][] hands;
    HashMap<String, WonderBoard> wonders;

    public TestTable() {
    }

    public TestTable(Card[][] hands, HashMap<String,WonderBoard> wonders) {
        this.hands = hands;
        this.wonders = wonders;
    }
    
    public void playFor(String wbID, Action action){ // Not an actual function for Table class. Only for Test purposes.
        WonderBoard wb = this.getWonders().get(wbID);
        wb.setLockedAction(action);
        playAction(wb);
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

      public boolean isPossible(Action action) {
        WonderBoard wb = this.getWonders().get(action.getWonderID());
        HashMap<String, Integer> wbSources = wb.getSources();
        HashMap<String, Integer> leftTrade = action.getLeftTrade();
        HashMap<String, Integer> rightTrade = action.getRightTrade();

        // null checking
        if (this.getHands()[wb.getHandNo()][action.getCardNo()] == null) {
            return false;
        }
        
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

      public void setHand(Card [][] hands) {
          this.hands = hands;
      }

      public Card [][] getHands(){
          return this.hands;
      }

      public void setWonders(HashMap<String, WonderBoard> wonders){
          this.wonders = wonders;
      }

      public HashMap<String, WonderBoard> getWonders(){
          return this.wonders;
      }
}