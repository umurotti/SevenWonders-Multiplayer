/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.*;


/**
 * @author: OmerFarukKurklu
 * @version: 0.1
 */
public class WonderBoard {
    private String leftNeighbor;
    private String rightNeighbor;
    
    private HashMap<String,Integer> sources; // String: Name of the resource, Integer: Amount of the resource
    private HashMap<String, Boolean> orSources;
    
    private Cost[] stageCosts;
    private List<String> sourcesToCalculate;
    
    private int currentStage;
    private int diceValue;
    private String name;
    private HashMap<String, Card> builtCards; // String: Name of the Card, Card: instance of the Card
    private CardAction lockedAction;
    private HashMap<String, Integer> leftDiscount;  // String: Name of the resource, Integer: price of the resource from left neighbor
    private HashMap<String, Integer> rightDiscount; // String: Name of the resource, Integer: price of the resource from left neighbor
    private int militaryTokens;
    private int defeatTokens;
    private int handNo;
    private String wonderName;
    private boolean bribery; // Magic Card Effect #1
    private boolean scientist; // Magic Card Effect #3

    public WonderBoard() {
        // militaryTokens[0]: 1st age military victories, militaryTokens[1]: 2nd, militaryTokens[2]: 3rd.
        militaryTokens = 0;
        wonderName ="";
        // stageCosts[0]: 1st stage cost, stageCosts[1]: 2nd, stageCosts[2]: 3rd.
        stageCosts = new Cost[3];
        
        //*
        this.sourcesToCalculate = new LinkedList<>();
        sourcesToCalculate.add("");
        //*
        
        sources = new HashMap<String,Integer>()
        {{
            put("wood", 0);
            put("stone", 0);
            put("aclay", 0);
            put("ore", 0);
            put("loom",0);
            put("papyrus", 0);
            put("glass", 0);
            put("bcompass", 0);
            put("tablet", 0);
            put("gear", 0);
            put("coin", 0);
            put("zshield", 0);
            put("victory point", 0);
        }};
        
        orSources = new HashMap<String, Boolean>()
        {{
            put("clayORwood", Boolean.FALSE);
            put("clayORstone", Boolean.FALSE);
            put("clayORore", Boolean.FALSE);
            put("stoneORwood", Boolean.FALSE);
            put("oreORwood", Boolean.FALSE);
            put("oreORstone", Boolean.FALSE);
            put("glassORloomORpapyrus", Boolean.FALSE);
            put("clayORoreORstoneORwood", Boolean.FALSE);
        }};
        
        builtCards = new HashMap<String,Card>();

        leftDiscount = new HashMap<String, Integer>()
        {{
            put("wood", 2);
            put("stone", 2);
            put("aclay", 2);
            put("ore", 2);
            put("loom", 2);
            put("papyrus", 2);
            put("glass", 2);
        }};
        rightDiscount = new HashMap<String, Integer>()
        {{
            put("wood", 2);
            put("stone", 2);
            put("aclay", 2);
            put("ore", 2);
            put("loom", 2);
            put("papyrus", 2);
            put("glass", 2);
        }};
        bribery = false;
        scientist = false;
    }

