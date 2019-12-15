import java.util.HashMap;
import java.util.Objects;
/** 
 * @author OmerFarukKurklu
 * @version 0.1
*/
public class Cost {
    private HashMap<String,Integer> costs;

    public Cost() {
    }

    public Cost(HashMap<String,Integer> costs) {
        this.costs = costs;
    }

    public HashMap<String,Integer> getCosts() {
        return this.costs;
    }

    public void setCosts(HashMap<String,Integer> costs) {
        this.costs = costs;
    }

    public Cost costs(HashMap<String,Integer> costs) {
        this.costs = costs;
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
        return Objects.equals(this.costs, cost.costs);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(costs);
    }

    @Override
    public String toString() {
        return "{" +
            " cost='" + getCosts() + "'" +
            "}";
    }
}