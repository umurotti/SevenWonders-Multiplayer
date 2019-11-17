import java.util.HashMap;

class Test {
    public static void main(String[] args) {
        HashMap<String,Integer> costsource = new HashMap<String, Integer>()
        {{
            put("wood", 2);
            put("ore", 1);
        }};

        Cost cost = new Cost(costsource);

        HashMap<String, Integer> benefits = new HashMap<String, Integer>()
        {{
            put("ore", 4);
            put("clay", 3);
            put("coin", 100);
        }};
        Card card = new Card(cost, "mor", "ardahan", "malatyafree", benefits, 0);

        WonderBoard wb = new WonderBoard();

        card.play(wb, "");

        System.out.println(card+"\n");
        System.out.println(wb);
      }
}