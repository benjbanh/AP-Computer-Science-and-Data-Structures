//@author Benjamin Banh

public class Array_Work_Banh{
   public static void main(String[] args){
      r7_27_2();
      System.out.println();
      System.out.print("Total: " + r7_27_4());
   }
   
   //R7.27 (#2) 
   public static void r7_27_2(){
      //example 2D array
      int[][] values = new int[5][5];
      for(int i = 0; i < values.length; i++){
         for(int j = 0; j < values[0].length; j++){
            //creates a patter every other line
            if(j%2==0 && i%2==0){
               values[i][j] = 1;
               }
            //completes the checkerboard pattern
            if(j%2 ==1 && i%2 ==1){
               values[i][j] = 1;
               }
               //print 2D array
               System.out.print(values[i][j]);
            }
            System.out.println();
          }
   }
   //R7.27 (#4)
   // @return the total of a 2D array
   public static int r7_27_4(){
      // example 2D array
      int[][] values = {{1,2,3},{4,5,6},{7,8,9}};
      int total = 0;
      
      for(int i = 0; i < values.length; i++){
         for(int j = 0; j < values[0].length; j++){
            total += values[i][j];
         }
      }
    return total;
    }

   
   //R7.30
/* True or False
   a)True
   b)False
   c)False
   d)False
   e)False
   f)False
   g)False
   h)False
*/
   
   //R7.32
/* True or False
   a)True
   b)True
   c)False
   d)True
   e)False
   f)False
*/
   }