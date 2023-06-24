import java.util.EmptyStackException;
import java.util.LinkedList;

//@author Benjamin Banh

/**
 * This is the Stack Tester which tests the Stack class using the provided tester
 */
public class StackTester_Banh {
    public static void main(String[] args) {
        Stack<String> dishes = new Stack<String>();
		System.out.println("Is it empty: " + dishes.isEmpty());
		System.out.println("Now pushing H, E, L, L, O");
		dishes.push("H");
		dishes.push("E");
		dishes.push("L");
		dishes.push("L");
		dishes.push("O");

		System.out.println("The top element is: " + dishes.peek());

		while (!dishes.isEmpty()){
			System.out.println("Popping: " + dishes.pop());
		}
		System.out.println("Is it empty: " + dishes.isEmpty());
		System.out.println("Now pushing 1");
		dishes.push("1");
		System.out.println("Is it empty: " + dishes.isEmpty());
		System.out.println("Now pushing 2, 3, 4, 5");
		dishes.push("2");
		dishes.push("3");
		dishes.push("4");
		dishes.push("5");
		System.out.println("The top element is: " + dishes.peek());
		System.out.println("Removing " + dishes.pop() );
		System.out.println("Removing "+ dishes.pop() );
		System.out.println("Now pushing Last");
		dishes.push("Last");
		System.out.println("The top element is: " + dishes.peek());

		while (!dishes.isEmpty()){
			System.out.println("Popping: " +dishes.pop());
		}
    }
}

/**
 * This stack implements the interface YoStack to mimic the Stacks using linked list
 * 
 */
class Stack<E> implements YoStack<E>{
    private LinkedList<E> stack;

    public Stack(){
        stack = new LinkedList<>();
    }

	/**
	 * Checks whether the stack is empty is empty 
	 */
    public boolean isEmpty(){
        return stack.isEmpty();
    }

	/**
	 * Adds an element to the end of the stack
	 * @param o the added element
	 * @return	the added element
	 */
    public E push(E element){
        stack.add(element);
        return element;
    }
    
	/**
	 * Removes the last element of the stact
	 */
    public E pop(){
        if(stack.isEmpty() ) throw new EmptyStackException();
        E o = stack.getLast();
        stack.removeLast();
        return o;
    }

	/**
	 * Checks the first element of the stack while not modifying the stack
	 */
    public E peek(){
        if(stack.isEmpty() ) throw new EmptyStackException();
        return stack.getFirst();   
    }

}
/**
 * interface used to implement the Stack class
 */
interface YoStack<E>{
	public boolean isEmpty();
	public E push(E element);
	public E pop();
	public E peek();
}