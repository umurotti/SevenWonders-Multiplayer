import java.util.HashMap;
import java.util.Objects;

/** 
 * @author: OmerFarukKurklu
 * @version: 0.1
*/
class WonderBoard {
    private WonderBoard leftNeighbor;
    private WonderBoard rightNeighbor;
    private HashMap<String,Integer> sources; // String: Name of the resource, Integer: Amount of the resource
    private Cost[] stageCosts;
    private int currentStage;
    private int diceValue;
    private String name;
    private HashMap<String, Card> builtCards; // String: Name of the Card, Card: instance of the Card
    private Action lockedAction;
    private HashMap<String, Integer> leftDiscount;  // String: Name of the resource, Integer: price of the resource from left neighbor
    private HashMap<String, Integer> rightDiscount; // String: Name of the resource, Integer: price of the resource from left neighbor
    private int[] militaryTokens;
    private int defeatTokens;
    private int handNo;

    public WonderBoard() {
        // militaryTokens[0]: 1st age military victories, militaryTokens[1]: 2nd, militaryTokens[2]: 3rd.
        militaryTokens = new int[3];

        // stageCosts[0]: 1st stage cost, stageCosts[1]: 2nd, stageCosts[2]: 3rd.
        stageCosts = new Cost[3];

        sources = new HashMap<String,Integer>()
        {{
            put("wood", 0);
            put("stone", 0);
            put("clay", 0);
            put("ore", 0);
            put("loom",0);
            put("papyrus", 0);
            put("glass", 0);
            put("compass", 0);
            put("tablet", 0);
            put("gear", 0);
            put("coin", 0);
            put("shield", 0);
            put("victoryPoint", 0);
        }};
        builtCards = new HashMap<String,Card>();

        leftDiscount = new HashMap<String, Integer>()
        {{
            put("wood", 2);
            put("stone", 2);
            put("clay", 2);
            put("ore", 2);
            put("loom", 2);
            put("papyrus", 2);
            put("glass", 2);
        }};
        rightDiscount = new HashMap<String, Integer>()
        {{
            put("wood", 2);
            put("stone", 2);
            put("clay", 2);
            put("ore", 2);
            put("loom", 2);
            put("papyrus", 2);
            put("glass", 2);
        }};
    }

    public WonderBoard(WonderBoard leftNeighbor, WonderBoard rightNeighbor, HashMap<String,Integer> sources, Cost[] stageCosts, int currentStage, int diceValue, String name, HashMap<String,Card> builtCards, Action lockedAction, HashMap<String,Integer> leftDiscount, HashMap<String,Integer> rightDiscount, int[] militaryTokens, int defeatTokens, int handNo) {
        this.leftNeighbor = leftNeighbor;
        this.rightNeighbor = rightNeighbor;
        this.sources = sources;
        this.stageCosts = stageCosts;
        this.currentStage = currentStage;
        this.diceValue = diceValue;
        this.name = name;
        this.builtCards = builtCards;
        this.lockedAction = lockedAction;
        this.leftDiscount = leftDiscount;
        this.rightDiscount = rightDiscount;
        this.militaryTokens = militaryTokens;
        this.defeatTokens = defeatTokens;
        this.handNo = handNo;
    }

    public WonderBoard getLeftNeighbor() {
        return this.leftNeighbor;
    }

    public void setLeftNeighbor(WonderBoard leftNeighbor) {
        this.leftNeighbor = leftNeighbor;
    }

    public WonderBoard getRightNeighbor() {
        return this.rightNeighbor;
    }

    public void setRightNeighbor(WonderBoard rightNeighbor) {
        this.rightNeighbor = rightNeighbor;
    }

    public HashMap<String,Integer> getSources() {
        return this.sources;
    }

    public void setSources(HashMap<String,Integer> sources) {
        this.sources = sources;
    }

    public Cost[] getStageCosts() {
        return this.stageCosts;
    }

    public void setStageCosts(Cost[] stageCosts) {
        this.stageCosts = stageCosts;
    }

    public int getCurrentStage() {
        return this.currentStage;
    }

    public void setCurrentStage(int currentStage) {
        this.currentStage = currentStage;
    }

    public int getDiceValue() {
        return this.diceValue;
    }

