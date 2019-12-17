import java.util.ArrayList;

/**
 * @author Omer Faruk Kurklu
 * @version 0.1(BETA)
 * 
 * Example:
 *  say the needed resources are O,S,S,W (2 stone, 1 ore, 1 wood)
 *  say the built buildings are W/S S/C S/O W/C
 *  then tree looks like:
 *
 *  X = return false;
 *  0 = return true;
 *  return false reasons:
 *  ├── NB: Not Built. (Can't choose source from a building not built yet.)
 *  └── SaP: Same as Previous. (Because you can't choose both sides of the OR in one building.)

     W   S   S  O
 ├── W/C
 │   ├── S/C
 │   │   ├── S/C X(SaP)
 │   │   ├── S/O
 │   │   │   ├── O/C X(NB)
 │   │   │   ├── O/S X(SaP)
 │   │   │   └── O/W X(NB)
 │   │   └── S/W
 │   │       ├── O/C X(NB)
 │   │       ├── O/S 0 (1)
 │   │       └── O/W X(NB)
 │   ├── S/O
 │   │   ├── S/C
 │   │   │   ├── O/C X(NB)
 │   │   │   ├── O/S X(SaP)
 │   │   │   └── O/W X(NB)
 │   │   ├── S/O X(SaP)
 │   │   └── S/W
 │   │       ├── O/C X(NB)
 │   │       ├── O/S X(SaP)
 │   │       └── O/W X(NB)
 │   └── S/W
 │       ├── S/C
 │       │   ├── O/C X(NB)
 │       │   ├── O/S 0 (2)
 │       │   └── O/W X(NB)
 │       ├── S/O
 │       │   ├── O/C X(NB)
 │       │   ├── O/S X(SaP)
 │       │   └── O/W X(NB)
 │       └── S/W X(SaP)
 ├── W/O X(NB)
 └── W/S
     ├── S/C
     │   ├── S/C X(SaP)
     │   ├── S/O
     │   │   ├── O/C X(NB)
     │   │   ├── O/S X(SaP)
     │   │   └── O/W X(NB)
     │   └── S/W X(SaP)
     ├── S/O
     │   ├── S/C
     │   │   ├── O/C X(NB)
     │   │   ├── O/S X(SaP)
     │   │   └── O/W X(NB)
     │   ├── S/O X(SaP)
     │   └── S/W X(SaP)
     └── S/W X(SaP)

 * Means there are 2 possible ways to choose these resources.
 * Recursive method checks in this tree and since JAVA does not check other conditions if one statement is true in OR statements (||),
 *   recursion stops after the first return true. Hence in average we do not check the whole 3 way tree, we just find if there exist one solution.
 */

class RecursiveORcheck {
      private static int [] elifDecoder(int source){
        // 0: W/S, 1: W/C, 2: W/O, 3: S/C, 4: S/O, 5: O/C
        if (source == 0){   //wood
            return new int [] {0,1,2};
        }
        if (source == 1){   //stone
            return new int [] {0,3,4};
        }
        if (source == 2){   //clay
            return new int [] {1,3,5};
        }
        if (source == 3){   //ore
            return new int [] {2,4,5};
        }
        return null;
      }

      public static boolean alptecursive(int building, ArrayList<Integer> prevBuildings, int [] sequence, int size){
          // If buildings are the same, means you cannot choose both sides of the OR, return false.
          if (prevBuildings.contains(building)){
              return false;
          } // -1 : initializing dummy value.
           // If the building is not built on the Wonder, return false.
          if (building != -1 && !builtOR[building]){
              return false;
          }
          // If succesfully reached end, return true.
          if (size == 0){
              return true;
          }

          prevBuildings.add(building);
          size--;

          // Decoder gets the next 3 buildings.
          return alptecursive(elifDecoder(sequence[size])[0], prevBuildings ,sequence,size) 
          || alptecursive(elifDecoder(sequence[size])[1], prevBuildings,sequence,size) 
          || alptecursive(elifDecoder(sequence[size])[2], prevBuildings,sequence,size);
      }

                                        //  W/S,    W/C,    W/O,    S/C,    S/O,    O/C
    private static boolean [] builtOR = {   true,   true,   false,  true,   true,   false};

      public static void main(String[] args) {
          int size = 4;
          // 0:W'ood, 1:S'tone, 2:C'lay, 3:O're
          int [] sequence = new int[] {2,1,1,0};
          
          // -1 and null ArrayList are initializing dummy values. Just ignore.
          System.out.println(alptecursive(-1,new ArrayList<Integer>(),sequence,size));
      }
}