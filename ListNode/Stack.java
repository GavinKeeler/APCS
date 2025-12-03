import java.io.*;
import java.util.*;
public class Stack
{
	private ListNode top;
	public Stack() {
		top=null;
	}
	public void push(Object item) { // pushes item onto the top
    ListNode l=new ListNode(item,top);
    top=l;
	}
	public Object pop() { 		    // removes and returns the element at the top; throws an exception if the stack is empty
    if ( top==null )
    throw new EmptyStackException();
    if(isEmpty())
    return null;
    ListNode l =top;
    top=top.getNext();
    return l.getValue();
	}
	public Object peek() { 		    // returns the element at the top; throws an exception if the stack is empty
    if ( top==null )
    throw new EmptyStackException();
    if(isEmpty())
    return null;
    return top.getValue();
	}
	public boolean isEmpty() {
    return(top==null);
	}
	public String toString() { // returns a string of the items, starting from the bottom and ending with the top, enclosed in square brackets, separated by commas and a space
    if(isEmpty())
    return "[]";
    ListNode temp=top;
    String s=""+temp.getValue();
    temp=temp.getNext();
    while(temp!=null){
        s=temp.getValue()+", "+s;
        temp=temp.getNext();
    }
    return "["+s+"]";
	}
}

