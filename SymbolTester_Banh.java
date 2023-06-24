import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

/**
 * driver class for the teater
 */
public class SymbolTester_Banh{
    public static void main(String[] args) {
        Interpreter newInterpreter = new Interpreter();
        newInterpreter.start();
    }
}

/**
 * This is the interpreter class which mimicks a made-up comp sci language without a name
 * It stores variables in a hashmap named varMap, can print, loop etc.
 */
class Interpreter{
    private HashMap<String,String> varMap;

    /**
     * Instantiates varMap
     */
    public Interpreter(){
        varMap = new HashMap<String,String>();
    }
    
    /**
     * Creates the loop that will continue unless scanStr is equal to "End"
     * interprets the statement that the user inputs
     */
    public void start(){
        Scanner scan = new Scanner(System.in);
        String scanStr;

        System.out.println("Starting Interpreter. type \"help\" for a list of commands ");
        while(scan.hasNext()){
            scanStr = scan.nextLine();
            if(interpret(scan,scanStr) == false){
                break;
            }
        }
    }

    /**
     * When called, it compares the values from the HashMap symbol table
     * in order to find a matching method to execute
     * @param scan the scanner reused from the start method
     * @param input the user inputed string 
     */
    public boolean interpret( Scanner scan, String input ){
        System.out.print("*");
        String strArr[] = input.split(" ");
        String command = strArr[0];

        if(command.equals("var")){
            varMethod(strArr);
            return true;
        }
        if(command.equals("print")){
            printMethod(strArr);
            return true;
        }
        if(command.equals("calc")){
            calcMethod(strArr);
            return true;
        }
        if(command.equals("loop")){
            loopMethod(scan, strArr);
            return true;
        }
        if(command.equals("dump")){
            System.out.println("    KEY: " + varMap.keySet().toString());
            System.out.println("*    VAL: " + varMap.values().toString());
            return true;
        }
        if(command.equals("help")){
            System.out.println("Help Menu");
            System.out.println("  var: instantiate a variable | var x = 10");
            System.out.println("  print: prints variable value or print calculations | print x");
            System.out.println("  calc: does calculations| calc x * 5");
            System.out.println("  loop: creates a subroutine | loop/break ");
            System.out.println("  dump: prints all values in varMap | dump ");
            System.out.println("  help: brings up help menu | help ");
            System.out.println("  End: terminates interpreter | End ");
            return true;
        }
        if(input.equals("End")) {
            System.out.println("Terminated");
            scan.close();
            return false;
        }
        else{                   //error try again
            System.out.println("Please enter a propper command. Try Again");
            return true;
        } 
    }

    /**
     * Adds variables to the varMap hashMap, mutates already added variables
     * @param input The entire string which is seperated into key and value
     */
    public void varMethod(String[] strArr){
        //checks left of "=" for key and right for value
        
        String left = "";
        String right = "";
        for(int i = 0; i < strArr.length; i++){
            if(strArr[i].equals("=")){
                left = strArr[i-1];
                for(int j = i+1; j < strArr.length; j++)
                    if(j != strArr.length-1)
                        right += strArr[j] + " ";   //added space
                    else
                        right += strArr[j];
            }
        }
        
        //if either value is equal to null and then asks to try again
        if(left.equals("")||right.equals("")){
            System.out.println("    Try again");
        }
        //mutate preexisting variables in varMap
        else if(varMap.containsKey(left)){
            varMap.replace(left, right);
        }
        //add to varMap
        else{
            varMap.put(left,right);
        }
        System.out.println("Key: |" + left + "| Right: |" + right + "|");
    }

    /**
     * Prints out inputed string by removing the command, substituting variables and
     * from varMap. This method also handles string concatonation and calculations.
     * @param strArr the inputed array which will be printed
     */
    //change strings to integer to calculate
    public void printMethod(String[] strArr){
        String expr = "";
        
        //fill expression without command and substitute variables
        for(int j = 1; j < strArr.length; j++){
            if(varMap.containsKey(strArr[j]))
                expr += varMap.get(strArr[j]);
            else
                expr += strArr[j];
        }
        
        //calculate
        try{
            Calc calculator = new Calc();
            int answer = calculator.evaluate(expr);
            System.out.println(answer);
        }

        //print
        catch(Exception e){
            //prints out values of new Array            
            System.out.println(expr);
        }
    }

