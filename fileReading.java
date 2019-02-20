import java.util.Scanner; 
import java.io.File; 
import java.io.PrintWriter; 


class fileReading {
  
  public static boolean isPrime(int n){
    if(n<=1) return false;
    for(int i = 2; i < n; i++){
      if((n % i == 0) ){
        return false; 
      }
    }
    return true; 
  }
  
  
  public static void main(String[] args) throws Exception {
    File file = new File("numbers.txt"); //File to read numbers from
    Scanner sc = new Scanner(file); 
    int countNegatives = 0; 
    int countPositive = 0;
    int smallNumber = 0; 
    int largeNumber = 0;
    int countNum = 0;
    int sumOfNum = 0; 
    double theAverage = 0; 
    int prime = 0; 
    

    boolean readFirst = false;
    
    
    while(sc.hasNextInt()){
      countNum++;
      int x = sc.nextInt();
      
      // if (!readFirst) {
      //   smallNumber = x;
      //   largeNumber = x;
      //   readFirst = true;
      // }
      
      if(x < 0){
        countNegatives ++; 
      }
      if (x>0){
       countPositive++;
      }
      
      if ( x < smallNumber){
        smallNumber = x; 
      }
      
      if(x > largeNumber){
        largeNumber = x; 
      }
      
      sumOfNum += x; 
      
      if(isPrime(x)){
        prime++; 
      }

    }
    
    
    theAverage = (double)sumOfNum/(double)countNum;
    File file2 = new File("stats.txt"); //File to output to 
    PrintWriter out = new PrintWriter(file2); 
    
    out.println("There are " + countNegatives + " negative numbers in the file"); 
    out.println("There are " + countPositive + " positive numbers in the file"); 
    out.println("The Smallest number is " + smallNumber); 
    out.println("The Largest number is " + largeNumber); 
    out.println("There are " + countNum + " Numbers in the file"); 
    out.println("The Sum of the numbers are " + sumOfNum); 
    out.println("The Average of the numbers are " + theAverage); 
    out.println("There are " + prime + " prime numbers in the file"); 
    
    sc.close();
    out.close(); 
  }
}   
