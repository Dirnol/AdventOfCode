package adventofcode.day08;

public class Node {

	public String name;
	public Node left, right;
	
	public Node(String name) {
		this.name = name;
	}
	
	public void setLeftChild(Node left) {
		this.left = left;
	}
	
	public void setRightChild(Node right) {
		this.right = right;
	}
	
	@Override
	public String toString() {
		return name + " = (" + left.name + ", " + right.name + ")";
	}
	
}
