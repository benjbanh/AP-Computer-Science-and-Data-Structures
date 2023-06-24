//R11.3, 11.4, 11.11, 11.14, 11.16 (from 7th edition)

//11.3 What happens if you try to open a file for reading that doesn’t exist? What happens 
//if you try to open a file for writing that doesn’t exist?
/** If you try to open a file for reading that does not exist, an exeption will be thrown. 
    If the file is for writing, then a file is created with a length of 0.
*/

//11.4 What happens if you try to open a file for writing, but the file or 
//device is write-protected
/** If you try to open a file for writing but the it is write-protected, then 
    you will only be able to read it and not modify the file. 
*/

//11.11 Why don’t you need to declare that your method might throw an IndexOutOfBounds -
//Exception?
/** You do not need to declare that because IndexOutOfBoundsExceptions is an 
    unchecked exception, and unchecked exceptions don't need to be declared by throws.
*/

//11.14 What can your program do with the exception object that a catch clause receives?
/** The catch clause can analyze the object to find out more details about the failure.
*/

//11.16 What is the purpose of the try-with-resources statement? Give an example of how it
//can be used.
/** The try -with-resources statement ensures that each 
    resource(an object that must be closed when the program is finished using it) 
    is closed at the end of the statement
 */

 //P11.3 Using the mechanism described in Special Topic 11.1, write a program that reads
 //all data from a web page and writes them to a file. Prompt the user for the web page
 //URL and the file.

 import java.io.IOException;
 import java.net.URL;
 import java.util.Scanner;
 import java.io.FileWriter; 
 
 public class Exceptions_BanhP7 {
    public static void main(String args[]) throws IOException {
        Scanner link = new Scanner(System.in);
        System.out.print("Enter HyperLink: ");
        try{
            URL url = new URL(link.nextLine());
            link.close();
            Scanner scan = new Scanner(url.openStream());
            StringBuffer sbuff = new StringBuffer();
            while(scan.hasNext()) {
                sbuff.append(scan.next());
            }
            String result = sbuff.toString();
            FileWriter myFile = new FileWriter("newFile.txt");
            myFile.write(result);
            myFile.close();
            System.out.println("File written");
            
            System.out.println("FILE:" + result + "\n\n\n");
        }
        catch(IOException e){
            System.out.println("Error. Not a valid Hyperlink!");
        }
    }
 }