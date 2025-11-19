// Name:
// Date: 

public class Widget implements Comparable<Widget>
{
   //fields
   private int myCubits;
   private int myHands;
   //constructors
   public Widget(){
       myCubits=0;
       myHands=0;
   }
   public Widget(int c, int h){
       myCubits=c;
       myHands=h;
   }
   public Widget(Widget w){
       myCubits=w.myCubits;
       myHands=w.myHands;
   }
   //accessors and modifiers
   public int getCubits(){
       return myCubits;
   } 
   public int getHands(){
       return myHands;
   } 
   public void setCubits(int c){
       myCubits=c;
   } 
   public void setHands(int h){
       myHands=h;
   } 
   //compareTo(Widget) and equals(Widget)
   public int compareTo(Widget w){
       if(w.myCubits==myCubits)
       return((int)Math.signum(myHands-w.myHands));
       return((int)Math.signum(myCubits-w.myCubits));
   }
   public boolean equals(Widget w){
       return(w instanceof Widget&&compareTo(w)==0);
   }
   public boolean equals(Object o){
       return(o instanceof Widget&&equals((Widget)o));
   }
   //toString
   public String toString(){
       return(myCubits+" cubits "+myHands+" hands");
   }
}
