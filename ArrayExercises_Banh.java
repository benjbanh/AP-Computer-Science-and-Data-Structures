//@author: Benjamin Banh

import java.util.Random;

public class ArrayExercises_Banh{
   public static void main(String[] args){
      //R7.23
      int[] values1 = {0,1,2,3,4,5,5,5};
      System.out.println("Longest Run: " + longestRun(values1));
      //R7.24
      int[] values2 = {0, 1, 2, 3, 4, 5, 20};
      makeCombination(values2,10);
      System.out.println("Randomize: " + forPrint(values2));
      //R7.19
      int[] values3 = {110, 90, 100, 120, 70};
      loseElement(values3,2);
      System.out.println("Deleted:   " + forPrint(values3));

   }

// R 7.23
/* 
   A run is a sequence of adjacent repeated values. Give pseudocode for computing the
   length of the longest run in an array. For example, the longest run in the array with
   elements 
*/
   public static int longestRun(int[] values){
      //starts the count at one due to comparing a different number at the start of a run
      int count = 1; 
      //boolean to return 0 when no run
      boolean notOne = false; 
      for(int i = 0; i < values.length-1; i++){
         if(values[i] == values[i+1]){
            //counts the amount of integers in a sequence
            count++;
            notOne = true;
            }
       }
       if(!notOne){
         count--;
         }
       return count;
    }
    
//R. 7.24
/* 
   What is wrong with the following method that aims to fill an array with random
   numbers? 
*/
   //fixed code
   public static void makeCombination(int[] values, int n){
      for (int i = 0; i < values.length; i++){
         Random generator = new Random(); //creates new random each iteration of loop
         values[i] = generator.nextInt(n);
         }
   }
/* 
   Answer:
   The problem is that the generator object would only produce one random integer 
   and fill the entire array with that one integer as well as the numbers array being 
   extraneous and not correctly transfering its values to values.
*/


//R. 7.19
/*
   Trace the algorithm for removing an element described in Section 7.3.6. Use an array
values with elements 110 90 100 120 80, and remove the element at index 2.
*/
public static void loseElement(int[] values, int index){
   //continue swapping elements after the found index
   boolean found = false;
   for(int i = 0; i < values.length-1; i++){
      if(i == index || found){
         values[i] = values[i+1];
         found = true;
         }
      }
      //change last element to be 0
      values[values.length-1] = 0;
   }
/*
   method to print all the elements in an array
*/
public static String forPrint(int[] arr){
   String str = "[ ";
   for(int a: arr){
      str = str + a + ",";
      }
   str = str + "]";
   return str;
   }
}