/**
   This class creates 2 arrays of the same random size and gives random values to 
   each array. Then the merge method combines both the arrays into one and then 
   sorts the arrays using bubble sort. There are 2 methods at the bottom which is
   used to print out the arrays more easily.
*/

public class MergeTester_Banh_v2
{
   public final int minR = 1;
   public final int maxR = 10000;
   public final int arrSize = (int)(Math.random()*(1100-900+1)+900);

   private int[] arr1 = new int[arrSize];
   private int[] arr2 = new int[arrSize];
   private int[] arrF = new int[arrSize*2];
   
   public static void main(String[] args)
   {
      MergeTester_Banh_v2 obj = new MergeTester_Banh_v2();
      obj.genArray(obj.arr1);
      obj.genArray(obj.arr2);
      obj.printArr();
      obj.merge();
      
   }
   
   //it is sorted
   /**
      @param arr this is the array that will be given random values and then sorted 
      
      This method uses Math.random to fill an array with random integers rangining
      from minR to maxR. Then the array is sorted using bubble sort.
   */
   public void genArray(int[] arr)
   {  
      //gives values to arrays
      for(int a = 0; a < arr.length; a++)
      {
         arr[a] = (int)(Math.random()*(maxR - minR)+ minR);
      }
      
      //sorting
      for (int i = 0; i < arr.length-1; i++){
         for (int j = 0; j < arr.length-i-1; j++){
            if (arr[j] > arr[j+1])
            {
               int temp = arr[j];
               arr[j] = arr[j+1];
               arr[j+1] = temp;
            }
         }
      }
   }
   
   /**
      This method merges arr1 and arr2(instance variables) by putting the smaller 
      number first and depends on array1 and array2 to be sorted in order to work
      
   */
   public void merge()
   {           
      int index1 = 0;   //array1
      int index2 = 0;   //array2
      
      
      for (int i = 0; i < arrF.length-1; i++){
         //checks if one array is fully interated through or not
         if(index1 >= arr1.length) 
         {
            arrF[i] = arr2[index2];
            index2++;
            continue;
         }
         if(index2 >= arr2.length) 
         {
            arrF[i] = arr1[index1];
            index1++;
            continue;
         }
         
         //puts the lower element first on the final array
         if(arr1[index1] > arr2[index2])
         {
            arrF[i] = arr2[index2];
            index2++;
         }
         else
         {
            arrF[i] = arr1[index1];
            index1++;
         }
      }
      
      //for the last element
      if(arr1[arr1.length-1] > arr2[arr2.length-1])
         arrF[arrF.length-1] = arr1[arr1.length-1];
      else
         arrF[arrF.length-1] = arr2[arr2.length-1];
      
      printMerged();
   }
   
   /**
      Convienience method to print either arr1 and arr2 or just the combined arr(arrF)
      
      Option for James who uses JGrasp as well:
      The number 25 is NOT a magic number, it is the limit of what can be printed within
      my JGrasp panel and I wanted to see all of the values.
   */
   public void printArr()
   {
      System.out.print("Arr1:");
      int panel = 30;
      for(int i = 0; i < arrSize; i++)
      {
         if(i % panel == 0) System.out.println();
         System.out.print(arr1[i] + ",");
      }
      System.out.println();
      System.out.print("Arr2:");
      for(int a = 0; a < arrSize; a++)
      {
         if(a % panel == 0) System.out.println();
         System.out.print(arr2[a] + ",");
      }
      System.out.println();
      System.out.println();
   }
   
   /**
      Convienience method to print either arr1 and arr2 or just the combined arr(arrF)
      
      The number 25 is NOT a magic number, it is the limit of what can be printed within
      my JGrasp panel and I wanted to see all of the values.
   */
   public void printMerged()
   {
      int panel = 30;
      
      System.out.print("Merged:");
      for(int i = 0; i < arrSize*2; i++)
      {
         if(i % panel == 0) System.out.println();
         System.out.print(arrF[i] + ",");
      }
   }
   
}
