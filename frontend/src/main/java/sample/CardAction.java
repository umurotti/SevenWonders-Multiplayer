package sample;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author umur
 */
public class CardAction {
    private int choice;
    private HashMap<String,Integer> leftTrade;
    private HashMap<String,Integer> rightTrade;
    private int cardNo;
    private String wonderID;

    public CardAction() {

    }

    public CardAction(int choice, HashMap<String,Integer> leftTrade, HashMap<String,Integer> rightTrade, int cardNo, String wonderID) {
        this.choice = choice;
        this.leftTrade = leftTrade;
        this.rightTrade = rightTrade;
        this.cardNo = cardNo;
        this.wonderID = wonderID;
    }

    public int getChoice() {
        return this.choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public HashMap<String,Integer> getLeftTrade() {
        return this.leftTrade;
    }

    public void setLeftTrade(HashMap<String,Integer> leftTrade) {
        this.leftTrade = leftTrade;
    }

    public HashMap<String,Integer> getRightTrade() {
        return this.rightTrade;
    }

    public void setRightTrade(HashMap<String,Integer> rightTrade) {
        this.rightTrade = rightTrade;
    }

    public int getCardNo() {
        return this.cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public String getWonderID() {
        return this.wonderID;
    }

    public void setWonderID(String wonderID) {
        this.wonderID = wonderID;
    }

    public CardAction choice(int choice) {
        this.choice = choice;
        return this;
    }

    public CardAction leftTrade(HashMap<String,Integer> leftTrade) {
        this.leftTrade = leftTrade;
        return this;
    }

    public CardAction rightTrade(HashMap<String,Integer> rightTrade) {
        this.rightTrade = rightTrade;
        return this;
    }

    public CardAction cardNo(int cardNo) {
        this.cardNo = cardNo;
        return this;
    }

    public CardAction wonderID(String wonderID) {
        this.wonderID = wonderID;
        return this;
    }




    @Override
    public String toString() {
        return "{" +
                " choice='" + getChoice() + "'" +
                ", leftTrade='" + getLeftTrade() + "'" +
                ", rightTrade='" + getRightTrade() + "'" +
                ", cardNo='" + getCardNo() + "'" +
                ", wonderID='" + getWonderID() + "'" +
                "}";
    }
}
