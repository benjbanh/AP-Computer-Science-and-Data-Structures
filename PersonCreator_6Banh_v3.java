//@author Benjamin Banh

/**
   This is the part where I write an essay for the TAs: 
      Please have mercy. I have a GPA.

Heirarchy:
   Person-Teacher-BCP-ASB-ASBpresident
                 -ND
                 -SF
*/

/**
   This is a driver class
*/
public class PersonCreator_6Banh_v3{
   public static void main(String[] args) {    
      Person oldMan = new Teacher("Salary", "Worker", 43);
      oldMan.greet();
      System.out.println( oldMan.toString() + "\n" );
      
      Person maleStudent = new BCP("Bobby", "Bellarmine", 16);
      maleStudent.greet();
      System.out.println( maleStudent.toString() + "\n" );

      Person guy = new ASBpresident( "Jeffery", "Bezos", 57);
      guy.greet();
      System.out.println( guy.toString() + "\n" );
      
      Person girl = new ND( "Jill", "Johnson", 13);
      girl.greet();
      System.out.println( girl.toString() + "\n" );
      
      Person jock = new SF("Tyler", "Brohug", 14);
      jock.greet();
      System.out.println( jock.toString() + "\n" );
      
      System.out.println("---------Testing Methods-----------");
      System.out.print("Compare To Method: ");
      jock.compareTo(oldMan,girl);
      
      System.out.println( "HashCode: " + girl.hashCode() );
      System.out.println( "getFQN: "   +  girl.getFQN(girl) );
      
      System.out.println("Specific Count(SF): " + jock.specificCount());
      System.out.println("Family Count(SF): " + jock.familyCount());
      
      System.out.println("\".equals \" check: " + guy.equals( guy.clone(guy) ) );

   }
}

/**
   This is the abstract superclass Person which has the abstract method greet which 
   is defined in its concrete sub classes while also holding the instance fields of 
   firstName, lastName, age and the greeting message.
*/
abstract class Person{
   private String fName;   
   private String lName;
   private int age;
   private String greeting;
   private static int familyInst;
   private static int instPerson;
   
   public Person( String nameF, String nameL, int year )
   {
      System.out.println("Person born");
      fName = nameF;
      lName = nameL;
      age = year;
      familyInst+= 5;  //amount of subclasses + 1 from Person
      instPerson++;
      
      greeting = "Hi, I am " + getFName() + getLName() + ", a person who now is " + year;
   }
   
   //abstract method greeting which is defined in concrete subclasses' methods
   public abstract void greet();
   
   //accessor methods
   public String getFName()
   {
      return fName;
   }
   
   public String getLName()
   {
      return lName;
   }
   
   public int getYear()
   {
      return age;
   }
   
   public void modGreet( String msg )
   {
      greeting = msg;
   }
   
   public String getGreet()
   {
      return greeting;
   }
   public Person clone(Person a)
   {
      if(a instanceof Teacher) 
         return new Teacher( getFName(), getLName(), getYear() );
      if(a instanceof BCP) 
         return new BCP( getFName(), getLName(), getYear() );
      if(a instanceof ASB) 
         return new ASB( getFName(), getLName(), getYear() );
      if(a instanceof ASBpresident) 
         return new ASBpresident( getFName(), getLName(), getYear() );
      if(a instanceof ND) 
         return new ND( getFName(), getLName(), getYear() );
      if(a instanceof SF) 
         return new SF( getFName(), getLName(), getYear() );
      return null;
   }
   
   public boolean equals(Person temp) 
   {
      return temp instanceof Person && temp.toString().equals(((Person)temp).toString());
   }
      
   public String getFQN( Person a )
   {
      String hash = String.valueOf(a.hashCode());
      String realhash = "getFQN: ";
      for( int i = 0; i < hash.length(); i++ )
      {
         realhash += hash.substring(i,i+1);
         realhash += ".";
      }
      return realhash;
   }
   
