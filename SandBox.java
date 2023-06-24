import java.lang.Math;
import java.util.Random;


public class SandBox{
   public static void main(String[] args) {
      Testing test1 = new Testing();
      
      int[] arr = {10,9,8,7,6,5,4,3,2,1,0};
      test1.lexoSort();
      test1.randomInt();
      test1.shuffle();
      test1.removeElement();
      test1.getBiggest();
      test1.compareTo();
      test1.shiftRight();
      test1.shiftLeft();
      test1.quickSort(arr, 0, 3);
      Testing.printArr(arr);

      
      
   }
}
class Testing{

//lexographicly sort an array of strings
   public void lexoSort(){
      String[] words = {"A","b","c","d"};
           
      //sorting
      for(int a = 0; a < words.length-1; a++){
         for(int b = 0; b < words.length; b++){
            if(words[a].compareTo(words[b]) > 0){
               String temp = words[a];
               words[a] = words[b];
               words[b] = temp;
            }
         }
      }
      System.out.print("Sorted: ");
      for(String str: words){
         System.out.print("[" + str + "] ");
      }
      System.out.println();
   }
    
   public void randomInt(){
      int max = 0;
      int min = 10;
      int range = max - min + 1;
      
      System.out.print("Random Int: [");
      for(int i = 0; i < 10; i++){
       //(int)(Math.random()*(max-min+1)+min)
         int rand = (int)(Math.random() * range) + min;
         System.out.print(rand + ", ");
      }
      System.out.println("]");
   }
     
   public void spamRandom(){
      Random r = new Random();
      System.out.println("int: " + r.nextInt() );
      System.out.println("int: " + r.nextInt() );
      System.out.println("double: " + r.nextDouble() );
      System.out.println("double: " + r.nextDouble() );
   }
   
   public void shuffle(){
      int[] array = {1,2,3,4,5};
            
      for(int index = 0; index < array.length; index++){
         int randomIndex = (int) (Math.random() * index);
         
         //shuffle
         int temp = array[index];
         array[index] = array[randomIndex];
         array[randomIndex] = temp;
      }
      System.out.print("Shuffled: ");
      
      for(int integer: array){
         System.out.print( integer + " ");         
      }
      System.out.println();
   }
      
   public void removeElement(){
      int[] values = {1,2,3,4,5};
      int pos = 2;
      int currentSize = values.length;
      for (int i = pos + 1; i < currentSize; i++){
         values[i - 1] = values[i];
      }
      currentSize--;
      System.out.print("Remove element: ");
      for(int a: values){
         System.out.print(a);
      }
      System.out.println();
   }
   
   public void getBiggest(){
      int[] values = {2,65,39,01,69};
      int big = 0;
      
      for(int val: values){
         if(big < val){
            big = val;
         }
      }
      System.out.println("Largest Int: " + big);
   }
   
   public void compareTo(){
      String str1 = "abba";
      String str2 = "baab";
      
      int ret = str1.compareTo(str2);
      
      System.out.println("Compare to: " + ret);
   }

   public static int binarySearch(String arr[], int a, int b, String str ){
      if (b >= 1) {
          int mid = (a+b) / 2;
          
          if(arr[mid].equals(str)){
              return mid;
          }
          if (arr[mid].compareTo(str) > 0){
              System.out.println("left");
              return binarySearch(arr, a, mid - 1, str);    
          } 
          if (arr[mid].compareTo(str) < 0){
              System.out.println("right");
              return binarySearch(arr, mid + 1, b, str);
          } 
      }               
  return -1;
}
   
   public int fib( int n ){ 
      if (n <= 1) 
         return n;
      return fib(n-1) + fib(n-2);
    
   } 

   public void shiftRight(){
      int[] num = {1,2,3,4,5};
      int last = num[num.length-1];          // save off first element

      // shift right
      for( int i = num.length-2; i >= 0 ; i-- ){
         num[i+1] = num[i];
      }

      // wrap last element into first slot
      num[0] = last;
      
      System.out.print("Shift Right: ");
      for(int a: num){
         System.out.print(a);
      }      
      System.out.println(); 
   }

   public void shiftLeft(){
      int[] arr = {1,2,3,4,5};
      int[] newArr = new int[arr.length];
    
      for (int i = 0; i < arr.length-1; i++) {
         if (i > 0){
            newArr[i] = arr[i+1];
         }
      }
      if (arr.length > 1){
         newArr[0] = arr[1];
      }
      newArr[arr.length-1] = arr[0];

      System.out.print("Shift Left: ");
      for(int a: newArr){
         System.out.print(a);
      }      
      System.out.println(); 
   }
         
