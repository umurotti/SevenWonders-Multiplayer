/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import sample.CardAction;
import sample.Card;
import sample.Cost;

import java.util.HashMap;
import java.util.List;


/**
 * @author: OmerFarukKurklu
 * @version: 0.1
 */
public class WonderBoard {


    private String leftNeighbor;
    private String rightNeighbor;

    private HashMap<String,Integer> sources; // String: Name of the resource, Integer: Amount of the resource
    private Cost[] stageCosts;
    private List<String> sourcesToCalculate;

    private int currentStage;
    private int diceValue;
    private String name;
    private HashMap<String, Card> builtCards; // String: Name of the Card, Card: instance of the Card
    private CardAction lockedAction;
    private HashMap<String, Integer> leftDiscount;  // String: Name of the resource, Integer: price of the resource from left neighbor
    private HashMap<String, Integer> rightDiscount; // String: Name of the resource, Integer: price of the resource from left neighbor
    private int[] militaryTokens;
    private int defeatTokens;
    private int handNo;

    public String getRightNeighbor() {
        return rightNeighbor;
    }

    public void setRightNeighbor(String rightNeighbor) {
        this.rightNeighbor = rightNeighbor;
    }

    public HashMap<String, Integer> getSources() {
        return sources;
    }

    public void setSources(HashMap<String, Integer> sources) {
        this.sources = sources;
    }

    public Cost[] getStageCosts() {
        return stageCosts;
    }

    public void setStageCosts(Cost[] stageCosts) {
        this.stageCosts = stageCosts;
    }

    public List<String> getSourcesToCalculate() {
        return sourcesToCalculate;
    }

    public void setSourcesToCalculate(List<String> sourcesToCalculate) {
        this.sourcesToCalculate = sourcesToCalculate;
    }

    public int getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(int currentStage) {
        this.currentStage = currentStage;
    }

    public int getDiceValue() {
        return diceValue;
    }

    public void setDiceValue(int diceValue) {
        this.diceValue = diceValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Card> getBuiltCards() {
        return builtCards;
    }

    public void setBuiltCards(HashMap<String, Card> builtCards) {
        this.builtCards = builtCards;
    }

    public CardAction getLockedAction() {
        return lockedAction;
    }

    public void setLockedAction(CardAction lockedAction) {
        this.lockedAction = lockedAction;
    }

    public HashMap<String, Integer> getLeftDiscount() {
        return leftDiscount;
    }

    public void setLeftDiscount(HashMap<String, Integer> leftDiscount) {
        this.leftDiscount = leftDiscount;
    }

    public HashMap<String, Integer> getRightDiscount() {
        return rightDiscount;
    }

    public void setRightDiscount(HashMap<String, Integer> rightDiscount) {
        this.rightDiscount = rightDiscount;
    }

    public int[] getMilitaryTokens() {
        return militaryTokens;
    }

    public void setMilitaryTokens(int[] militaryTokens) {
        this.militaryTokens = militaryTokens;
    }

    public int getDefeatTokens() {
        return defeatTokens;
    }

    public void setDefeatTokens(int defeatTokens) {
        this.defeatTokens = defeatTokens;
    }

    public int getHandNo() {
        return handNo;
    }

    public void setHandNo(int handNo) {
        this.handNo = handNo;
    }

    public String getLeftNeighbor() {
        return leftNeighbor;
    }

    public void setLeftNeighbor(String leftNeighbor) {
        this.leftNeighbor = leftNeighbor;
    }
}