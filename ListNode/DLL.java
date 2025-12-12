// Name:
// Date:

//  DoubleLinkedList, circular, with a dummy head node
//  implements some of the List and LinkedList interfaces:
//	 	  size(), add(i, o), remove(i);  addFirst(o), addLast(o);
//  This class also overrides toString().
//  the list is zero-indexed.
//  Uses DLNode.

class DLL
{
	private int size;
	private DLNode head; //points to a dummy node--very useful--don't mess with it
	public DLL()
	{
		head=new DLNode(null,null,null);
		head.setNext(head);
		head.setPrev(head);
		//make it circular

	}

	/* two accessor methods  */
	public int size()
	{
		return size;
	}
	public DLNode getHead()
	{
		return head;
	}

	/* appends obj to end of list; increases size;
		  @return true  */
	public boolean add(Object obj)
	{
		addLast(obj);
		return true;
	}

	/* inserts obj at position index (the list is zero-indexed).
	   increments size.
	   no need for a special case when size == 0.
	   */
	public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real LinkedList is coded
	{
		if( index > size || index < 0 )
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		/* enter your code below  */
		else {
			DLNode current = head;
			for(int i =0; i<index; i++)
				current=current.getNext();
			current.setNext(new DLNode(obj,current,current.getNext()));
			current.getNext().getNext().setPrev(current.getNext());
			size++;
		}
	}

	/* return obj at position index (zero-indexed).
	*/
	public Object get(int index) throws IndexOutOfBoundsException
	{
		if(index >= size || index < 0)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		/* enter your code below  */
		DLNode p = head;
		for(int i =0; i<=index; i++) {
			p=p.getNext();
		}
		return p.getValue();
	}
	/* replaces obj at position index (zero-indexed).
	     returns the obj that was replaced.
	     */
	public Object set(int index, Object obj) throws IndexOutOfBoundsException
	{
		if(index >= size || index < 0)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		/* enter your code below  */
		if(head==null)
			return null;
		Object val=get(index);
		DLNode current = head;
		for(int i =0; i<=index; i++)
			current=current.getNext();
		current.setValue(obj);
		return val;
	}

	/*  removes the node from position index (zero-indexed).  decrements size.
	    @return the object in the node that was removed.
	     */
	public Object remove(int index) throws IndexOutOfBoundsException
	{
		if(index >= size || index < 0)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		/* enter your code below  */
		else {
			size--;
			DLNode current = head;
			for(int i = 0; i<=index; i++)
				current=current.getNext();
			current.getPrev().setNext(current.getNext());
			current.getNext().setPrev(current.getPrev());
			return current.getValue();
		}
	}

	/* inserts obj to front of list, increases size.
	    */
	public void addFirst(Object obj)
	{
		DLNode added=new DLNode(obj,head,head.getNext());
		added.getNext().setPrev(added);
		added.getPrev().setNext(added);
		size++;
	}

	/* appends obj to end of list, increases size.
	    */
	public void addLast(Object obj)
	{
		DLNode added=new DLNode(obj,head.getPrev(),head);
		head.setPrev(added);
		added.getPrev().setNext(added);
		size++;
	}

	/* returns the first element in this list
	   */
	public Object getFirst()
	{
		if(head==null)
			return null;
		return head.getNext().getValue();
	}

	/* returns the last element in this list
	  */
	public Object getLast()
	{
		if(head==null)
			return null;
		return head.getPrev().getValue();
	}

	/* returns and removes the first element in this list, or
	   returns null if the list is empty
	   */
	public Object removeFirst()
	{
		if(head==null)
			return null;
		DLNode removed=head.getNext();
		head.setNext(removed.getNext());
		removed.getNext().setPrev(head);
		size--;
		return removed.getValue();
	}

	/* returns and removes the last element in this list, or
	   returns null if the list is empty
	   */
	public Object removeLast()
	{
		size--;
		if(head==null)
			return null;
		DLNode removed=head.getPrev();
		removed.getPrev().setNext(head);
		head.setPrev(removed.getPrev());
		return removed.getValue();
	}

	/*  returns a String with the values in the list in a
	    friendly format, for example   [Apple, Banana, Cucumber]
	    The values are enclosed in [], separated by one comma and one space.
	    An empty list returns [].
	 */
	public String toString()
	{
		if(size==0)
			return "[]";
		DLNode p = head.getNext();
		String s= "[";
		for(int i =0;i<size-1;i++){
			s+=p.getValue()+", ";
			p=p.getNext();
		}
		s+=p.getValue()+"]";
		return s;
	}
}




