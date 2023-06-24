
/**
//2.
public Object removeFirst() {
	if (first == null)
		throw new NoSuchElementException();
	Object element = first.data;
	first = first.next;
	return element;
}

//3
public Object next(){
   if (!hasNext()) 
       throw new NoSuchElementException(); 
   previous = position; 
   isAfterNext = true;
   if (position == null)
      position = first;
   else
      position = position.next;
   return position.data;
}

//4
public void remove(){
    if( isAfterNext == false )
        throw new IllegalStateException();
    if( position == first )
        removeFirst();
    else{
        previous.next = position.next;
    }
    position = previous;
    isAfterNext = false;
}

//5
public void add(Object element){
    if (position == null)
    {
       addFirst(element);
       position = first;
    }
    else
    {
       Node newNode = new Node();
       newNode.data = element;
       newNode.next = position.next; 
       position.next = newNode; 
       position = newNode; 
    }
    isAfterNext = false; 
}

//6
public void set(Object element){
    if( isAfterNext == false )
        thrown new IllegalStateException();
    position.data = element;
}

*/

//8
import java.util.NoSuchElementException;
 
class StackArray
{ 
   private Object[] item; // The array where elements are stored
   private int open = 0;  // The index of the first empty location in the stack
   private int size = 2;  // The current number of item locations in the stack
 
  public StackArray()
  {
     item = new Object[size];
  }
  
  public void push(Object element)
  {
    if( open >= size ){
       size = size * 2;
       Object[] arr = new Object[size];

       for( int i = 0; i < item.length; i++ ){
            arr[i] = item[i];
       }
       item = arr;
    }
    
    item[open] = element;
    open++;
  }
  
  public Object pop()
  {
    if( open == 0 )
        throw new NoSuchElementException();
    else{
        open--;
        Object returnObj = item[open];
        item[open] = null;
        return returnObj;
    }
  }
  
  public boolean isEmpty()
  {
     return open == 0;
  }
  
  public String toString()
  {
     if (open == 0) { return "[]"; }
     String temp = "[" + item[0];
     int i = 1;
     while (i < open)
     {
        temp = temp + ", " + item[i];
        i = i + 1;
     }
     temp = temp + "]";
     return temp;
  }  
}


public class Stacks_Asg1_Lists_Lab{
  public static void main(String[] args)
  {
     StackArray sa = new StackArray();
     sa.push("a");
     sa.push("b");
     sa.push("c");
     sa.push("d");
     sa.push("e");
     System.out.println(sa);
     System.out.println(sa.pop());
     System.out.println(sa);
     System.out.println(sa.pop());
     System.out.println(sa);
     System.out.println(sa.pop());
     System.out.println(sa);
     System.out.println(sa.pop());
     System.out.println(sa);
     System.out.println(sa.pop());
     System.out.println(sa);
     System.out.println(sa.pop());
  }
}

