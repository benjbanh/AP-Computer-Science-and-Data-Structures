import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/** 
   This is the Driver class which tests JOptionPane pop-up message,
   the Singleton class, and the Counted Class
*/ 
class CountedTester_Banh_v2
{
   public static void main(String[] args) throws Exception
   {
      //This is the URL to the image provided
       URL image = new URL(
         "https://code.bcp.org/anc/bellarmine-college-preparatory-logo.jpg");
      
      //This creates a pop up which has a title, message, and icon
       Object input = JOptionPane.showInputDialog(null, "What is your name?", "Howya Doin Quiz", 
         JOptionPane.INFORMATION_MESSAGE, new ImageIcon(image), null, null);
       System.out.println("Input: " + input);
       
      
      //Testing the singleton Class
         //Singleton single1 = Singleton.get();
      //These are testing the Counted Class
         //Counted count1 = new Counted();
         //Counted count2 = new Counted();
         //Counted count3 = new Counted();
      System.out.println("Counted count: " + Counted.getCount());
    }
    
} 

/**
   This is a class which can only have one instance of itself
*/
class Singleton
{
   //This is the only singleton instance which will be used in get()
   private static Singleton instanceOne = null;
   
   //This is a constructor to test the class
   private Singleton()
   {
      System.out.println("Singleton Test");
   }
   
/**
   This method replaces instanceOne with a new instance which will
   replace the next Singleton object created having the same 
   memory replace the new object
*/
   public static Singleton get()
   {
     //replaces instanceOne to create the only instance
      if (instanceOne == null) 
      {
         //giving instanceOne new Singleton's value
         instanceOne = new Singleton();
     }
  return instanceOne;
   }
 }
 
 /**
   Class which counts the amount of instances of itself has
   been created
 */
class Counted
{
   //amount of instances
   private static int numInst = 0;
   
   //adds to numInst whenever constructor called
   public Counted()
   {
      numInst++;
   }
    //returns count
   public static int getCount()
   {
      return numInst;
   }
}    