    public WonderBoard( String name, int handNo, String wonderName) {
        this.name = name;
        this.wonderName = wonderName;
        // militaryTokens[0]: 1st age military victories, militaryTokens[1]: 2nd, militaryTokens[2]: 3rd.
        militaryTokens = 0;
        this.handNo = handNo;
        
        // stageCosts[0]: 1st stage cost, stageCosts[1]: 2nd, stageCosts[2]: 3rd.
        stageCosts = new Cost[3];
        HashMap<String, Integer> toAdd = new HashMap<>();
        toAdd.put("wood", 0);
        Cost x = new Cost(toAdd);
        stageCosts[0] = x;
        sourcesToCalculate = new LinkedList<>();
        sourcesToCalculate.add("abcglopst");
        
        sources = new HashMap<String,Integer>()
        {{
            put("wood", 0);
            put("stone", 1);
            put("aclay", 1);
            put("ore", 1);
            put("loom",1);
            put("papyrus", 1);
            put("glass", 1);
            put("bcompass", 1);
            put("tablet", 1);
            put("gear", 0);
            put("coin", 1);
            put("zshield", 0);
            put("victory point", 0);
        }};
        
        
        orSources = new HashMap<String, Boolean>()
        {{
            put("clayORwood", Boolean.FALSE);
            put("clayORstone", Boolean.FALSE);
            put("clayORore", Boolean.FALSE);
            put("stoneORwood", Boolean.FALSE);
            put("oreORwood", Boolean.FALSE);
            put("oreORstone", Boolean.FALSE);
            put("glassORloomORpapyrus", Boolean.FALSE);
            put("clayORoreORstoneORwood", Boolean.FALSE);
        }};
        
        
        builtCards  = new HashMap<String,Card>();
//        {{
//            //put("altar", new Card(new Cost(new HashMap<String, Integer>()), "red", "altar", "", 3, sources));
//        }};

        leftDiscount = new HashMap<String, Integer>()
        {{
            put("wood", 2);
            put("stone", 2);
            put("aclay", 2);
            put("ore", 2);
            put("loom", 2);
            put("papyrus", 2);
            put("glass", 2);
        }};
        rightDiscount = new HashMap<String, Integer>()
        {{
            put("wood", 2);
            put("stone", 2);
            put("aclay", 2);
            put("ore", 2);
            put("loom", 2);
            put("papyrus", 2);
            put("glass", 2);
        }};
        bribery = false;
        scientist = false;
    }

    public List<String> getSourcesToCalculate() {
        return sourcesToCalculate;
    }

    public void setSourcesToCalculate(List<String> sourcesToCalculate) {
        this.sourcesToCalculate = sourcesToCalculate;
    }

    public WonderBoard(String leftNeighbor, String rightNeighbor, HashMap<String,Integer> sources,
                       Cost[] stageCosts, int currentStage, int diceValue, String name, HashMap<String,Card> builtCards,
                       CardAction lockedAction, HashMap<String,Integer> leftDiscount, HashMap<String,Integer> rightDiscount,
                       int militaryTokens, int defeatTokens) {
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
    }

    public Boolean getBribery(){
        return bribery;
    }

    public void setBribery(boolean bribery) {
        this.bribery = bribery;
    }

    public Boolean getScientist(){
        return this.scientist;
    }

    public void setScientist(boolean scientist) {
        this.scientist = scientist;
    }

    public String getWonderName() {
        return wonderName;
    }

    public void setWonderName(String wonderName) {
        this.wonderName = wonderName;
    }

   
    
    public String getLeftNeighbor() {
        return this.leftNeighbor;
    }

    public void setLeftNeighbor(String leftNeighbor) {
        this.leftNeighbor = leftNeighbor;
    }

    public String getRightNeighbor() {
        return this.rightNeighbor;
    }

