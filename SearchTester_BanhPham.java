import java.util.Scanner;

public class SearchTester_BanhPham {
    /**
	 * @param args
	 */
	public static void main(String[] args) {
		// Ask the user for search string
		Scanner myScan = new Scanner(System.in);
		System.out.println("Enter name: ");
		String input = myScan.nextLine();
        System.out.println("\"normal\" or \"recursive\"? ");
		String input2 = myScan.nextLine();
        while (!input2.equals("normal") && !input2.equals("recursive")) {
            System.out.println("\"normal\" or \"recursive\"? ");
		    input2 = myScan.nextLine();
        }
        myScan.close();
        int index;
        if (input2.equals("normal")) {
            index = binSearch( getList(), input );
        } else {
            index = binSearchWreck(getList(), 0, getList().length-1, input);
        }
		System.out.println(input + " found in list through binary search at index: " + index);

	}
	public static String[] getList() {
		String[] names = { "Adams", "Amarillas", "Baxter", "Eder", "Giradaux", 
                                             "Gonzalez", "Hansbrough", "Janda", "Kniffen", 
                                             "Lambert", "Mathurin", "McCrystal", "Molina", 
                                             "Preciado", "Reyerson", "Tam", "Ward", "Wolf", 
                                             "Wong", "Zabinski" };
		return names;
		
	}

    /**
     * @author Alex Pham
     * @param arr the array
     * @param str the string to search
     * @return index
     */
    public static int binSearch(String[] arr, String str) {
        int min = 0;
        int max = arr.length - 1;
        int mid = (max + min) / 2;
        while (!arr[mid].equals(str)) {
            if (str.compareTo(arr[mid]) > 0) {
                if (min == max) {
                    return -1;
                }
                min = mid + 1;
                mid = (max + min) / 2;
            } else {
                if (min == max) {
                    return -1;
                }
                max = mid - 1;
                mid = (max + min) / 2;
            }
        }
        return mid;
    }

    /**
     * @author Ben Banh
     * @param arr array
     * @param a min
     * @param b max
     * @param str string to search
     * @return index
     */
    public static int binSearchWreck(String arr[], int a, int b, String str ){
        int mid = (a+b) / 2;
        
        if(arr[mid].equals(str)){
            return mid;
        } else if (arr[mid].compareTo(str) > 0){
            if (a == b) {return -1;}
            return binSearchWreck(arr, a, mid - 1, str);    
        } else {
            if (a == b) {return -1;}
            return binSearchWreck(arr, mid + 1, b, str);
        } 
    }
}