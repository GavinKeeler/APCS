Station:
public class Station 
{
    private String stationName;
    private int zone;

    public Station(){
    stationName="";
    zone=0;
    }
    
    public SmartCard(String myName, int myZone){
        stationName=myName, zone=myZone;
    }
    
    public String getName(){
        return myName;
    }
    
    public int getZone(){
        return myZone;
    }
    
    public static String toString(Station station){
        return ("<"+station.getName+">, zone <"+station.getZone+">")
    }
} 

SmartCard:
import java.text.DecimalFormat;

public class SmartCard
{
	// instantiate the constants
	public final static DecimalFormat df = new DecimalFormat("$0.00");
	public final static double MIN_FARE = 0.5;
	// declare the private fields
	private double balance;
	private boolean isBoarded;
	private Station boardedAt;
	// write the one-arg constructor
	public SmartCard(double myBalance) {
		balance=myBalance;
		isBoarded=false;
		boardedAt=null;
	}
	// write four getter methods
	public double getBalance() {
		return balance;
	}
	public String getFormattedBalance() {
		return df.format(balance);
	}
	public boolean getIsBoarded() {
		return isBoarded;
	}
	public Station getBoardedAt() {
		return boardedAt;
	}


	// write the instance methods as described in the handout
	public void board(Station s) {
		if(isBoarded)
			System.out.println("Error: already boarded?!");
		if(balance<0.5)
			System.out.println("Insufficient funds to board. Please add more money.");
		if(!isBoarded&&balance>=0.5) {
			boardedAt=s;
			isBoarded=true;
		}
	}
	public double cost(Station s) {
		return(0.50 + 0.75*Math.abs(boardedAt.getZone()-s.getZone()));
	}
	public void exit(Station s) {
		if(!isBoarded)
			System.out.println("Error: Did not board?!");
		if(balance<cost(s))
			System.out.println("Insufficient funds to exit. Please add more money.");
		if(isBoarded&&balance>0) {
			System.out.println("From "+boardedAt.getName()+" to "+s.getName()+" costs "+df.format(cost(s))+". SmartCard has "+df.format(balance));
			balance-=cost(s);
			isBoarded=false;
			if(boardedAt==s)
			boardedAt=null;
		}
	}
	public void addMoney(double d) {
		balance+=d;
	}


}