    /**
     * Calculates expressions using the Calc class(stack based calculator).
     * @param strArr the inputed expression which will be concatenated to an expression
     * @return the answer to the calculations
     */
    public int calcMethod(String[] strArr){
        String expr = "";
        //expand array and substitute variables
        for(String str: strArr){
            if(varMap.containsKey(str))
                expr += varMap.get(str);
            else
                expr += str;
        }
        try{
            Calc calculator = new Calc();
            int answer = calculator.evaluate(expr);
            System.out.println(answer);
            return answer;
        }
        //for unbalanced equations and such
        catch(Exception e){
            System.out.println("Error in calculations");
            return 0;
        }
    }

    /**
     * Creates a subroutine(either for or while loop) which then mimics the start method
     * @param scan scanner asking for input for which loop and which task
     * @param strArr an array which will be used to reenter the intepret method
     */
    public void loopMethod(Scanner scan, String[] strArr){
        int count = 0;
        System.out.println("for loop/while loop(type \"for\" or \"while\")");
        String askLoop = scan.nextLine();
        System.out.print("Enter task: ");
        String task = scan.nextLine();
        
        //for loop
        if(askLoop.equals("for")){
            System.out.print("Loop how many times: ");
            count = scan.nextInt();
            for(int i = 0; i < count; i++){
                System.out.print("  |");
                interpret(scan, task); 
            }   
        }

        //while loop
        else if(askLoop.equals("while")){
            System.out.println("type break to break subroutine");
            while(true){  
                System.out.print("  |");
                if(scan.nextLine().equals("break"))
                    break;
                interpret(scan, task);
            }
        }

        //invalid loop
        else{
            System.out.print("Not a valid Loop");
        }
        System.out.println("* Loop ended");
    }
}

/**
 * This is a stack based calculator used in the previous assignment
 */
class Calc {
    private char[] tokens;
    private Stack<Integer> values;
    private Stack<Character> ops;
    
    /**
     * Evaluates the expression by converting it to an array of Characters
     * @param expression the expression to be calculated
     * @return the answer to the expression
     */
    public int evaluate(String expression){
        tokens = expression.toCharArray();
        values = new Stack<Integer>();
        ops = new Stack<Character>();

        for (int i = 0; i < tokens.length; i++){
            // space
            if (tokens[i] == ' ')
                continue;
                
            // number
            else if (tokens[i] >= '0' && tokens[i] <= '9'){
                String str = new String();
                 
                // 2+ digits
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
                    str += tokens[i++];

                values.push(Integer.parseInt(str.toString()));
                i--;
            }
 
            // Open bracket
            else if (tokens[i] == '(')
                ops.push(tokens[i]);
 
            // Close bracket
            else if (tokens[i] == ')'){
                while (ops.peek() != '(')
                    values.push(solve(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }
 
            // Operator.
            else if (tokens[i] == '+' || tokens[i] == '-' ||
                     tokens[i] == '*' || tokens[i] == '/') {

                while (!ops.isEmpty() && getPrecedence(tokens[i], ops.peek()))
                    values.push(solve(ops.pop(), values.pop(), values.pop()));

                ops.push(tokens[i]);
            }
        }
 
        while (!ops.isEmpty())
            values.push(solve(ops.pop(), values.pop(), values.pop()));
 
        //answer is top of values
        return values.pop();    
        
        }
 
    /**
     * Checks precedence of operator 1 and operator 2 to see if the 2 opperators have the
     * same precedence
     * @param op1 Operator to be compared with op2
     * @param op2 Operator to be compared with op1
     * @return True if opperators have same precedence, False if not
     */
    public static boolean getPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }
 
    /**
     * Solves by popping 2 values from the values stack and 1 opperand from the ops stack.
     * It then applies to opperator to the numbers in order of which they came from the stack
     * @param op Opperand applied to a and b
     * @param b  Value popped from values stack
     * @param a  Value popped from values stack
     * @return the answer of a (opperand) b
     */
    public static int solve(char op, int b, int a) {
        switch (op){
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':{
                if (b == 0)
                    return 0;
                return a / b;
            }
        }
        return 0;
    }
}