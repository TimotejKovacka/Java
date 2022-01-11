import java.util.HashMap;

public class Node {
	public HashMap<Character, Node> children;
	
	public Node(){
		this.children = new HashMap<Character, Node>();
	}
}