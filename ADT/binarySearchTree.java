/**
 * @author Timotej Kovacka
 */

public class binarySearchTree<Item extends Comparable<Item>> {
	private Node<Item> root;
	
	//Constructor method for BinarySearchTree
	public binarySearchTree() {
		this.root=null;
	}
	
	private static class Node<Item> {
		private Item key;
		private Node<Item> leftChild;
		private Node<Item> rightChild;
		
		//Constructor which creates a node for BST with value item
		public Node(Item item) {
			this.key=item;
			this.leftChild=null;
			this.rightChild=null;
		}
		
		public String toString() {
			String text =  "Key: "+this.key;
			if(this.leftChild!=null) {
				text+=" Left:"+this.leftChild.Key();
			}
			if(this.rightChild!=null) {
				text+=" Right: "+this.rightChild.Key();
			}
			return text;
			
		}
		
		public String Key() {
			return this.key.toString();
		}
	}
	
	//Add method with inserts Item x in BST
	public void add(Item x) {
		Node<Item> node = new Node<Item>(x);
		if (this.root==null) {
			this.root=node;
		} else {
			Node<Item> fNode = this.root;
			Node<Item> pNode;
			while(true) {
				pNode = fNode;
				if(x.compareTo(fNode.key)<0) {
					fNode = fNode.leftChild;
					if(fNode == null) {
						pNode.leftChild = node;
						break;
					}
				} else if(x.compareTo(fNode.key)==0) {
					break;
				} else {
					fNode = fNode.rightChild;
					if(fNode == null) {
						pNode.rightChild = node;
						break;
					}
				}
			}
		}
	}
	
	//Method which removes an Item x from BST with help of a recursive method
	public void remove(Item x) {
		this.root=removeR(this.root, x);
	}
	//Recursive method which finds Item x inside BST and removes it and puts the next larger one in its place
	public Node<Item> removeR(Node<Item> node, Item x) {
		if(node == null) {
			return node;
		}
		if(x.compareTo(node.key)<0) {
			node.leftChild = removeR(node.leftChild, x);
		} else if (x.compareTo(node.key)>0) {
			node.rightChild = removeR(node.rightChild, x);
		} else {
			if(node.leftChild==null) {
				return node.rightChild;
			} else if (node.rightChild==null) {
				return node.leftChild;
			} else {
				node.key=minValue(node.rightChild);
				node.rightChild=removeR(node.rightChild, node.key);
				
			}
		}
		return node;
	}
	
	//Method which finds the node with smallest value
	Item minValue(Node<Item> node) {
		while(node.leftChild!=null) {
			node=node.leftChild;
		}
		return node.key;
	}
	
	//Method which checks if Item x is inside of BST with help of recursive method
	public boolean isElement(Item x) {
		Node<Item> node = isElementR(this.root, x);
		if (node!=null)
			return true;
		else
			return false;
	}
	//Recursive method which goes through each node to check if it has the same value as x
	public Node<Item> isElementR(Node<Item> node, Item x) {
		if(node==null || x.compareTo(node.key)==0) {
			return node;
		}
		if(x.compareTo(node.key)<0) {
			return isElementR(node.leftChild, x);
		}
		else
			return isElementR(node.rightChild, x);
	}
	
	//Method with performs in order traversal on BST and prints value of each node from smallest to largest
	public void inOrderTraversal(Node<Item> focusNode) {
		if(focusNode!=null) {
			System.out.println(focusNode.toString());
			inOrderTraversal(focusNode.leftChild);
			inOrderTraversal(focusNode.rightChild);
		}
	}
	
	//Method which checks if BST is empty
	public boolean setEmpty() {
		if(this.root==null)
			return true;
		else
			return false;
	}
	
	//Method which return the size of a BST with help of a recursive method
	public int setSize() {
		return setSize(this.root);
	}
	//Recursive method which calculates number of nodes in a BST
	public int setSize(Node<Item> node) {
		if(node==null)
			return 0;
		else
			return(setSize(node.leftChild)+1+setSize(node.rightChild));
	}
	
	//Deconstruction method which creates an DLL out of a BST
	public void deconstruct(DoubleLinkedList<Item> dBST,Node<Item> focusNode) {
		if(focusNode!=null) {
			deconstruct(dBST, focusNode.leftChild);
			dBST.add(focusNode.key);
			deconstruct(dBST, focusNode.rightChild);
		}
	}
	
