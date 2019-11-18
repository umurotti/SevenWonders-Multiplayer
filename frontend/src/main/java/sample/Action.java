package sample;
import java.util.Map;

/**
 *
 * @author umur
 */
public class Action {
    private int choice;
    private Map<String,Integer> leftTrade;
    private Map<String,Integer> rightTrade;
    private int cardNo;
    private String wonderID;

    public Action(){

    }
    public Action(int choice, Map<String, Integer> leftTrade, Map<String, Integer> rightTrade, int cardNo, String wonderID) {
        this.choice = choice;
        this.leftTrade = leftTrade;
        this.rightTrade = rightTrade;
        this.cardNo = cardNo;
        this.wonderID = wonderID;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public Map<String, Integer> getLeftTrade() {
        return leftTrade;
    }

    public void setLeftTrade(Map<String, Integer> leftTrade) {
        this.leftTrade = leftTrade;
    }

    public Map<String, Integer> getRightTrade() {
        return rightTrade;
    }

    public void setRightTrade(Map<String, Integer> rightTrade) {
        this.rightTrade = rightTrade;
    }

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public String getWonderID() {
        return wonderID;
    }

    public void setWonderID(String wonderID) {
        this.wonderID = wonderID;
    }
}
