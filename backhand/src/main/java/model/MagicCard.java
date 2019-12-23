/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author umur
 */
public class MagicCard extends Card {
        private int effect; //

    public MagicCard(String name, String color, Cost cost, int minNoOfPlayers, List<String> freeBuildings, int effect) {
        super(name, color, cost, minNoOfPlayers, freeBuildings);
        this.effect = effect;
    }

    @Override
    void play(WonderBoard wb, String selection, HashMap<String, WonderBoard> wonderboards ) {
        // TODO
        switch(effect){
            case 1:                 // Corrupt neighbor armies. Win every fight, pay the difference in shield as coins.
                wb.setBribery(true);
                break;
            case 2:                 // Replace all normal resources with random ones.
                

            case 3:                 // One science card will be chosen, rest will be 0, that one will give 3*n*n VP's
                HashMap<String, Integer> sources3 = wb.getSources();
                int random = (int)(Math.random() * 3);
                if (random == 0){           // gear
                    sources3.replace("tablet",0);
                    sources3.replace("bcompass",0);
                    wb.setScientist(true);
                }
                if (random == 1) {          // tablet
                    sources3.replace("gear",0);
                    sources3.replace("bcompass",0);
                    wb.setScientist(true);
                }
                if (random == 2) {           // bcompass
                    sources3.replace("gear",0);
                    sources3.replace("tablet",0);
                    wb.setScientist(true);
                }
                wb.setSources(sources3);
                wb.refactorStrings();
                break;

            case 4:                 // Lose a stage
                if (wb.getCurrentStage() > 0) {
                    wb.setCurrentStage(wb.getCurrentStage() - 1);
                }
                else {
                    wb.setCurrentStage(wb.getCurrentStage() + 1);
                }
                break;

            case 5:                 // Flip a coin, if win get double the amount of coins, if lose, lose all coins. Will regain the dice game entry price.
                HashMap<String, Integer> sources5 = wb.getSources();
                sources5.replace("coin", sources5.get("coin") + 3);
                if(Math.random() >= 0.5){   // High rolled, won!
                    sources5.replace("coin",sources5.get("coin") *2);
                }
                else {                      // Low rolled, lost!
                    sources5.replace("coin",0);
                }
                wb.setSources(sources5);
                wb.refactorStrings();
                break;
            
            case 6:                 // double the manifactured materials, cut the raw materials in half.
                HashMap<String, Integer> sources6 = wb.getSources();
                sources6.replace("loom", sources6.get("loom")*2);
                sources6.replace("papyrus", sources6.get("papyrus")*2);
                sources6.replace("glass", sources6.get("glass")*2);

                sources6.replace("wood", sources6.get("wood")/2);
                sources6.replace("stone", sources6.get("stone")/2);
                sources6.replace("ore", sources6.get("ore")/2);
                sources6.replace("aclay", sources6.get("clay")/2);

                wb.setSources(sources6);
                wb.refactorStrings();
                break;
            case 7:                 // en yukse, en dusuk
            //find biggest
                String sourceName1 = ""; 
                Integer max = -1;

                Integer min =wb.getSources().get("aclay");
                String sourceName2 = "aclay";
                for(Map.Entry<String,Integer> entry: wb.getSources().entrySet())
                    {
                    if(entry.getValue()> max )
                    {
                        max = entry.getValue();
                        sourceName1 = entry.getKey();
                    }    
                    if(entry.getValue()< min)
                    {
                        min = entry.getValue();
                        sourceName2 = entry.getKey();
                    }
                }     
                //change sources
                HashMap<String,Integer> hold = wb.getSources();
                for(HashMap.Entry<String,Integer> entry: hold.entrySet())
                {
                        if(entry.getKey().equals(sourceName1) )
                        {
                            hold.replace(sourceName1,min);
                        }    
                        if(entry.getKey().equals(sourceName2))
                        {
                            hold.replace(sourceName2,max);    
                        }
                }
                wb.setSources(hold);
                wb.refactorStrings();
                break;
                }
    }

    public int getEffect() {
        return effect;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + effect;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        MagicCard other = (MagicCard) obj;
        if (effect != other.effect)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "MagicCard [effect=" + effect + "]";
    }

    public void setEffect(int effect) {
        this.effect = effect;
    }
    
    
}
