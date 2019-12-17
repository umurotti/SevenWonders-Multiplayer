import java.util.HashMap;

/**
 * @author OmerFarukKurklu
 * @version 0.2
 * Added a function playAction() which will be a helper function for playTurn() in the Table class. Game engine tests.
 */
class Test {    
    public static void main(String[] args) {
        HashMap<String,Integer> costsource1 = new HashMap<String, Integer>()
        {{
            put("wood", 0);
        }};
        HashMap<String,Integer> costsource2 = new HashMap<String, Integer>()
        {{
            put("wood", 2);
            put("ore", 2);
        }};
        HashMap<String,Integer> costsource3 = new HashMap<String, Integer>()
        {{
            put("wood", 3);
            put("ore", 3);
        }};
        HashMap<String,Integer> costsource4 = new HashMap<String, Integer>()
        {{
            put("wood", 4);
            put("ore", 4);
        }};
        HashMap<String,Integer> costsource5 = new HashMap<String, Integer>()
        {{
            put("wood", 5);
            put("ore", 5);
        }};
        HashMap<String,Integer> costsource6 = new HashMap<String, Integer>()
        {{
            put("wood", 6);
            put("ore", 6);
        }};
        HashMap<String,Integer> costsource7 = new HashMap<String, Integer>()
        {{
            put("wood", 7);
            put("ore", 7);
        }};
        HashMap<String,Integer> costsourceW11 = new HashMap<String, Integer>()
        {{
            put("stone", 1);
            put("clay", 1);
        }};
        HashMap<String,Integer> costsourceW12 = new HashMap<String, Integer>()
        {{
            put("stone", 2);
            put("clay", 2);
        }};
        HashMap<String,Integer> costsourceW13 = new HashMap<String, Integer>()
        {{
            put("stone", 3);
            put("clay", 3);
        }};
        HashMap<String,Integer> costsourceW21 = new HashMap<String, Integer>()
        {{
            put("stone", 1);
            put("clay", 1);
        }};
        HashMap<String,Integer> costsourceW22 = new HashMap<String, Integer>()
        {{
            put("stone", 2);
            put("clay", 2);
        }};
        HashMap<String,Integer> costsourceW23 = new HashMap<String, Integer>()
        {{
            put("stone", 3);
            put("clay", 3);
        }};

        Cost [] costs = new Cost[]{new Cost(costsource1),new Cost(costsource2),new Cost(costsource3),new Cost(costsource4),
            new Cost(costsource5),new Cost(costsource6),new Cost(costsource7)};

        HashMap<String, Integer> benefit1 = new HashMap<String, Integer>()
        {{
            put("wood", 2);
            put("ore", 2);
            put("coin", 100);
        }};
        HashMap<String, Integer> benefit2 = new HashMap<String, Integer>()
        {{
            put("wood", 1);
            put("ore", 1);
            put("coin", 100);
        }};
        HashMap<String, Integer> benefit3 = new HashMap<String, Integer>()
        {{
            put("wood", 1);
            put("ore", 1);
            put("coin", 100);
        }};
        HashMap<String, Integer> benefit4 = new HashMap<String, Integer>()
        {{
            put("wood", 1);
            put("ore", 1);
            put("coin", 100);
        }};
        HashMap<String, Integer> benefit5 = new HashMap<String, Integer>()
        {{
            put("wood", 1);
            put("ore", 1);
            put("coin", 100);
        }};
        HashMap<String, Integer> benefit6 = new HashMap<String, Integer>()
        {{
            put("wood", 1);
            put("ore", 1);
            put("coin", 100);
        }};
        HashMap<String, Integer> benefit7 = new HashMap<String, Integer>()
        {{
            put("clay", 4);
            put("stone", 3);
            put("coin", 100);
        }};

        Card card1 = new Card(costs[0], "mor", "card_1", "none", benefit1,0);
        Card card2 = new Card(costs[1], "mor", "card_2", "none", benefit2,0);
        Card card3 = new Card(costs[2], "mor", "card_3", "none", benefit3,0);
        Card card4 = new Card(costs[3], "mor", "card_4", "none", benefit4,0);
        Card card5 = new Card(costs[4], "mor", "card_5", "none", benefit5,0);
        Card card6 = new Card(costs[5], "mor", "card_6", "none", benefit6,0);
        Card card7 = new Card(costs[6], "mor", "card_7", "card_2", benefit7,0);

        WonderBoard wb1 = new WonderBoard();
        Cost [] wb1costs = new Cost[] {new Cost(costsourceW11),new Cost(costsourceW12),
            new Cost(costsourceW13)};
        wb1.setStageCosts(wb1costs);
        wb1.setName("wonderboard_1");
        wb1.setHandNo(0);

        WonderBoard wb2 = new WonderBoard();
        Cost [] wb2costs = new Cost[] {new Cost(costsourceW21),new Cost(costsourceW22),
            new Cost(costsourceW23)};
        wb2.setStageCosts(wb2costs);
        wb2.setName("wonderboard_2");
        wb2.setHandNo(1);
        
        HashMap<String, WonderBoard> wonders = new HashMap<String, WonderBoard>(){{put("wonderboard_1", wb1);put("wonderboard_2", wb2);}};
        TestTable testtable = new TestTable();
        testtable.setWonders(wonders);
        Card [][] hands = new Card[2][7];
        Card [] hand = new Card[]{card1,card2,card3,card4,card5,card6,card7};
        hands[0] = hand;
        hands[1] = hand;
        testtable.setHand(hands);
        
        HashMap<String,Integer> trade = new HashMap<String,Integer>();
        int discard = 0;
        int build = 1;
        int stage = 2;
        Action action1 = new Action(build,trade,trade,0,"wonderboard_1");
        Action action2 = new Action(build,trade,trade,1,"wonderboard_1");
        Action action3 = new Action(build,trade,trade,2,"wonderboard_1");
        Action action4 = new Action(build,trade,trade,6,"wonderboard_1");
        Action action5 = new Action(stage,trade,trade,3,"wonderboard_1");
//        Action action6 = new Action(discard,trade,trade,0,"wonderboard_1");
        Action action7 = new Action(discard,trade,trade,4,"wonderboard_1");
        
        System.out.println("Wonder looks like: \n" + testtable.getWonders().get("wonderboard_1"));
        System.out.println("is playing action1 possible: " + testtable.isPossible(action1));
        System.out.println("is playing action2 possible: " + testtable.isPossible(action2));
        System.out.println("is playing action7 possible: " + testtable.isPossible(action7));
        System.out.println("is playing action5 possible: " + testtable.isPossible(action5)+"\n");

        System.out.println("playing action1\n");
        testtable.playFor("wonderboard_1", action1);

        System.out.println("is playing action2 possible: " + testtable.isPossible(action2));
        System.out.println("is playing action4 possible: " + testtable.isPossible(action4));
        System.out.println("is playing action7 possible: " + testtable.isPossible(action7));
        System.out.println("is playing action5 possible: " + testtable.isPossible(action5));

        System.out.println("playing action2\n");
        testtable.playFor("wonderboard_1", action2);

        System.out.println("is playing action3 possible: " + testtable.isPossible(action3));
        System.out.println("is playing action4 possible: " + testtable.isPossible(action4));
        System.out.println("is playing action5 possible: " + testtable.isPossible(action5));

        System.out.println("playing action4\n");
        testtable.playFor("wonderboard_1", action4);

        System.out.println("is playing action5 possible: " + testtable.isPossible(action5));

        System.out.println("playing action5\n");
        testtable.playFor("wonderboard_1", action5);

        System.out.println("playing action7\n");
        testtable.playFor("wonderboard_1", action7);

        System.out.println(testtable.getWonders().get("wonderboard_1"));
        for (int i = 0; i < 7; i++){
            System.out.println(testtable.getHands()[0][i]);
        }
        }
    }