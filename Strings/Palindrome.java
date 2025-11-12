// Name:  
// Date: 

public class Palindrome
{
   private String mySentence;

   //Precondition:  str is not empty.
   public Palindrome( String str )
   { 
      mySentence=str;
   }
   public String getSentence()
   {
      return mySentence; 
   }

//Returns a string of mySentence with all blanks removed.
//Postcondition:  Returned string contains just one word.
   public String removeBlanks()
   {  
       return(mySentence.replace(" ",""));
   }

//Returns a string of mySentence with all letters in lowercase.
//Postcondition:  Number of words is not changed.
   public String lowerCase()
   {  
      return(mySentence.toLowerCase());
   }

//Returns a string of mySentence with all punctuation removed.
//Postcondition:  Number of words is not changed.
   public String removePunctuation( )
   { 
    String s = "";
    for(int i =0;i<mySentence.length();i++)
            if(!",./;:'\"?<>[]{}|` ~!@#$%^&*()".contains(mySentence.substring(i,i+1)))
            s+=mySentence.charAt(i);
    return s;
   }
   
   //cleans up mySentence
   //prints the cleaned-up mySentence
   //returns true if mySentence is a palindrome, false otherwise.
   //calls the 3-arg isPalindrome(String, int, int)
   public boolean isPalindrome()
   {
    Palindrome s = new Palindrome(mySentence.toLowerCase());
    String s2= s.removePunctuation();
        System.out.println(s2);
    return isPalindrome(s2,0,s2.length()-1);
   }
	
	//Precondition: s has no blanks, no punctuation, and is in lower case.
	//Recursive method. Returns true if s is a palindrome, false otherwise.
   public boolean isPalindrome( String s, int start, int end )
   {
    if(start==end||start==end-1)
        return(s.charAt(start)==s.charAt(end));
    if(s.charAt(start)==s.charAt(end))
    return isPalindrome(s,start+1,end-1);
    return false;
   }
}


