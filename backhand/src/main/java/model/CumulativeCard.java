/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.function.IntBinaryOperator;

/**
 * @author OmerFarukKurklu
 * @version 0.1
 */
public class CumulativeCard extends Card {

    private final String involve; // 'own', 'neighbor', 'both'
    private final Set<String> dependencies; // 'brown', 'red', 'yellow', 'green', 'gray', 'blue', 'stage', 'purple', 'defeatTokens'
    private final int coin;
    private final int vPoint;

    public CumulativeCard(String name, String color, Cost cost, int minNoOfPlayers, List<String> freeBuildings,
            String involve, Set<String> dependencies, int coin, int vPoint) {
        super(name, color, cost, minNoOfPlayers, freeBuildings);
        this.involve = involve;
        this.dependencies = dependencies;
        this.coin = coin;
        this.vPoint = vPoint;
    }

    @Override
    void play(WonderBoard wb, String selection, HashMap<String, WonderBoard> wonderboards) {
        int amount = 0;
        for (String dependency : dependencies) {
            if (dependency.equals("stage")) {
                if (involve.equals("own")) {
                    amount += wb.getCurrentStage();
                } else if (involve.equals("neigbor")) {
                    //amount += wb.getLeftNeighbor().getCurrentStage();
                    amount += getLeftNeighbor(wb, wonderboards).getCurrentStage();

//                    amount += wb.getRightNeighbor().getCurrentStage();
                    amount += getRightNeighbor(wb, wonderboards).getCurrentStage();
                } else if (involve.equals("both")) {
                    amount += wb.getCurrentStage();
                    //amount += wb.getLeftNeighbor().getCurrentStage();
                    amount += getLeftNeighbor(wb, wonderboards).getCurrentStage();
//                    amount += wb.getRightNeighbor().getCurrentStage();
                    amount += getRightNeighbor(wb, wonderboards).getCurrentStage();
                }
            } else if (dependency.equals("defeatTokens")) {
                if (involve.equals("own")) {
                    amount += wb.getDefeatTokens();
                } else if (involve.equals("neigbor")) {
                    //amount += wb.getLeftNeighbor().getDefeatTokens();
                    amount += getLeftNeighbor(wb, wonderboards).getDefeatTokens();

//                    amount += wb.getRightNeighbor().getDefeatTokens();
                    amount += getRightNeighbor(wb, wonderboards).getDefeatTokens();
                } else if (involve.equals("both")) {
                    amount += wb.getDefeatTokens();
                    //amount += wb.getLeftNeighbor().getDefeatTokens();
                    amount += getLeftNeighbor(wb, wonderboards).getDefeatTokens();

//                    amount += wb.getRightNeighbor().getDefeatTokens();
                    amount += getRightNeighbor(wb, wonderboards).getDefeatTokens();
                }
            } else {
                if (involve.equals("own")) {
                    for (Card card : wb.getBuiltCards().values()) {
                        if (card.getColor().equals(dependency)) {
                            amount++;
                        }
                    }
                } else if (involve.equals("neigbor")) {
                    for (Card card : getLeftNeighbor(wb, wonderboards).getBuiltCards().values()) {
                        if (card.getColor().equals(dependency)) {
                            amount++;
                        }
                    }
                    for (Card card : getRightNeighbor(wb, wonderboards).getBuiltCards().values()) {
                        if (card.getColor().equals(dependency)) {
                            amount++;
                        }
                    }
                } else if (involve.equals("both")) {
                    for (Card card : wb.getBuiltCards().values()) {
                        if (card.getColor().equals(dependency)) {
                            amount++;
                        }
                    }
                    for (Card card : getLeftNeighbor(wb, wonderboards).getBuiltCards().values()) {
                        if (card.getColor().equals(dependency)) {
                            amount++;
                        }
                    }
                    for (Card card : getRightNeighbor(wb, wonderboards).getBuiltCards().values()) {
                        if (card.getColor().equals(dependency)) {
                            amount++;
                        }
                    }
                }
            }
        }
        HashMap<String, Integer> sources = wb.getSources();
        if (coin != 0 && vPoint != 0) {
            sources.replace("coin", sources.get("coin") + (amount * coin));
            wb.setSources(sources);
            //return true;
        }
        sources.replace("coin", sources.get("coin") + (amount * coin));
        sources.replace("victoryPoint", sources.get("victoryPoint") + (amount * vPoint));
        wb.setSources(sources);
        //return true;
    }

    private WonderBoard getLeftNeighbor(WonderBoard wb, HashMap<String, WonderBoard> wonderboards) {
        return wonderboards.get(wb.getLeftNeighbor());
    }

    private WonderBoard getRightNeighbor(WonderBoard wb, HashMap<String, WonderBoard> wonderboards) {
        return wonderboards.get(wb.getRightNeighbor());
    }

    public String getInvolve() {
        return involve;
    }

    public Set<String> getDependencies() {
        return dependencies;
    }

    public int getCoin() {
        return coin;
    }

    public int getvPoint() {
        return vPoint;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + coin;
        result = prime * result + ((dependencies == null) ? 0 : dependencies.hashCode());
        result = prime * result + ((involve == null) ? 0 : involve.hashCode());
        result = prime * result + vPoint;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CumulativeCard other = (CumulativeCard) obj;
        if (coin != other.coin) {
            return false;
        }
        if (dependencies == null) {
            if (other.dependencies != null) {
                return false;
            }
        } else if (!dependencies.equals(other.dependencies)) {
            return false;
        }
        if (involve == null) {
            if (other.involve != null) {
                return false;
            }
        } else if (!involve.equals(other.involve)) {
            return false;
        }
        if (vPoint != other.vPoint) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{" + " involve='" + getInvolve() + "'" + ", dependencies='" + getDependencies() + "'" + ", coin='"
                + getCoin() + "'" + "}";
    }
}