    public void setRightNeighbor(String rightNeighbor) {
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

    public CardAction getLockedAction() {
        return this.lockedAction;
    }

    public void setLockedAction(CardAction lockedAction) {
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

    public int getMilitaryTokens() {
        return this.militaryTokens;
    }

    public void setMilitaryTokens(int militaryTokens) {
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

    public HashMap<String, Boolean> getOrSources() {
        return orSources;
    }

    public void setOrSources(HashMap<String, Boolean> orSources) {
        this.orSources = orSources;
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

    public WonderBoard lockedAction(CardAction lockedAction) {
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

    public WonderBoard militaryTokens(int militaryTokens) {
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
    public void refactorStrings()
    {
        String norm ="";
        for(HashMap.Entry<String,Integer> entry: this.sources.entrySet())
        {
            int a = 0;
            while(a < entry.getValue() )
            {
                norm += entry.getKey().charAt(0);
                a++;
            }
        }
        List<String> temp = new ArrayList<String>();
        temp.add(norm);
        //look if it has or
        List<String> temp2;
        for(HashMap.Entry<String,Boolean> entry: this.orSources.entrySet())
        {

            if(entry.getValue() == Boolean.TRUE)
            {
                temp2 = new ArrayList<String>();
                if(entry.getKey().equals("clayORwood"))
                {
                    //add a and w to all of the strings in
                    for( int a= 0; a < temp.size(); a++)
                    {
                       String res = temp.get(a);
                       String res2 = temp.get(a);
                       res2 = "w" + res2;
                       res = "a" + res;
                       temp2.add(res);
                       temp2.add(res2);
                    }
                }
                if(entry.getKey().equals("clayORstone"))
                {
                    for( int a= 0; a < temp.size(); a++)
                    {
                        String res = temp.get(a);
                        String res2 = temp.get(a);
                        res2 = "s" + res2;
                        res = "a" + res;
                        temp2.add(res);
                        temp2.add(res2);
                    }
                }
                if(entry.getKey().equals("clayORore"))
                {
                    for( int a= 0; a < temp.size(); a++)
                    {
                        String res = temp.get(a);
                        String res2 = temp.get(a);
                        res2 = "o" + res2;
                        res = "a" + res;
                        temp2.add(res);
                        temp2.add(res2);
                    }
                }
                if(entry.getKey().equals("stoneORwood"))
                {
                    for( int a= 0; a < temp.size(); a++)
                    {
                        String res = temp.get(a);
                        String res2 = temp.get(a);
                        res2 = "w" + res2;
                        res = "s" + res;
                        temp2.add(res);
                        temp2.add(res2);
                    }
                }
                if(entry.getKey().equals("oreORwood"))
                {
                    for( int a= 0; a < temp.size(); a++)
                    {
                        String res = temp.get(a);
                        String res2 = temp.get(a);
                        res2 = "w" + res2;
                        res = "o" + res;
                        temp2.add(res);
                        temp2.add(res2);
                    }
                }
                if(entry.getKey().equals("oreORstone"))
                {
                    for( int a= 0; a < temp.size(); a++)
                    {
                        String res = temp.get(a);
                        String res2 = temp.get(a);
                        res2 = "o" + res2;
                        res = "s" + res;
                        temp2.add(res);
                        temp2.add(res2);
                    }
                }
                if(entry.getKey().equals("glassORloomORpapyrus"))
                {
                    for( int a= 0; a < temp.size(); a++)
                    {
                        String res = temp.get(a);
                        String res2 = temp.get(a);
                        String res3 = temp.get(a);
                        res2 = "l" + res2;
                        res = "p" + res;
                        res3 = "g" + res3;
                        temp2.add(res);
                        temp2.add(res2);
                        temp2.add(res3);
                    }
                }
                if(entry.getKey().equals("clayORoreORstoneORwood"))
                {
                    for( int a= 0; a < temp.size(); a++)
                    {
                        String res = temp.get(a);
                        String res2 = temp.get(a);
                        String res3 = temp.get(a);
                        String res4 = temp.get(a);
                        res2 = "a" + res2;
                        res = "o" + res;
                        res3 = "s" + res3;
                        res4 = "w" + res4;
                        temp2.add(res);
                        temp2.add(res2);
                        temp2.add(res3);
                        temp2.add(res4);
                    }
                }
                //delete the things in temp
                //put temp 2 to temp
                for(int a = 0; a < temp2.size(); a++)
                {
                    char tempAr[] = temp2.get(a).toCharArray();
                    Arrays.sort(tempAr);
                    String str = new String((tempAr));
                    temp2.set(a,str);
                }
                temp.clear();;
                for(int a = 0; a < temp2.size();a++)
                {
                    temp.add(a,temp2.get(a));
                }

            }
        }

        this.setSourcesToCalculate(temp);

    }

}