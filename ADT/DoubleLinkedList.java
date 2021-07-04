/**
 * @author Timotej Kovacka
 */
public class DoubleLinkedList<Item extends Comparable<Item>> {
	private Node<Item> head;
	
	//Constructor for creating a DoubleLinkedList
	public DoubleLinkedList() {
		this.head=null;
	}	
	
	static class Node<Item> {
		private Item key;
		private Node<Item> previous;
		private Node<Item> next;
		
		//Constructor for creating a node of DoubleLinkedList
		public Node(Item item) {
			this.key=item;
			this.previous=null;
			this.next=null;
		}
		
		public String toString() {
			return key+" ";
		}
		
		public Item getKey(){
			return key;
		}
	}
	
	//Adds a unique Item x in DLL at the end or at the start if DLL is empty
	public void add(Item x) {
		Node<Item> node = new Node<Item>(x);
		if (this.head==null) {
			this.head=node;
		} else {
			Node<Item> cnode = this.head;
			while(cnode.next!=null) {
				if(cnode.key.equals(x))
					return;
				cnode = cnode.next;
			}
			if(cnode.key.equals(x))
				return;
			cnode.next=node;
			node.previous=cnode; 
		}
	}
	
	//Removes an Item x from DLL and recreates pointers for previous and next Nodes in DLL
	public void remove(Item x) {
		Node<Item> dnode = this.head;
		while(dnode!=null) {
			if(dnode.key.equals(x))
				break;
			dnode=dnode.next;
		}
		if(dnode.previous!=null) {
			dnode.previous.next=dnode.next;
		} else {
			this.head=dnode.next;
		}
		if(dnode.next!=null) {
			dnode.next.previous=dnode.previous;
		}
	}
	
	//Checks if Item x is present in DLL
	public boolean isElement(Item x) {
		Node<Item> cnode = this.head;
		while(cnode!=null) {
			if (cnode.key.equals(x)) {
				return true;
			}
			cnode=cnode.next;
		}
		return false;
	}
	
	//Checks if DLL is empty
	public boolean setEmpty() {
		if(this.head==null) {
			return true;
		} else {
			return false;
		}
	}
	
	//Finds the size of DLL
	public int setSize() {
		int nelements = 0;
		Node<Item> cnode = this.head;
		while(cnode!=null) {
			nelements++;
			cnode=cnode.next;
		}
		return nelements;
	}
	
	//Creates a deep copy of DLL
	public DoubleLinkedList<Item> deepCopy() {
		DoubleLinkedList<Item> output = new DoubleLinkedList<Item>();
		Node<Item> node = this.head;
		while(node!=null) {
			output.add(node.key);
			node=node.next;
		}
		return output;
	}
	
	//Finds an intersection of 2 DLLs
	public DoubleLinkedList<Item> intersection (DoubleLinkedList<Item> secondSet) {
		DoubleLinkedList<Item> result = new DoubleLinkedList<Item>();
		if (this.head==null || secondSet.head==null)
			return result;
		Node<Item> cnode = secondSet.head;
		while(cnode!=null) {
			if(this.isElement(cnode.key))
				result.add(cnode.key);
			cnode=cnode.next;
		}
		return result;
	}
	
	//Finds an union of 2 DLLs
	public DoubleLinkedList<Item> union (DoubleLinkedList<Item> secondSet) {
		if(secondSet.head==null)
			return this;
		else if (this.head==null)
			return secondSet;
		DoubleLinkedList<Item> result = this.deepCopy();
		Node<Item> cnode = secondSet.head;
		while(cnode!=null) {
			result.add(cnode.key);
			cnode=cnode.next;
		}
		return result;
	}
	
	//Finds a difference of 2 DLLs
	public DoubleLinkedList<Item> difference (DoubleLinkedList<Item> secondSet) {
		if (secondSet.head==null)
			return this;
		else if (this.head==null)
			return new DoubleLinkedList<Item>();
		DoubleLinkedList<Item> ilist = this.intersection(secondSet), result = this.deepCopy();
		Node<Item> cnode = ilist.head;
		while(cnode!=null) {
			result.remove(cnode.key);
			cnode=cnode.next;
		}
		return result;
	}
	
	//Checks if passed DLL is a subset of another
	public boolean isSubset(DoubleLinkedList<Item> secondSet) {
		if (secondSet.head==null)
			return true;
		Node<Item> cnode = secondSet.head;
		while(cnode!=null) {
			if(!this.isElement(cnode.key)) {
				return false;
			}
			cnode=cnode.next;
		}
		return true;
	}
	
	//Creates a string of key values of DLL and returns it
	public String toString() {
		String o="";
		Node<Item> node = this.head;
		while(node!=null) {
			o+=node.toString();
			node=node.next;
		}
		return o;
	}
	
	//Creates an Object array out of a DLL
	public Object[] getAsArray(){
		int setSize = this.setSize();
		Object[] array = new Object[setSize];
		Node<Item> cnode = this.head;
		for(int i=0;i<setSize;i++) {
			array[i]=cnode.key;
			cnode=cnode.next;
		}
		return array;
	}
}
