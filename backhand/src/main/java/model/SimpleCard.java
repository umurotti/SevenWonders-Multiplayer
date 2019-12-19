/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 *
 * @author umur
 */
public class SimpleCard extends Card {

    private boolean isOr;
    private HashMap<String, Integer> benefits;
    
    public SimpleCard(Cost cost, String color, String name, List<String> freeBuildings, int minNoOfPlayer, HashMap<String, Integer> benefits , boolean isOr) {
        super(name, color, cost, minNoOfPlayer, freeBuildings);
        this.benefits = benefits;
        this.isOr = isOr;
    }
    
    public SimpleCard(boolean isOr) {
        super();
        this.isOr = isOr;
    }
    
    @Override
    public void play(WonderBoard wb, String selection, HashMap<String, WonderBoard> wonderboards) {
        //applyCost(wb);
        if(selection.equals("0")) {
            addThreeCoins(wb);
        } else {
            addAllSources(wb);
        }
    }

    private void addThreeCoins(WonderBoard wb) {
        List<String> sourcesToCalculate = wb.getSourcesToCalculate();
        ListIterator it = wb.getSourcesToCalculate().listIterator();
        while (it.hasNext()) {
            String next = (String) it.next();
            next = addSource("coin", 3, next);
            it.set(next);
        }
    }
    
    private void addAllSources(WonderBoard wb) {
        List<String> sourcesToCalculate = wb.getSourcesToCalculate();
        ListIterator it = wb.getSourcesToCalculate().listIterator();
        
        if(!isOr) {
            while(it.hasNext()) {
                String next = (String) it.next();
                for(Map.Entry<String, Integer> entry : getBenefits().entrySet()) {
                    if(entry.getValue() != 0) {
                        next = addSource(entry.getKey(), entry.getValue(), next);
                    }
                }
                it.set(next);
            }
            addToNormalSource(wb);
        } else {
            List<String> toAdd = new LinkedList<>();
            while(it.hasNext()) {
                String next = (String) it.next();
                for(Map.Entry<String, Integer> entry : getBenefits().entrySet()) {
                    if(entry.getValue() != 0) {
                        toAdd.add(addSource(entry.getKey(), entry.getValue(), next));
                    }
                }
            }
            wb.setSourcesToCalculate(toAdd);
            addToOrSource(wb);
        }
    }

    private void addToNormalSource(WonderBoard wb) {
        for (Map.Entry<String, Integer> entry : getBenefits().entrySet()) {
            if (entry.getValue() != 0) {
                wb.getSources().put(entry.getKey(), wb.getSources().get(entry.getKey()) + entry.getValue());
            }
        }
    }
    
    private void addToOrSource(WonderBoard wb) {
        List<String> orSources = new LinkedList<>();
        for (Map.Entry<String, Integer> entry : getBenefits().entrySet()) {
            if (entry.getValue() != 0) {
                orSources.add(entry.getKey());
            }
        }
        
        ListIterator it = orSources.listIterator();
        String toAdd = (String) it.next();
        while(it.hasNext()) {
            toAdd += "OR" + it.next();
        }
        
        wb.getOrSources().put(toAdd, Boolean.TRUE);
    }
        
    private String addSource(String sourceType, int amount, String sourceToAdd) {
        for(int i = 0; i < amount; i++) {
            sourceToAdd += sourceType.charAt(0);
        }
        char tempArray[] = sourceToAdd.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }
    
    public boolean isIsOr() {
        return isOr;
    }

    public void setIsOr(boolean isOr) {
        this.isOr = isOr;
    }
    
    

    public HashMap<String, Integer> getBenefits() {
        return benefits;
    }

    public void setBenefits(HashMap<String, Integer> benefits) {
        this.benefits = benefits;
    }
    

}
