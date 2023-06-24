import java.util.NoSuchElementException;

class DoubleLinkListTester_Banh_v2<E> {
	public static void main(String args[]){
		DoubleLinkList<String> test = new DoubleLinkList<>();
		
		test.addFirst("C");
		test.addLast("D");
		test.addFirst("B");
		test.addLast("E");
		test.addFirst("A"); 
		test.addLast("F");
		test.addLast("G");
		test.addLast("H");

      //makes ABCDEFGH
	  //keep in mind it counts 0,1,2,3...
		
		System.out.println("Start:");
		System.out.println("List: " + test.toString());
	
      	System.out.println("   getFirst(): " + test.getFirst() );
      	System.out.println("   removeFirst(): " + test.removeFirst() );
		System.out.println("   removeLast(): " + test.removeLast() );
  		System.out.println("List: " + test.toString());
		
		System.out.println("   get(3): " + test.get(3) );
		System.out.println("   add(2,\"0\") "); test.add(2,"0");
		System.out.println("List: " + test.toString());
      	
		System.out.println("   remove(3): " + test.remove(3) );
      	System.out.println("   size(): " + test.size() );
		System.out.println("List: " + test.toString());
	
		System.out.println("   reverse()"); test.reverse();
		System.out.println("List: " + test.toString());

	}
}

/**
 * This is the class implemented that has a variety of mutator and accessor methods 
 * for Nodes. It can add, remove, access, mutate and reverse the Nodes.
 * 
 * It can access Nodes through the first and last node as the next and prev from those.
 */
class DoubleLinkList<E>{
	private Node first;
	private Node last;
   
	private class Node 	{ 
		public E data;
		public Node next;
		public Node prev;
	}
	/**
	 * Constructor to instantiatie instance variables
	 */
	public DoubleLinkList() {
		first = null;
		last = null;
	}
	
	/**
	* @return the first element in the linked list
	*/	
	public E getFirst() {
		if (first == null)
			throw new NoSuchElementException();
		return first.data;
	}
	
	/**
	* Removes the first element in the linked list.
	* @return the removed element
	*/
	public E removeFirst() {
		if (first == null)
			throw new NoSuchElementException();
		E element = first.data;
		first = first.next;
		return element;
	}

	/**
	* Adds an element to the front of the linked list.
	* @param element the data to store in the linked list
	*/
	public void addFirst(E element)  {
		Node newNode = new Node();
		newNode.data = element;
		
		newNode.next = first;
		first = newNode;

		if(first.next == null) {
			last = first;
		}
	}

	/**
	 * New Nodes always are added to the end
	 * @param element the data to store in the linked list
	 */
    public void addLast(E element) {
    	Node newNode = new Node();
		newNode.data = element;

		if(first == null){
			first = newNode;
			first.prev = null;
			first.next = null;

			last = newNode;
			last.prev = null;
			last.next = null;
		}
		else{
			last.next = newNode;
			newNode.prev = last;
			last = newNode;
			last.next = null;
		}
    }
	
	/**
	 * Removes the last node
	 * @return node removed data
	 */
    public E removeLast(){
        if(first == null) return null;

		E element = last.data;
		last = last.prev;
		last.next = null;
		return element;
    }    

    /**
	 * Retrieves the node by the index of first
	 * @param index which index of node to get
	 * @return	the index's node's data
	 */
    public E get(int index){
		if (first == null)
			throw new NoSuchElementException();
		
		Node curr = first;
		int count = 0;
		while(true){
			if(count == index){	
				return curr.data;
			}
			else if(curr == null){
				return null;
			}
			else{
				curr = curr.next;
				count++;
			}
		}
    }

	/**
	 * Adds a new node at the index of index
	 * @param index the index where to add the new node
	 * @param element the data attached to the new node
	 */
    public void add(int index, E element){
      	Node curr = first;
		Node newNode = new Node();
		newNode.data = element;
		int num = 0;

		while(true){
			if(num+1 == index){				//found
				Node temp = curr.next;
				curr.next = newNode;
				newNode.next = temp; 
            	return;
			}
			else if(first == null){          //empty
				newNode.data = element;
				newNode.next = first;
				first = newNode;
				return;
			}
			else if(curr.next == null){		//end
        		curr.next = newNode;
				return;
			}
			else{							//iterate
				num++;
				curr = curr.next;
			}
		}
	}  
	
	/**
	 * Removes a node at an index and returns the data from the removed node
	 * @param index the index of node that will be removed
	 * @return the data of the removed node
	 */
    public E remove(int index){
		int num = 0;
		Node curr = first;
		while(true){
			if(num+1 == index){					//found
				curr = curr.next;
				E element = curr.data;
				curr.prev.next = curr.next;
				curr.next.prev = curr.prev;
				return element;
			}
			else if(curr.next == null ){		//end
				return null;
			}
			else{								//iterate
				curr = curr.next;
				num++;
			}
		}
    }

	/**
	 * Reverses the Nodes as a Single Linked List
	 */
	public void sReverse(){
		Node curr = first;
		int size = size();	//gives the number of nodes
		Node end = null;
		
		while(size != 0){
			while (curr.next != end) {
				E element = curr.next.data;
				curr.next.data = curr.data;
				curr.data = element;
				curr = curr.next;
			}
		end = curr;
		curr = first;
		size--;
		}
	}
	
	/**
	 * Reverses the Nodes order like as a Double Linked List
	 */
	public void reverse(){
		Node curr = first;
		Node end = last;
		int size = size();
		
		while(size >= 0){
			E element = end.data;
			end.data = curr.data;
			curr.data = element;
			curr = curr.next;
			end = end.prev;
			size -= 2;
		}
	}
	
	/**
	 * Returns the amount of nodes by iterating through all nodes till .next is null
	 * @return the amount of nodes
	 */
    public int size(){
		if (first == null)
			return 0;
		
		Node curr = first;
		int count = 0;
		while(true){
			if(curr.next != null){	
				curr = curr.next;
				count++;
			}
			else{
				return count;
			}
		}
    }

	/**
	 * ToString to return all nodes
	 */
	public String toString(){
		Node curr = first;
		String str = "{ ";
		while(true){
			if(curr.next != null){	
				str += curr.data + ", ";
				curr = curr.next;
			}
			else{
				str += curr.data + " }";
				return str;
			}
		}
	}
   

}