public class Queue
{
    private ListNode first;
    private ListNode last;
    public Queue(){
        last=null;
        first=null;
    }
    // enqueues obj at the end of the queue
    public void add(Object obj){
        if(first==null){
            first=new ListNode(obj,null);
            last=first;
        }else if(last==null){
            last=new ListNode(obj,null);
            first.setNext(last);
        }else{
            last.setNext(new ListNode(obj,null));
            last=last.getNext();
        }
    }
    // dequeues and returns the element at the front; returns null if the queue is empty
    public Object remove(){
        if(isEmpty())
            return null;
        ListNode f=first;
        first=first.getNext();
        return f.getValue();
    }
    // returns the element at the front of the queue; returns null if the queue is empty
    public Object peek(){
        if(isEmpty())
            return null;
        return first.getValue();
    }
    // returns a boolean if the queue is empty
    public boolean isEmpty(){
        return(first==null);
    }
    // returns a string of the elements enclosed in square brackets, separated by commas and a space
    public String toString(){
        String s="";
        ListNode temp=first;
        while(temp!=null){
            s+=temp.getValue();
            temp=temp.getNext();
            if(temp!=null){
                s+=", ";
            }
        }
        s="["+s+"]";
        return s;
    }
}


