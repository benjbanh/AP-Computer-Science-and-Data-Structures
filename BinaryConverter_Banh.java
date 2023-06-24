import java.util.Stack;

class BinaryConverter_Banh {
	/**
	  printBinary converts a decimal integer to its binary equivalent (and displays it to standard output).
	  @preconditon: decimal > 0
	*/
	public static void printBinary( int decimal ) {
		Stack <Integer> stack = new Stack<>();
        System.out.print(decimal + ": ");
        while(decimal != 0){
            int remainder = decimal % 2;
            stack.push(remainder);
            decimal /= 2;
        }

        while(!stack.isEmpty()){
            System.out.print(stack.pop());
        }
        System.out.println();

	}
	
	public static void main(String[] a) {
	
		printBinary(100);
		printBinary(97);
		printBinary(5);
	}		
}