   //counting instances method
   public int familyCount()
   {
      return familyInst;
   }
   public void modFamCount( int mod )
   {
      familyInst += mod;
   }
   public int specificCount()          
   {
      return instPerson;
   }

   
   //compareTo method which sorts which comes first
   public void compareTo(Person a, Person b)
   {
      if( a instanceof Teacher  && !(b instanceof Teacher) )
         System.out.println( a.getFName() + " " + a.getLName() 
            + ", "+ b.getFName() + " " + b.getLName() );
         
      else if( b instanceof Teacher  && !(a instanceof Teacher) )
         System.out.println( b.getFName() + " " + b.getLName() + ", " 
                         + a.getFName() + " " + a.getLName() );
      
      else if( a.getLName().compareTo(b.getLName() ) >= 0 ) 
      {
         if( a.getFName().compareTo(b.getFName() ) >= 0 )
         {
            System.out.println( a.getFName() + " " + a.getLName() + ", " 
                         + b.getFName() + " " + b.getLName() );    
         }     
      }
      
      else if( b.getLName().compareTo(a.getLName() ) >= 0 ) 
      {
         if( b.getFName().compareTo(a.getFName() ) >= 0 )
         {
            System.out.println( b.getFName() + " " + b.getLName() + ", " 
                         + a.getFName() + " " + a.getLName() );    
         }     
      }
   }
    
   public String toString()
   {
      return fName + " " + lName;
   }
}

/**
   This is the Teacher subclass which is the direct parent class of 
   BCP,ND, and SF.
   
   @param nameF is the first name to be put in Person constructor
   @param nameL is the last name to be put in Person constructor
   @param year is the age to be put in Person constructor

*/
class Teacher extends Person
{
   private static int instTeach;
   
   public Teacher( String nameF, String nameL, int year ) 
   {
      super( nameF, nameL, year );
      super.modFamCount(-1);   
      instTeach++;
      System.out.println("Teacher born"); 
      
   }
   
   //@override
   public void greet( )
   {
      super.modGreet( "I am Teacher " + super.getFName() + " " + super.getLName() 
                              + " who is " + super.getYear() + " years old" );
                              
   }
   
   public int specificCount()
   {
      return instTeach;
   }
   
   
   public String toString( )
   {  
      //id# (id) of (size): countSelf: first_name last_name Example: id# 17 of size: 9: melissa dorsey
      return "id# " + super.specificCount() + " of size " + super.familyCount()  + ": " + super.getFName()  +  " "  + super.getLName();
   }
   
}


/**
   Direct Super class of ASB 
   
   @param nameF is the first name to be put in Person constructor
   @param nameL is the last name to be put in Person constructor
   @param year is the age to be put in Person constructor
*/
class BCP extends Teacher
{
   private static int instBCP;
   
   public BCP( String nameF, String nameL, int year )
   {
      super( nameF, nameL, year );
      super.modFamCount(-1);
      instBCP++;
      System.out.println("Bellarmine Student born");
   }
   
   //@override
   public void greet(  )
   {
      super.modGreet("I'm a Bell Student, " + super.getFName() + " " +
                        super.getLName()   + "and only " + super.getYear() + "years old");
   }
   
   public int specificCount( )           //
   {
      return instBCP;
   }
   
   public String getFQN( )
   {
      BCP temp = new BCP(super.getFName(), super.getLName(), super.getYear() );
      String hash = String.valueOf(temp.hashCode());
      String realhash = "getFQN: ";
      for( int i = 0; i < hash.length(); i++ )
      {
         realhash += hash.substring(i,i+1);
         realhash += ".";
      }
      return realhash;
      
   }
      
   public String toString( )
   {
      //id# id of size: countSelf: first_name last_name Example: id# 17 of size: 9: melissa dorsey
      return "id# " + super.specificCount() + " of size " + super.familyCount()  + ": " + super.getFName()  +  " "  + super.getLName();
   }
}

/**
   Direct Super Class of ASBpresident
   
   @param nameF is the first name to be put in Person constructor
   @param nameL is the last name to be put in Person constructor
   @param year is the age to be put in Person constructor
*/
class ASB extends BCP
{
   private static int instASB;
   
   public ASB( String nameF, String nameL, int year )
   {
      super( nameF, nameL, year );
      super.modFamCount(-1);
      System.out.println("ASB member born");
      instASB++;
   }
   
