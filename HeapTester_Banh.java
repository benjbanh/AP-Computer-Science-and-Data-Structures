//@author Benjamin Banh
/**
 * Ignatian Reflection:
 * 
 * I tested the add method using random values, but often found repeat elements which confused
 * me during the testing process, so I resorted to using an array to insert values as it
 * was much easier to test in comparison to copying and pasting heap1.add(#). After the 
 * rigorous testing process, I have concluded that my results match my expectations and 
 * everything works. My testing process using random numbers either had too many 
 * repeat numbers(smaller range) or had numbers too large that rendered the print
 * method useless due to too many digits(larger range). I only tested ints as it was easier
 * for me to identify whether the max heap was functional and did not test including but not
 * limited to : String, char, INTEGER, boolean etc. After this project I am a changed man
 * after learning how to make an array print like a tree and how the parents are associated
 * with the child in the array implementation of a heap. I more thoroughly understand
 * how to both heapify up and down in order to remove and add elements into the heap while 
 * maintaining the max heap functionality.
 * 
 */

public class HeapTester_Banh{
    final static int size = 15;
    public static void main(String[] args) {
        HeapMaker<Integer> heap1 = new HeapMaker<>(2);

        int[] arr = new int[] {98,82,87,78,63,52,46,19,31,16,51,20,28,31,38};
        for(int num: arr)
            heap1.add(num);
        heap1.print();

/**     //OPTIONAL: adds random values to heap1   
        
        for (int i = 0; i < size; i++) 
            heap1.add( (int) (Math.random()*100) );
        heap1.print();
*/

     //removes all nodes one by one and prints them
        System.out.println("\nREMOVE:");
        for (int j = 0; j < size+1; j++){
            System.out.println("    Removed Element: " + heap1.remove(0));
        }

    }
} 

/**
 * This is a class which creates a heap using an array representation. It creates a max-heap
 * and compares them using Comparables through the use of heapifying up and down. The print 
 * method allows the printing of an array in a tree like manner and the array automatically
 * expands once the size reaches a threshhold.
 */
class HeapMaker<E extends Comparable<E>>{
    private Comparable<E>[] heap;
    private int currSize;
    private int maxSize;

    /**
     * Constructor which instantiates the heap Array and size. 
     * @param newSize the size of the array
     */
    @SuppressWarnings("unchecked")
    public HeapMaker(int newSize){
        maxSize = newSize;
        currSize = 0;
        heap = new Comparable[maxSize];
    }

    /**
     * Adds a value to an array at index size, then calls the heapify method 
     * @param val the value to be added
     */
    public void add(Comparable<E> val) {
        if (currSize == maxSize) 
            expand();
        currSize += 1;
        heap[currSize-1] = val;
        heapifyUp(currSize-1);
    }

    /**
     * Removes a node at a given index and returns the removed node's value. It calls the 
     * heapifyDown method in order to maintain the max-heap property
     * @param index the index of the element to be removed in the heap array
     * @return the value of the removed element at index
     */
    public Comparable<E> remove(int index){
        if(heap[0] == null)
            return null;
        Comparable<E> found = heap[index];
        currSize -= 1;
        heap[index] = heap[currSize];
        heap[currSize] = null;
        heapifyDown(index);
        return found;
    }
    
    /**
     * Moves the greatest value to the top and continually swaps the value with its parent
     * @param index the index of the value to be heapified
     */
    @SuppressWarnings("unchecked")
    private void heapifyUp(int index) {
        Comparable<E> newValue = heap[index];
        
        //heap[(index-1)/2] is the parent node
        while (index > 0 && newValue.compareTo((E) heap[(index-1)/2]) > 0) {
            heap[index] = heap[(index-1)/2];
            index = (index-1)/2;
        }
        heap[index] = newValue;
    }  
    
    /**
     * Heap method which starts at the root and then descends to the child nodes. Continues
     * until it reaches the leaf nodes
     * @param index the index of the element to start heapifying at
     */
    @SuppressWarnings("unchecked")
    private void heapifyDown(int index) {
        int bigChild = index;
        Comparable<E> root = heap[index];
        while(index < currSize/2){
            int leftChild = index*2+1;
            int rightChild = index*2+2;
            
            //find larger child element
            if(rightChild < currSize && heap[leftChild].compareTo((E) heap[rightChild]) < 0)
                bigChild = rightChild;
            else
                bigChild = leftChild;
            
            // switch the parent and larger child
            heap[index] = heap[bigChild];
            index = bigChild;
       }
       // place the root at the index where its children are not larger than it
       heap[index] = root;            
    } 

    /**
     * Expands the heap array by double its original size
     */
    @SuppressWarnings("unchecked")
    public void expand(){
        Comparable<E>[] copy = new Comparable[maxSize*2];
        for(int i = 0; i < maxSize; i++)
            copy[i] = heap[i];
        maxSize *= 2;
        heap = copy;
    }
    
    /**
     * Prints the array 2 ways: in array form and heap form.
     * Array Form: [ # # # # # # # # ... ]
     * Tree Form:        #
     *          #               #
     *      #        #       #       #
     */
    public void print(){
        //Array Part
        System.out.print("Array: [");
        for(Comparable<E> value: heap){
            if(value == null)
                break;   
            System.out.print(value + " ");
        }
        System.out.println("]");
        System.out.println("Largest Element: " + heap[0]);
        System.out.println("Array Size: " + (currSize));

        //Tree Part
        int numBlanks = 32;
        int maxPerRow = 1;
        int currItem = 0;
        int curr = 0;

        System.out.println("    HEAP: ");
        
        //doesn't print a tree if there are no values
        if(currSize == 0) 
            return;

        while(true){
            //Start of row spaces
            if(currItem == 0)
                for(int k=0; k<numBlanks; k++)
                    System.out.print(" ");
            
            //Print Element && increment
            System.out.print(heap[curr]);
            curr += 1;
            currItem += 1;

            //if last element in array, break
            if(curr == currSize)
                break;

            //enough items per row(start a new row)
            if(currItem == maxPerRow){
               numBlanks /= 2;
               maxPerRow *= 2;
               currItem = 0;
               System.out.println();
            }

            //print spaces in-between elements
            else
               for(int k=0; k<numBlanks*2-1; k++)
                  System.out.print(" ");
        }  
        System.out.println();
    } 
}