	//Method which returns the height of a BST with help of a recursive method
	public int getHeight() {
		return getHeightR(this.root);
	}
	//Recursive method which scans all walks between nodes to find the greatest length
	public int getHeightR(Node<Item> node) {
		if(node==null || (node.leftChild==null && node.rightChild == null))
			return 0;
		else {
			int leftBST= getHeightR(node.leftChild);
			int rightBST= getHeightR(node.rightChild);
			if(leftBST > rightBST)
				return leftBST+1;
			else
				return rightBST+1;
		}
	}
	
	//Reconstruction method which calls a recursive method to build a BST 
	public void reconstruct(Object[] array) {
		this.root=reconstructR(array, 0, array.length-1);
	}
	//Recursive method for reconstruction which iterates through an array to build a balanced BST
	public Node<Item> reconstructR(Object[] array, int start, int end) {
		if (start>end)
			return null;
		int mid = (start+end)/2;
		@SuppressWarnings("unchecked")
		Node<Item> node = new Node<Item>((Item) array[mid]);
		node.leftChild= reconstructR(array,start, mid-1);
		node.rightChild= reconstructR(array,mid+1, end);
		return node;
	}
	
	//Finds an union of 2 BSTs with deconstructing them to DLL structure and finds the uion there after which it reconstructs it back to a BST
	public binarySearchTree<Item> union(binarySearchTree<Item> secondSet) {
		if(this.root==null)
			return secondSet;
		else if (secondSet.root==null)
			return this;
		DoubleLinkedList<Item> set1 = new DoubleLinkedList<Item>(), set2 = new DoubleLinkedList<Item>(), resultS = new DoubleLinkedList<Item>();
		binarySearchTree<Item> result = new binarySearchTree<Item>();
		this.deconstruct(set1, this.root);
		secondSet.deconstruct(set2, secondSet.root);
		resultS = set1.union(set2);
		result.reconstruct(resultS.getAsArray());
		return result;
	}
	
	public binarySearchTree<Item> naiveUnion(binarySearchTree<Item> secondSet) {
		binarySearchTree<Item> result = secondSet;
		while (this.root!=null) {
			result.add(this.root.key);
			this.remove(this.root.key);
		}
		return result;
	}
	
	//Finds an intersection of 2 BSTs with deconstructing them to DLL structure and finds the intersection there after which it reconstructs it back to a BST
	public binarySearchTree<Item> intersection(binarySearchTree<Item> secondSet) {
		binarySearchTree<Item> result = new binarySearchTree<Item>();
		if (this.root==null || secondSet.root==null)
			return result;
		DoubleLinkedList<Item> set1 = new DoubleLinkedList<Item>(), set2 = new DoubleLinkedList<Item>(), resultS = new DoubleLinkedList<Item>();
		this.deconstruct(set1, this.root);
		secondSet.deconstruct(set2, secondSet.root);
		resultS=set1.intersection(set2);
		result.reconstruct(resultS.getAsArray());
		return result;
	}
	
	//Finds a difference of 2 BSTs with deconstructing them to DLL structure and finds the difference there after which it reconstructs it back to a BST
	public binarySearchTree<Item> difference(binarySearchTree<Item> secondSet) {
		if (secondSet.root==null)
			return this;
		binarySearchTree<Item> result = new binarySearchTree<Item>();
		if (this.root==null)
			return result;
		DoubleLinkedList<Item> set1 = new DoubleLinkedList<Item>(), set2 = new DoubleLinkedList<Item>(), resultS = new DoubleLinkedList<Item>();
		this.deconstruct(set1, this.root);
		secondSet.deconstruct(set2, secondSet.root);
		resultS=set1.difference(set2);
		result.reconstruct(resultS.getAsArray());
		return result;
	}
	
	//Checks if a passed BST is a subset of another
	public boolean isSubset(binarySearchTree<Item> secondSet) {
		if (secondSet.root==null)
			return true;
		DoubleLinkedList<Item> set1 = new DoubleLinkedList<Item>(), set2 = new DoubleLinkedList<Item>();
		this.deconstruct(set1, this.root);
		secondSet.deconstruct(set2, secondSet.root);
		return set1.isSubset(set2);
	}
}
