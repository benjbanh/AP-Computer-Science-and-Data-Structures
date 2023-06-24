
// R16.22
/**
 * No you cannot compute the size of a circular implementation of a queue  
 * due to the fact that if the head and tail are the same, you would not 
 * know where the array ends using a currentSize method.
 */

// R16.23 (a, c only)
/**
 * a) 1,2,3,4,5
 * c) 1,2,3,4,5,6,7,8,9,10
 */

// R16.26
/**
 * If you assign a unique ID to each object, then two of the same objects
 * will have different hashCodes which negate the purpose of hashing
 */

// E16.17 (Check Canvas)

/**
public boolean hasNext()
      {
         if (current != null && current.next != null) { return true; }
         for (int b = bucketIndex + 1; b < buckets.length; b++)
         {
            if (buckets[b].hashCode() == 0) { return true; }
         }
         return false;
      }
*/ 

// E16.18

/**
  public boolean add(Object x)
  { 
    
   int h = x.hashCode();
   if (h < 0) { h = -h; }
   h = h % buckets.length;

   //need more space
   if( currentSize > buckets.length ){
      //create new bucket and rehash everything
      currentSize = 0;
      Node[] newBucket = new Node[buckets.length*2];
      for(Node obj: buckets){
         Node newNode = new Node();
         newNode.data = obj.data;
         newNode.next = buckets[h];
         buckets[h] = newNode;
         currentSize++;
      }

   }
   //not enough space
   else if( currentSize < buckets.length/2){
      currentSize = 0;
      Node[] newBucket = new Node[buckets.length/2];
      for(Node obj: buckets){
         Node newNode = new Node();
         newNode.data = obj.data;
         newNode.next = buckets[h];
         buckets[h] = newNode;
         currentSize++;
      }

   }
     
     Node current = buckets[h];
     while (current != null)
     {
        if (current.data.equals(x)) { return false; }
           // Already in the set
        current = current.next;
     }
     Node newNode = new Node();
     newNode.data = x;
     newNode.next = buckets[h];
     buckets[h] = newNode;
     currentSize++;
     return true;
  }
  

  public boolean remove(Object x)
  {
     int h = x.hashCode();
     if (h < 0) { h = -h; }
     h = h % buckets.length;
   
     //need more space
   if( currentSize > buckets.length ){
      //create new bucket and rehash everything
      currentSize = 0;
      Node[] newBucket = new Node[buckets.length*2];
      for(Node obj: buckets){
         Node newNode = new Node();
         newNode.data = obj.data;
         newNode.next = buckets[h];
         buckets[h] = newNode;
         currentSize++;
      }
   }

   //not enough space
   else if( currentSize < buckets.length/2){
      currentSize = 0;
      Node[] newBucket = new Node[buckets.length/2];
      for(Node obj: buckets){
         Node newNode = new Node();
         newNode.data = obj.data;
         newNode.next = buckets[h];
         buckets[h] = newNode;
         currentSize++;
      }
   }

     Node current = buckets[h];
     Node previous = null;
     while (current != null)
     {
        if (current.data.equals(x)) 
        {
           if (previous == null) { buckets[h] = current.next; }
           else { previous.next = current.next; }
           currentSize--;
           return true;
        }
        previous = current;
        current = current.next;
     }
     return false;
  }
*/

// E16.20

/**
public int compress(int num){
   int p = 101;
   int a = 20;
   int b = 90;
   
   return Math.abs((a*num + b) % p) % buckets.length;
}

// E16.21

public int countCol(){
   int[] arr = new int[buckets.length];
   for(int i = 0; i < buckets.length; i++){
      arr[i] = buckets[i];
   }
   int ans = 0;
   for (int a = 0; a < buckets.length; a++){
      for (int b = a+1; b < buckets.length; b++){
         if (arr[a] == arr[b])
             ans++;
      }
   }
   return ans;
}
*/