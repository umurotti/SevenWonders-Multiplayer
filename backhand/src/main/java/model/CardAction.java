package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author AkinParkan, OmerFarukKurklu
 * @version 0.2
 */
public class CardAction implements Serializable {
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
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CardAction)) {
            return false;
        }
        CardAction action = (CardAction) o;
        return choice == action.choice && Objects.equals(leftTrade, action.leftTrade) && Objects.equals(rightTrade, action.rightTrade) && cardNo == action.cardNo && Objects.equals(wonderID, action.wonderID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(choice, leftTrade, rightTrade, cardNo, wonderID);
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