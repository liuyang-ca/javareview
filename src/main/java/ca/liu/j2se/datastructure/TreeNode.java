package ca.liu.j2se.datastructure;

public class TreeNode {
	private TreeNode left;
	private int value;
	private TreeNode right;
	
	public TreeNode(int value) {
		this.setValue(value);
	}
	
	public void addNode(TreeNode node) {
		if(node.getValue() <= value) {
			if(left == null) {
				left = node;
			} else {
				left.addNode(node);
			}
		} else {
			if(right == null) {
				right = node;
			} else {
				right.addNode(node);
			}
		}
	}

	public TreeNode getLeft() {
		return left;
	}

	public TreeNode getRight() {
		return right;
	}

	public int getValue() {
		return value;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}

