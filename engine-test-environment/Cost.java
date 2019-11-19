import java.util.HashMap;
import java.util.Objects;
/** 
 * @author OmerFarukKurklu
 * @version 0.1
*/
public class Cost {
    private HashMap<String,Integer> cost;

    public Cost() {
    }

    public Cost(HashMap<String,Integer> cost) {
        this.cost = cost;
    }

    public HashMap<String,Integer> getCost() {
        return this.cost;
    }

    public void setCost(HashMap<String,Integer> cost) {
        this.cost = cost;
    }

    public Cost cost(HashMap<String,Integer> cost) {
        this.cost = cost;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Cost)) {
            return false;
        }
        Cost cost = (Cost) o;
        return Objects.equals(cost, cost.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cost);
    }

    @Override
    public String toString() {
        return "{" +
            " cost='" + getCost() + "'" +
            "}";
    }
}