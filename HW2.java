import java.io.*;
import java.util.*;

class HW2 {   
   // Prints all movies that occur in both lists.
   public static void intersection(List<String> list1, List<String> list2) {
     Set<String> set = new HashSet<String>();
     Set<String> set2 = new HashSet<String>();
     for(int i  = 0; i< list1.size(); i++){
      set.add(list1.get(i));
     }

     for(int i  = 0; i< list2.size(); i++){
      if(set.contains(list2.get(i)))
        set2.add(list2.get(i));
      }
     System.out.println(set2);
   }
   
   // Prints all movies in the list that occur at least k times
   // (print the movie followed by the number of occurrences in parentheses).
   public static void frequent(List<String> list, int k) {
      Map<String, Integer> map = new HashMap<>(); 

      for(int i = 0; i < list.size(); i++){
        if(map.containsKey(list.get(i)))
           map.put(list.get(i), map.get(list.get(i))+1);
        else
         map.put(list.get(i),1);
       }

      for (Map.Entry<String, Integer> entry : map.entrySet()){ // Prints the HashMap with a for-each loop
      if(entry.getValue() >= k)
            System.out.println(entry.getKey() + "(" + entry.getValue() + ")");
  
       }  

   }
   
   // Prints all movies in the list, grouped by year.
   // All movies from the same year should be printed on the same line.
   // Earlier years should be listed first.
   public static void groupByYear(List<String> list) {
      TreeMap<String, Set<String>> tmap = new TreeMap<>(); 
      Set<String> set = null;
      String[] fileLines = new String[list.size()];
      String[] years = new String[list.size()];
      String[] movies = new String[list.size()];

      for(int i = 0; i< list.size(); i++){
        fileLines = list.get(i).split(";"); 
        years[i] = fileLines[1];
        movies[i] = fileLines[0];

        if(!(tmap.containsKey(years[i]))){
          set = new HashSet<>();
          set.add(movies[i]);
          tmap.put(years[i], set); 
          
      }
        
      else{
        if(tmap.containsKey(years[i])){
          tmap.get(years[i]).add(movies[i]); //Returns the value to which the specified key is mapped
        }
      }   
      } 
        for (Map.Entry m:tmap.entrySet()) {
          System.out.println(m.getKey() + "=" + m.getValue()); 
        } 

   }
   // Returns a List of all movies in the specified file (assume there is one movie per line).
   public static List<String> getList(String filename) {
      List<String> list = new ArrayList<>();
      
      try (Scanner in = new Scanner(new FileReader(filename))) {
         while (in.hasNextLine()) {
            String line = in.nextLine();
            list.add(line);
            

         }
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
      return list;
   }

   public static void main(String[] args) {
      List<String> list1 = getList("1001_movies.txt");
      List<String> list2 = getList("rosenbaum.txt");
      List<String> list3 = getList("all_lists.txt");
      List<String> list4 = getList("sightsound_with_years.txt");

      System.out.println("***intersection***");
      intersection(list1, list2);

       System.out.println("***frequent***");
       frequent(list3, 3);

      System.out.println("***groupByYear***");
      groupByYear(list4);
   }
}
