//@author Benjamin Banh

/**
   size method return the occupied spaces of your array list and not the amount of space is allotted. 
   would be nice if you implemented own .toString() method for testing
   You do not handle index out of bounds errors.
   Objects names should be at least a little more descriptive, 
   Improper spacing with ending curly braces and in main, 
   Some methods don't check for indexes outside over size and negatives, 
   checkValidIndex should just be integrated with other methods and would be more efficient, 
   Output should be a bit more descriptive as to what's going on, 
   Javadoc should be lined up with first letter of class/method signature, 
   Add line breaks between methods and javadoc, 
   Usually tags are below method description in javadoc
*/

public class BellArrayList_v2{
   public static void main(String args[]){
      BellArrayList a1 = new BellArrayList();
      BellArrayList a2 = new BellArrayList(25);
      
      a1.add("1");
      a1.add("2");
      a1.add("3");
      a1.add("4");
      a1.add("5");
      a1.add("6");
      a1.add(3,"TEST ADD INDEX");
      a1.remove(0);
      a1.set(15,"SET TEST");
      
      
      a2.add("Mary");
      a2.add("Had");
      a2.add("Little Mistake");
      a2.add("Little Lamb");
      a2.add(2,"A");
      a2.remove(3);
      
      System.out.println("Array #1: "); 
         a1.printAll();
      System.out.println("Size: " + a1.size());
      System.out.println("Get: " + a1.get(2));
      System.out.println("Array #2: ");
         a2.printAll();
           
  }
}//end of Main

/**
   This is the BellArrayList class which tries to replace the arraylist package and only works for strings.
   It has the size(), get(), set, add(), add(index), and remove() methods. If the array need to become larger,
   the checkGrow() method checks if it does and doubles the size of the current array. 
*/

class BellArrayList{
   private String[] arr;

/**
   This constructor creates instanciates arr the array to have a length of 10 elements as a the default
*/
   public BellArrayList(){
      arr = new String[10];
   }
/**
   @param int n sets the size of the arr
   
   This is a constructor which instanciates the array's length to the given parameter.
*/
   public BellArrayList(int n) {
      arr = new String[n];
   }
/**
   @return the size of the array
   
   This method returns the size of the array as well as checking if the size needs to 
   be increased or not.
*/
   public int size(){
      checkGrow();
      for(int i = 0; i < arr.length; i++)
      {
         if(arr[i] == null)
            return i;
      }
      return arr.length;
   }
/**
   @param index is the index accessed of the array
   @return the element at the index of arr
   
   This method returns the element at the index of arr while checking if the index is valid
   within the length of the array.
*/
   public String get(int index){
      if(index > arr.length) {
         throw new IndexOutOfBoundsException("The number was not an index of the array");
         }
      else{
         return arr[index];
      }
   }
/**
  @param index is the index accessed of the array
  @param item is the element added to the array
  
  This method replaces the object detected at index with item. The elements after index
  are shifted one to the right.
*/
   public void add(int index, String item){      
      checkGrow();
      for( int i = arr.length-2; i >= index ; i-- ){
            arr[i+1] = arr[i];
      }
      arr[index] = item;
   }
/**
   @param item is the element added to the array
   
   This method replaces item with the first found null element of the array
*/
   public void add(String item){
      checkGrow();
      for(int i = 0; i < arr.length; i++){
         if( arr[i] == null){
            arr[i] = item;
            break;
            }
       }
   }
/**
   @param index is the index of the array that will be removed
   @return the element at the index that will be removed
   
   This method removes the element at index and shifts the elements of the array
   to the left while also returning the String removed.
*/
   public String remove(int index){
      String ret = arr[index];
      int size = arr.length;
      
      checkGrow();
      for (int i = index + 1; i < size; i++){
         arr[i - 1] = arr[i];
      }
      size--;
      return ret;
   }
/**
   @param index is  the index of the array that item will replace
   @param item is the String that will replace the element at index
   @return returns the previous element at index before method occurs
   
   This method sets the element at index with item, replacing it and returning
   the replaced String
*/
   public String set(int index, String item){
      checkValidIndex(index);
      String ret = "";
      for(int i = 0; i < arr.length; i++){
         if(i == index){
            ret = arr[i];
            arr[i] = item;
            }
         }
         return ret;
   }
/**
   Non required methods
*/
   private void checkGrow(){
      int newSize = arr.length * 2;
      if(arr[arr.length-1] != null ){
         //no packages
         String[] temp = arr;
         arr = new String[newSize];
         for(int i = 0; i < temp.length; i++){
            temp[i] = arr[i];
         }
      }
   }
   
   private void checkValidIndex(int index){
      boolean tr = false;
      while(!tr){
         if(index >= arr.length){
            String[] temp = new String[arr.length];
            for(int a = 0; a < temp.length; a++){
               temp[a] = arr[a];
            }
            arr = new String[arr.length * 2];
            for(int i = 0; i < temp.length; i++){
               arr[i] = temp[i];
            }
         }
         else{
            tr = true;
         }
      }
   }
/**
   This method prints the array and saves time from copying and pasting
*/
   public void printAll(){
       System.out.print("[");
       for(String str: arr){
         System.out.print(str + ",");
       }
       System.out.println("]");
    }
   
}//end of BellArrayList 

