import java.util.HashMap;

/**
 * @author OmerFarukKurklu
 * @version 0.1
 */

public class DiscountCard extends Card{
    private String neighbor; // Possible meanful values: 'left': left neighbor, 'right': right neighbor, 'both'(left and right), null: not initialized.
    private String discountMaterial; // Possible meanful values: 'raw': raw materials(W,S,O,C), 'man': manifactured materials(L,P,G), null: not initialized

    private final Set<String> RAW_MAT;
    private final Set<String> MAN_MAT;

    public DiscountCard(String name, String color, Cost cost, int minNoOfPlayers, List<String> freeBuildings, String neighbor, String discountMaterial) {
        super(cost, color, name, minNoOfPlayers, freeBuildings);
        this.neighbor = neighbor;
        this.discountMaterial = discountMaterial;

        this.RAW_MAT = new Set<String>() {{
            add("wood");
            add("stone");
            add("ore");
            add("clay");
        }};
        this.MAN_MAT = new Set<String>() {{
            add("papyrus");
            add("loom");
            add("glass");
        }};
    }    

    @Override
    public boolean play(WonderBoard wb){
        if (neighbor.equals("left")){
            HashMap<String,Integer> discountedMap = wb.getLeftDiscount();
            if (discountMaterial.equals("raw")){
                for (String k : discountedMap.keySet()){
                    if (RAW_MAT.contains(k)){
                        discountedMap.replace(k, 1);
                    }
                }
                wb.setLeftDiscount(discountedMap);
                return true;
            }
            else if(discountMaterial.equals("man")) {
                for (String k : discountedMap.keySet()){
                    if (MAN_MAT.contains(k)){
                        discountedMap.replace(k, 1);
                    }
                }
                wb.setLeftDiscount(discountedMap);
                return true;
            }
        }
        else if (neighbor.equals("right")){
            HashMap<String,Integer> discountedMap = wb.getLeftDiscount();
            if (discountMaterial.equals("raw")){
                for (String k : discountedMap.keySet()){
                    if (RAW_MAT.contains(k)){
                        discountedMap.replace(k, 1);
                    }
                }
                wb.setRightDiscount(discountedMap);
                return true;
            }
            else if(discountMaterial.equals("man")) {
                for (String k : discountedMap.keySet()){
                    if (MAN_MAT.contains(k)){
                        discountedMap.replace(k, 1);
                    }
                }
                wb.setRightDiscount(discountedMap);
                return true;
            }
        }
        else if (neighbor.equals("both")){
            HashMap<String,Integer> discountedMapR = wb.getRightDiscount();
            HashMap<String,Integer> discountedMapL = wb.getLeftDiscount();
            if (discountMaterial.equals("raw")){
                for (String k : discountedMapR.keySet()){
                    if (RAW_MAT.contains(k)){
                        discountedMapR.replace(k, 1);
                        discountedMapL.replace(k, 1);
                    }
                }
                wb.setRightDiscount(discountedMapR);
                wb.setLeftDiscount(discountedMapL);
                return true;
            }
            else if(discountMaterial.equals("man")) {
                for (String k : discountedMapR.keySet()){
                    if (MAN_MAT.contains(k)){
                        discountedMapR.replace(k, 1);
                        discountedMapL.replace(k, 1);
                    }
                }
                wb.setRightDiscount(discountedMapR);
                wb.setLeftDiscount(discountedMapL);
                return true;
            }
        }
        return false;
    };

    public String getNeighbor() {
        return this.neighbor;
    }

    public void setNeighbor(String neighbor) {
        this.neighbor = neighbor;
    }

    public String getDiscountMaterial() {
        return this.discountMaterial;
    }

    public void setDiscountMaterial(String discountMaterial) {
        this.discountMaterial = discountMaterial;
    }

    public DiscountCard neighbor(String neighbor) {
        this.neighbor = neighbor;
        return this;
    }

    public DiscountCard discountMaterial(String discountMaterial) {
        this.discountMaterial = discountMaterial;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " neighbor='" + getneighbor() + "'" +
            ", discountMaterial='" + getDiscountMaterial() + "'" +
            "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((discountMaterial == null) ? 0 : discountMaterial.hashCode());
        result = prime * result + ((neighbor == null) ? 0 : neighbor.hashCode());
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
        DiscountCard other = (DiscountCard) obj;
        if (discountMaterial == null) {
            if (other.discountMaterial != null)
                return false;
        } else if (!discountMaterial.equals(other.discountMaterial))
            return false;
        if (neighbor == null) {
            if (other.neighbor != null)
                return false;
        } else if (!neighbor.equals(other.neighbor))
            return false;
        return true;
    }
}