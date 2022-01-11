
public class Trie {
	public Node root;
	private int codeWordLength;
	private int code;

	public Trie(int codeWordLength) {
		this.root = new Node();
		this.codeWordLength = codeWordLength;
		this.code = 0;
	}

	public int getCodewordLength() {
		return this.codeWordLength;
	}

	public void insert(String w) {
		Node n = this.root;
		for (int level = 0, length = w.length(); level < length; ++level) {
			if (n.children.containsKey(w.charAt(level)) == false) {
				n.children.put(w.charAt(level), new Node());
				code++;
				if (code > Math.pow(2, this.codeWordLength))
					this.codeWordLength++;
			}
			n = n.children.get(w.charAt(level));
		}
	}

	public boolean search(String w) {
		Node n = this.root;
		for (int level = 0, length = w.length(); level < length; ++level) {
			if (n.children.containsKey(w.charAt(level)) == false)
				return false;
			n = n.children.get(w.charAt(level));
		}
		if (n != null)
			return true;
		return false;
	}
}