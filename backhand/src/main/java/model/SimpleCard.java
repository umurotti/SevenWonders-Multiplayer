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

    private boolean isConvertible;
    private HashMap<String, Integer> benefits;
    
    public SimpleCard(Cost cost, String color, String name, List<String> freeBuildings, int minNoOfPlayer, HashMap<String, Integer> benefits , boolean isConvertible) {
        super(name, color, cost, minNoOfPlayer, freeBuildings);
        this.benefits = benefits;
        this.isConvertible = isConvertible;
    }
    
    public SimpleCard(boolean isConvertible) {
        super();
        this.isConvertible = isConvertible;
    }
    
    @Override
    public void play(WonderBoard wb, String selection) {
        //applyCost(wb);
        addAllSources(wb);
    }
    
    private void applyCost(WonderBoard wb) {
        
    }
    
    private void addAllSources(WonderBoard wb) {
        List<String> sourcesToCalculate = wb.getSourcesToCalculate();
        ListIterator it = wb.getSourcesToCalculate().listIterator();
        
        if(!isConvertible) {
            while(it.hasNext()) {
                String next = (String) it.next();
                for(Map.Entry<String, Integer> entry : getBenefits().entrySet()) {
                    if(entry.getValue() != 0) {
                        next = addSource(entry.getKey(), entry.getValue(), next);
                    }
                }
                it.set(next);
            }
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
        }
    }

    
    private String addSource(String sourceType, int amount, String sourceToAdd) {
        for(int i = 0; i < amount; i++) {
            sourceToAdd += sourceType.charAt(0);
        }
        char tempArray[] = sourceToAdd.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }
    
    public boolean isIsConvertible() {
        return isConvertible;
    }

    public void setIsConvertible(boolean isConvertible) {
        this.isConvertible = isConvertible;
    }

    public HashMap<String, Integer> getBenefits() {
        return benefits;
    }

    public void setBenefits(HashMap<String, Integer> benefits) {
        this.benefits = benefits;
    }
    

}
