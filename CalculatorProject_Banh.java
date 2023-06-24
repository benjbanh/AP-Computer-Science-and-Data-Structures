import java.util.Scanner;
import java.util.EmptyStackException;
import java.util.LinkedList;
 
public class CalculatorProject_Banh{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Type your expression: ");
        String inputStr = input.nextLine();
        input.close();
   
        CalcTester c = new CalcTester();
        System.out.println(c.evaluate(inputStr));
    }
}
class CalcTester
{
    public int evaluate(String expression)
    {
        char[] tokens = expression.toCharArray();
        CalcStack<Integer> values = new CalcStack<Integer>();
        CalcStack<Character> ops = new CalcStack<Character>();
 
        for (int i = 0; i < tokens.length; i++)
        {
            // space
            if (tokens[i] == ' ')
                continue;
 
            // number
            if (tokens[i] >= '0' && tokens[i] <= '9'){
                String str = new String();
                 
                // 2+ digits
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
                    str += tokens[i++];

                values.push(Integer.parseInt(str.toString()));
                i--;
            }
 
            // Current token is an opening brace,
            // push it to 'ops'
            else if (tokens[i] == '(')
                ops.push(tokens[i]);
 
            // Closing brace encountered,
            // solve entire brace
            else if (tokens[i] == ')')
            {
                while (ops.peek() != '(')
                    values.push(solve(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }
 
            // Current token is an operator.
            else if (tokens[i] == '+' ||
                     tokens[i] == '-' ||
                     tokens[i] == '*' ||
                     tokens[i] == '/')
            {
                // Apply operator on top of 'ops'
                // to top two elements in values stack
                while (!ops.isEmpty() && getPrecedence(tokens[i], ops.peek()))
                    values.push(solve(ops.pop(), values.pop(), values.pop()));
 
                // Push current token to 'ops'.
                ops.push(tokens[i]);
            }
        }
 
        // Entire expression has been
        // parsed at this point, apply remaining
        // ops to remaining values
        while (!ops.isEmpty())
            values.push(solve(ops.pop(), values.pop(), values.pop()));
 
        // Top of 'values' contains result, return it
        return values.pop();
    }
 
    // Returns true if 'op2' has higher
    // or same precedence as 'op1',
    // otherwise returns false.
    public static boolean getPrecedence(char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }
 
    public static int solve(char op, int b, int a)
    {
        switch (op){
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':{
                if (b == 0)
                    throw new UnsupportedOperationException("Cannot divide by zero");
                return a / b;
            }
        }
        return 0;
    }
}


/**
 * @generic <E> Element 
 * This is a mirror of the Stack class from Stack Tester. Both the 
 * evaluate method and postfixFill method use this 
 */
class CalcStack<E>{
   private LinkedList<E> stack;

   public CalcStack(){
      stack = new LinkedList<>();
   }

	/**
	 * Checks whether the stack is empty is empty
    * @return TRUE if empty, FALSE if not empty 
	 */
   public boolean isEmpty(){
      return stack.isEmpty();
   }

	/**
	 * Adds an element to the end of the stack
	 * @param String the added element
	 * @return the added element
	 */
   public E push(E element){
      stack.add(element);
      return element;
   }
   
	/**
	 * Removes the last element of the stack and returns it
    * @return returns popped character
	 */
   public E pop(){
      if( stack.isEmpty() ) 
         throw new EmptyStackException();
      return stack.removeLast();
   }

	/**
	 * Checks the last element of the stack while not modifying the stack
    * @return last element
	 */
   public E peek(){
      if( stack.isEmpty() ) 
         throw new EmptyStackException();
      return stack.peek();   
   }

}