   public static void printArr( int[] arr ){
      for(int a : arr){
         System.out.print(a + ",");
      }
      System.out.println();
   }

   /**
    * The selection sort algorithm sorts an array by repeatedly finding 
    * the minimum element (considering ascending order) from unsorted part 
    * and putting it at the beginning. The algorithm maintains two 
    * subarrays in a given array.
    * @param arr
    */
   public static void selectionSort(int[] arr){
      for (int i = 0; i < arr.length; i++) { 
         int pos = i; 
         
         for (int j = i+1; j < arr.length; j++) {
            if (arr[j] < arr[pos]) pos = j;
         }
         int temp = arr[pos];
         arr[pos] = arr[i]; 
         arr[i] = temp; 
      }
       
      System.out.print("Selection Sort:");
      printArr(arr);
   } 
    
   /**
    * Swaps two adjacent integers and continues till all sorted
    * @param arr
    */
   public static void bubbleSort(int[] arr){

      for (int i = 0; i < arr.length-1; i++){
         for (int j = 0; j < arr.length-i-1; j++){
            if (arr[j] > arr[j+1])
            {
                // swap arr[j+1] and arr[j]
               int temp = arr[j];
               arr[j] = arr[j+1];
               arr[j+1] = temp;
            }
         }
      }
             
      System.out.print("Bubble Sort:");
      printArr(arr);
   }
   
   /**
    * Insertion sort is a simple sorting algorithm that works 
    * similar to the way you sort playing cards in your hands. 
    * The array is virtually split into a sorted and an unsorted part. 
    * Values from the unsorted part are picked and placed at the correct 
    * position in the sorted part.
    * @param arr
    */
   public static void insertionSort(int[] arr){
      for (int i = 1; i < arr.length; i++) {
         int next = arr[i];
         while (i > 0 && arr[i - 1] > next) {
            arr[i] = arr[i - 1];
            i--;
         }
         arr[i] = next;
      }
      System.out.print("Insertion Sort:");
      printArr(arr);
   }

   //Merge Sort Part 1
   public static void mergeSort(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
  
        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];
  
        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
  
        /* Merge the temp arrays */
  
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
  
        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
  
        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
  
        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
  
    /**
     * Merge Sort is a recursive algorithm and time complexity can be 
     * expressed as following recurrence relation. 
     * T(n) = 2T(n/2) + θ(n)
     *  
     * @param arr array
     * @param l   beginning of sort search index
     * @param r   end of sort seach index
     */
    public static void mSort(int arr[], int l, int r)
    {
        if (l < r) {
            // Find the middle point
            int m =l+ (r-l)/2;
  
            // Sort first and second halves
            mSort(arr, l, m);
            mSort(arr, m + 1, r);
  
            // Merge the sorted halves
            mergeSort(arr, l, m, r);
            printArr(arr);
        }
        System.out.print("Merge Sort: ");
         printArr(arr);
    }

   /* This function takes last element as pivot, 
	places the pivot element at its correct 
	position in sorted array, and places all 
	smaller (smaller than pivot) to left of 
	pivot and all greater elements to right 
	of pivot */
	public int partition(int arr[], int low, int high) 
	{ 
		int pivot = arr[high]; 
		int i = (low-1); // index of smaller element 
		for (int j=low; j<high; j++) 
		{ 
			// If current element is smaller than or 
			// equal to pivot 
			if (arr[j] <= pivot) 
			{ 
				i++; 

				// swap arr[i] and arr[j] 
				int temp = arr[i]; 
				arr[i] = arr[j]; 
				arr[j] = temp; 
			} 
		} 

		// swap arr[i+1] and arr[high] (or pivot) 
		int temp = arr[i+1]; 
		arr[i+1] = arr[high]; 
		arr[high] = temp; 

		return i+1; 
	} 


	/* The main function that implements QuickSort() 
	arr[] --> Array to be sorted, 
	low --> Starting index, 
	high --> Ending index */
	public void quickSort(int arr[], int low, int high) 
	{ 
		if (low < high) 
		{ 
			/* pi is partitioning index, arr[pi] is 
			now at right place */
			int pi = partition(arr, low, high); 

			// Recursively sort elements before 
			// partition and after partition 
			quickSort(arr, low, pi-1); 
			quickSort(arr, pi+1, high); 
		} 
	} 

   
    
}//end of Testing bracket
    
/* Testing Zone



*/