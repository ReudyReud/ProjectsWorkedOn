import java.util.*;
class SolveablePuzzle{
   // Returns true if it is possible to reach the goal state from the initial state.
   static boolean isSolvable(List<Integer> initialState) {
      // Fill in.
          Set<List<Integer>> set = new HashSet<>();
          Queue<List<Integer>> q = new ArrayDeque<>();
          q.add(initialState);
          set.add(initialState);

          while(!q.isEmpty()){
            List<Integer> v = q.remove();
            List<List<Integer>> z = generateNewStates(v);

            for(List<Integer> i: z){
            if(!set.contains(i)){
              if(isGoalState(i))
                return true;    
              set.add(i);
              q.add(i);
              
              }
            }
          }
      return false; 
   }

   public static void main(String[] args) {
      List<Integer> puzzle1 = Arrays.asList(null, 1, 3, 4, 2, 5, 7, 8, 6);
      System.out.println(isSolvable(puzzle1)); // true
      
      List<Integer> puzzle2 = Arrays.asList(3, null, 7, 2, 8, 1, 6, 4, 5);
      System.out.println(isSolvable(puzzle2)); // true
      
      List<Integer> puzzle3 = Arrays.asList(2, 1, 3, 4, 5, 6, 7, 8, null);
      System.out.println(isSolvable(puzzle3)); // false
   }

   // Returns true if the given state is the goal state.
   static boolean isGoalState(List<Integer> state) {
      return state.equals(goalState);
   }
   static List<Integer> goalState = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, null);

   // Takes a puzzle state as a parameter and returns a List of all possible new puzzle states.
   static List<List<Integer>> generateNewStates(List<Integer> state) {
      List<List<Integer>> newStates = new ArrayList<>();
      for (int i = 0; i < 9; ++i) {
         if (state.get(i) == null) {
            if (i > 2) { // Swap empty square with square above
               List<Integer> newState = new ArrayList<>(state);
               Collections.swap(newState, i, i-3);
               newStates.add(newState);
            }
            if (i < 6) { // Swap empty square with square below
               List<Integer> newState = new ArrayList<>(state);
               Collections.swap(newState, i, i+3);
               newStates.add(newState);
            }
            if (i % 3 != 0) { // Swap empty square with square to left
               List<Integer> newState = new ArrayList<>(state);
               Collections.swap(newState, i, i-1);
               newStates.add(newState);
            }
            if (i % 3 != 2) { // Swap empty square with square to right
               List<Integer> newState = new ArrayList<>(state);
               Collections.swap(newState, i, i+1);
               newStates.add(newState);
            }
         }
      }
      return newStates;
   }
}