    public void setDiceValue(int diceValue) {
        this.diceValue = diceValue;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String,Card> getBuiltCards() {
        return this.builtCards;
    }

    public void setBuiltCards(HashMap<String,Card> builtCards) {
        this.builtCards = builtCards;
    }

    public Action getLockedAction() {
        return this.lockedAction;
    }

    public void setLockedAction(Action lockedAction) {
        this.lockedAction = lockedAction;
    }

    public HashMap<String,Integer> getLeftDiscount() {
        return this.leftDiscount;
    }

    public void setLeftDiscount(HashMap<String,Integer> leftDiscount) {
        this.leftDiscount = leftDiscount;
    }

    public HashMap<String,Integer> getRightDiscount() {
        return this.rightDiscount;
    }

    public void setRightDiscount(HashMap<String,Integer> rightDiscount) {
        this.rightDiscount = rightDiscount;
    }

    public int[] getMilitaryTokens() {
        return this.militaryTokens;
    }

    public void setMilitaryTokens(int[] militaryTokens) {
        this.militaryTokens = militaryTokens;
    }

    public int getDefeatTokens() {
        return this.defeatTokens;
    }

    public void setDefeatTokens(int defeatTokens) {
        this.defeatTokens = defeatTokens;
    }

    public int getHandNo() {
        return this.handNo;
    }

    public void setHandNo(int handNo) {
        this.handNo = handNo;
    }

    public WonderBoard leftNeighbor(WonderBoard leftNeighbor) {
        this.leftNeighbor = leftNeighbor;
        return this;
    }

    public WonderBoard rightNeighbor(WonderBoard rightNeighbor) {
        this.rightNeighbor = rightNeighbor;
        return this;
    }

    public WonderBoard sources(HashMap<String,Integer> sources) {
        this.sources = sources;
        return this;
    }

    public WonderBoard stageCosts(Cost[] stageCosts) {
        this.stageCosts = stageCosts;
        return this;
    }

    public WonderBoard currentStage(int currentStage) {
        this.currentStage = currentStage;
        return this;
    }

    public WonderBoard diceValue(int diceValue) {
        this.diceValue = diceValue;
        return this;
    }

    public WonderBoard name(String name) {
        this.name = name;
        return this;
    }

    public WonderBoard builtCards(HashMap<String,Card> builtCards) {
        this.builtCards = builtCards;
        return this;
    }

    public WonderBoard lockedAction(Action lockedAction) {
        this.lockedAction = lockedAction;
        return this;
    }

    public WonderBoard leftDiscount(HashMap<String,Integer> leftDiscount) {
        this.leftDiscount = leftDiscount;
        return this;
    }

    public WonderBoard rightDiscount(HashMap<String,Integer> rightDiscount) {
        this.rightDiscount = rightDiscount;
        return this;
    }

    public WonderBoard militaryTokens(int[] militaryTokens) {
        this.militaryTokens = militaryTokens;
        return this;
    }

    public WonderBoard defeatTokens(int defeatTokens) {
        this.defeatTokens = defeatTokens;
        return this;
    }

    public WonderBoard handNo(int handNo) {
        this.handNo = handNo;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof WonderBoard)) {
            return false;
        }
        WonderBoard wonderBoard = (WonderBoard) o;
        return Objects.equals(leftNeighbor, wonderBoard.leftNeighbor) && Objects.equals(rightNeighbor, wonderBoard.rightNeighbor) && Objects.equals(sources, wonderBoard.sources) && Objects.equals(stageCosts, wonderBoard.stageCosts) && currentStage == wonderBoard.currentStage && diceValue == wonderBoard.diceValue && Objects.equals(name, wonderBoard.name) && Objects.equals(builtCards, wonderBoard.builtCards) && Objects.equals(lockedAction, wonderBoard.lockedAction) && Objects.equals(leftDiscount, wonderBoard.leftDiscount) && Objects.equals(rightDiscount, wonderBoard.rightDiscount) && Objects.equals(militaryTokens, wonderBoard.militaryTokens) && defeatTokens == wonderBoard.defeatTokens && handNo == wonderBoard.handNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftNeighbor, rightNeighbor, sources, stageCosts, currentStage, diceValue, name, builtCards, lockedAction, leftDiscount, rightDiscount, militaryTokens, defeatTokens, handNo);
    }

    @Override
    public String toString() {
        return "{" +
            " leftNeighbor='" + getLeftNeighbor() + "'" +
            ", rightNeighbor='" + getRightNeighbor() + "'" +
            ", sources='" + getSources() + "'" +
            ", stageCosts='" + getStageCosts() + "'" +
            ", currentStage='" + getCurrentStage() + "'" +
            ", diceValue='" + getDiceValue() + "'" +
            ", name='" + getName() + "'" +
            ", builtCards='" + getBuiltCards() + "'" +
            ", lockedAction='" + getLockedAction() + "'" +
            ", leftDiscount='" + getLeftDiscount() + "'" +
            ", rightDiscount='" + getRightDiscount() + "'" +
            ", militaryTokens='" + getMilitaryTokens() + "'" +
            ", defeatTokens='" + getDefeatTokens() + "'" +
            ", handNo='" + getHandNo() + "'" +
            "}";
    }

}