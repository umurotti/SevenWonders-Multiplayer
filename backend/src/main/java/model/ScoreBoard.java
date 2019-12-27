/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;

/**
 * @author OmerFarukKurklu
 * @version 0.1
 */
public class ScoreBoard {

    private HashMap<String, Integer> totalScores;
    private HashMap<String, WonderBoard> wonderBoards;

    public ScoreBoard(/*HashMap<String, Integer> totalScores, */HashMap<String, WonderBoard> wonderBoards) {
        // this.totalScores = totalScores;
        this.totalScores = new HashMap<>();
        this.wonderBoards = wonderBoards;
    }

    public boolean calculateScores() {
        for (WonderBoard wb : this.wonderBoards.values()) {
            int totalScore = 0;
            totalScore = calculateCoinPoints(wb) + calculateMilitaryPoints(wb) + calculateSciencePoints(wb) + calculateTradeCardPoints(wb);
            totalScore += wb.getSources().get("victoryPoint");
            totalScores.put(wb.getName(), totalScore);
        }
        return true;
    }

    private int calculateMilitaryPoints(WonderBoard wb) {
        int mTokens = wb.getMilitaryTokens();
        //return (mTokens[0] * 1) + (mTokens[1] * 3) + (mTokens[2] * 5) - wb.getDefeatTokens();
        return mTokens - wb.getDefeatTokens();
    }

    private int calculateCoinPoints(WonderBoard wb) {
        return wb.getSources().get("coin") / 3;
    }

    private int calculateTradeCardPoints(WonderBoard wb) {
        HashMap<String, Card> builtCards = wb.getBuiltCards();
        for (Card card : builtCards.values()) {
            if (card instanceof CumulativeCard) {
                CumulativeCard cumCard = (CumulativeCard) card;
                if (cumCard.getCoin() != 0 && cumCard.getvPoint() != 0) {
                    int amount = 0;
                    for (String dependency : cumCard.getDependencies()) {
                        if (dependency.equals("stage")) {
                            if (cumCard.getInvolve().equals("own")) {
                                amount += wb.getCurrentStage();
                            } else if (cumCard.getInvolve().equals("neigbor")) {
//                                amount += wb.getLeftNeighbor().getCurrentStage();
                                amount += getLeftNeighbor(wb).getCurrentStage();

                                //amount += wb.getRightNeighbor().getCurrentStage();
                                amount += getRightNeighbor(wb).getCurrentStage();
                            } else if (cumCard.getInvolve().equals("both")) {
                                amount += wb.getCurrentStage();
//                                amount += wb.getLeftNeighbor().getCurrentStage();
                                amount += getLeftNeighbor(wb).getCurrentStage();
//                                amount += wb.getRightNeighbor().getCurrentStage();
                                amount += getRightNeighbor(wb).getCurrentStage();
                            }
                        } else if (dependency.equals("defeatTokens")) {
                            if (cumCard.getInvolve().equals("own")) {
                                amount += wb.getDefeatTokens();
                            } else if (cumCard.getInvolve().equals("neigbor")) {
//                                amount += wb.getLeftNeighbor().getDefeatTokens();
                                amount += getLeftNeighbor(wb).getDefeatTokens();
                                //amount += wb.getRightNeighbor().getDefeatTokens();
                                amount += getRightNeighbor(wb).getDefeatTokens();
                            } else if (cumCard.getInvolve().equals("both")) {
                                amount += wb.getDefeatTokens();
//                                amount += wb.getLeftNeighbor().getDefeatTokens();
                                amount += getLeftNeighbor(wb).getDefeatTokens();
//                                amount += wb.getRightNeighbor().getDefeatTokens();,
                                amount += getRightNeighbor(wb).getDefeatTokens();
                            }
                        } else {
                            if (cumCard.getInvolve().equals("own")) {
                                for (Card colorCard : wb.getBuiltCards().values()) {
                                    if (colorCard.getColor().equals(dependency)) {
                                        amount++;
                                    }
                                }
                            } else if (cumCard.getInvolve().equals("neigbor")) {
                                for (Card colorCard : getLeftNeighbor(wb).getBuiltCards().values()) {
                                    if (colorCard.getColor().equals(dependency)) {
                                        amount++;
                                    }
                                }
                                for (Card colorCard : getRightNeighbor(wb).getBuiltCards().values()) {
                                    if (colorCard.getColor().equals(dependency)) {
                                        amount++;
                                    }
                                }
                            } else if (cumCard.getInvolve().equals("both")) {
                                for (Card colorCard : wb.getBuiltCards().values()) {
                                    if (colorCard.getColor().equals(dependency)) {
                                        amount++;
                                    }
                                }
                                for (Card colorCard : getLeftNeighbor(wb).getBuiltCards().values()) {
                                    if (colorCard.getColor().equals(dependency)) {
                                        amount++;
                                    }
                                }
                                for (Card colorCard : getRightNeighbor(wb).getBuiltCards().values()) {
                                    if (colorCard.getColor().equals(dependency)) {
                                        amount++;
                                    }
                                }
                            }
                        }
                        return amount * cumCard.getvPoint();
                    }
                }
            }
        }
        return 0;
    }

    private WonderBoard getLeftNeighbor(WonderBoard wb) {
        return wonderBoards.get(wb.getLeftNeighbor());
    }

    public HashMap<String, WonderBoard> getWonderBoards() {
        return wonderBoards;
    }

    public void setWonderBoards(HashMap<String, WonderBoard> wonderBoards) {
        this.wonderBoards = wonderBoards;
    }

    private WonderBoard getRightNeighbor(WonderBoard wb) {
        return wonderBoards.get(wb.getRightNeighbor());
    }

    private int calculateSciencePoints(WonderBoard wb) {
        int gear = wb.getSources().get("gear");
        int tablet = wb.getSources().get("tablet");
        int compass = wb.getSources().get("bcompass");

        return (gear * gear) + (tablet * tablet) + (compass * compass) + (7 * Math.min(gear, Math.min(tablet, compass)));
    }

    public HashMap<String, Integer> getTotalScores() {
        return totalScores;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((totalScores == null) ? 0 : totalScores.hashCode());
        result = prime * result + ((wonderBoards == null) ? 0 : wonderBoards.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ScoreBoard other = (ScoreBoard) obj;
        if (totalScores == null) {
            if (other.totalScores != null) {
                return false;
            }
        } else if (!totalScores.equals(other.totalScores)) {
            return false;
        }
        if (wonderBoards == null) {
            if (other.wonderBoards != null) {
                return false;
            }
        } else if (!wonderBoards.equals(other.wonderBoards)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{"
                + " totalScores='" + getTotalScores() + "'"
                + ", wonderBoards='" + getWonderBoards() + "'"
                + "}";
    }

}
