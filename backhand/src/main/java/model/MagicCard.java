import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MagicCard extends Card {
    private final int effect; //

    public MagicCard(String name, String color, Cost cost, int minNoOfPlayers, List<String> freeBuildings, int effect) {
        super(name, color, cost, minNoOfPlayers, freeBuildings);
        this.effect = effect;
    }

    @Override
    void play(WonderBoard wb) {
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
                if (radom == 2) {           // bcompass
                    sources3.replace("gear",0);
                    sources3.replace("tablet",0);
                    wb.setScientist(true);
                }
                wb.setSources(sources3);
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
                break;
            case 7:                 // en yukse, en dusuk
            //find biggest
                String sourceName1; 
                int max = -1;

                int min =wb.getSources().get("aclay");
                String sourceName2 = "aclay";
                for(Map.Entry<String,Integer> entry: wb.getSources().entrySet())
                    {
                    if(entry.getValue()> max )
                    {
                        max = entry.getValue();
                        sourceName = entry.getKey();
                    }    
                    if(entry.getValue()< min)
                    {
                        min = entry.getValue();
                        sourceName2 = entry.getKey();
                    }
                }     
                //change sources
                HashMap<String,Integer> hold = wb.getSources();
                for(Map.Entry<String,Integer> entry: hold.entrySet())
                {
                        if(entry.getKey().equals(sourceName1) )
                        {
                            entry.replace(sourceName1,min);
                        }    
                        if(entry.getKey().equals(sourceName2))
                        {
                            entry.replace(sourceName2,max);    
                        }
                }
                wb.setSources(hold);
 
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
}