   //@override
   public void greet(  )
   {  
      super.modGreet("I am the most superior of all BCP students," + super.getFName() 
                           + " " + super.getLName() + ". Born in " + (2020-super.getYear()));
   }
      
   public int specificCount( )                   //
   {
      return instASB;
   }
   
   public String getFQN( )
   {
      ASB temp = new ASB(super.getFName(), super.getLName(), super.getYear() );
      String hash = String.valueOf(temp.hashCode());
      String realhash = "getFQN: ";
      for( int i = 0; i < hash.length(); i++ )
      {
         realhash += hash.substring(i,i+1);
         realhash += ".";
      }
      return realhash;
      
   }
      
   public String toString( )
   {
      //id# id of size: countSelf: first_name last_name Example: id# 17 of size: 9: melissa dorsey
      return "id# " + super.specificCount() + " of size " + super.familyCount()  + ": " + super.getFName()  +  " "  + super.getLName();

   }
}

/**
   Direct Subclass of ASB
   
   @param nameF is the first name to be put in Person constructor
   @param nameL is the last name to be put in Person constructor
   @param year is the age to be put in Person constructor
*/
class ASBpresident extends ASB
{
   private static int instPres;

   public ASBpresident( String nameF, String nameL, int year )
   {
      super( nameF, nameL, year );
      super.modFamCount(-1);
      System.out.println("ASB president born");
      instPres++;
   }
   
   //@override
   public void greet(  )
   {  
      super.modGreet( "I am the king of ASB, " + super.getFName() + " " + super.getLName() 
                                       + ". Born in " + (2020-super.getYear()) + "." );
   }
   
   public int specificCount( )                    //
   {
      return instPres;
   }
   
   public String getFQN( )
   {
      ASBpresident temp = new ASBpresident(super.getFName(), super.getLName(), super.getYear() );
      String hash = String.valueOf(temp.hashCode());
      String realhash = "getFQN: ";
      for( int i = 0; i < hash.length(); i++ )
      {
         realhash += hash.substring(i,i+1);
         realhash += ".";
      }
      return realhash;
      
   }
   
   public String toString( )
   {
      //id# id of size: countSelf: first_name last_name Example: id# 17 of size: 9: melissa dorsey
      return "id# " + super.specificCount() + " of size " + super.familyCount()  + ": " + super.getFName()  +  " "  + super.getLName();

   }
}

/**
   Direct Subclass of Teacher
   
   @param nameF is the first name to be put in Person constructor
   @param nameL is the last name to be put in Person constructor
   @param year is the age to be put in Person constructor
*/
class ND extends Teacher
{
   private static int instND;
   
   public ND( String nameF, String nameL, int year )
   {
      super( nameF, nameL, year );
      super.modFamCount(-3);
      System.out.println("Notre Dame Student born");
      instND++;
   }
   
   //@override
   public void greet(  )
   {
      super.modGreet("I am a " + super.getYear() + " year old girl by the name of " 
                                 + super.getFName() + " " + super.getLName() + ".");
   }
   
   public int specificCount( )                         //
   {
      return instND;
   }
      
   public String toString( )
   {
      //id# id of size: countSelf: first_name last_name Example: id# 17 of size: 9: melissa dorsey
      return "id# " + super.specificCount() + " of size " + super.familyCount()  + ": " + super.getFName()  +  " "  + super.getLName();

   }
}

/**
   Direct Subclass of Teacher 
   
   @param nameF is the first name to be put in Person constructor
   @param nameL is the last name to be put in Person constructor
   @param year is the age to be put in Person constructor
*/
class SF extends Teacher
{
   private static int instSF;
   
   public SF( String nameF, String nameL, int year )
   {
      super( nameF, nameL, year );
      super.modFamCount(-3);
      System.out.println("Saint Francis Student born");
      instSF++;
   }
   
   //@override
   public void greet(  )
   {
      super.modGreet( "Yo, I am the mighty " + super.getFName() + " " + super.getLName() 
                                       + ". The " + super.getYear() + " year old king/queen.");
   }
   
   public int specificCount( )                      //
   {
      return instSF;
   }
   
   public String toString( )
   {
      return "id# " + super.specificCount() + " of size " + super.familyCount()  + ": " + super.getFName()  +  " "  + super.getLName();

   }
}
