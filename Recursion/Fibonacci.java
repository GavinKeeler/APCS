// Name:
// Date:
  
import java.util.*;
public class Fibonacci
{
   public static void main(String[] args)
   {
      long start, end, fib; //why long?
      int lastFibNumber = 43;
      int[] fibNumber = {1};
      System.out.println("\tFibonacci\tBy Iteration\tTime\tby Recursion\t Time");
      for(int n = fibNumber[0]; n <= lastFibNumber; n++)
      { 
         start = System.nanoTime();
         fib = fibIterate(n);
         end = System.nanoTime();
         System.out.print("\t\t" + n + "\t\t\t" + fib + "\t\t\t\t\t\t" + (end-start)/1000.);
         start = System.nanoTime();   	
         fib = fibRecur(n);      
         end = System.nanoTime();
         System.out.println("\t\t\t" + fib + "\t\t\t\t" + (end-start)/1000.);
      }
   }
   
   /**
    * Calculates the nth Fibonacci number by interation
    * @param n A variable of type int representing which Fibonacci number
    *          to retrieve
    * @returns A long data type representing the Fibonacci number
    */
   public static long fibIterate(int n)
   {
       if(n<=2)
           return 1;
       int a=1,b=0,temp=0;
       for(int i =0;i<n;i++){
           temp+=a;
           a=b;
           b=temp;
       }  
       return temp;
   }
 
   /**
    * Calculates the nth Fibonacci number by recursion
    * @param n A variable of type int representing which Fibonacci number
    *          to retrieve
    * @returns A long data type representing the Fibonacci number
    */
   public static long fibRecur(int n)
   {
        if(n<=2)
           return 1;
        return (fibIterate(n-1)+fibIterate(n-2));
   }
}

