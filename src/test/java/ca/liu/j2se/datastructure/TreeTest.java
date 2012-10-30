package ca.liu.j2se.datastructure;

import java.util.LinkedList;
import org.junit.Test;

import ca.liu.j2se.datastructure.TreeNode;


public class TreeTest {
	private void inOrder(TreeNode node) {
		if(node != null) {
			inOrder(node.getLeft());
			System.out.println(node.getValue());
			inOrder(node.getRight());
		}
	}
	
	private void preOrder(TreeNode node) {
		if(node != null) {
			System.out.println(node.getValue());
			preOrder(node.getLeft());
			preOrder(node.getRight());
		}
	}
	
	private void postOrder(TreeNode node) {
		if(node != null) {
			postOrder(node.getLeft());
			postOrder(node.getRight());
			System.out.println(node.getValue());
		}
	}
	
	private void breadthFirst(TreeNode node) {
		LinkedList<TreeNode> list = new LinkedList<TreeNode>();
		
		list.add(node);
		
		while(list.isEmpty() == false) {
			TreeNode n = list.removeFirst();
			System.out.println(n.getValue());
			if(n.getLeft() != null) {
				list.add(n.getLeft());
			}
			
			if(n.getRight() != null) {
				list.add(n.getRight());
			}
		}
	}
	
	/**
	 * Print all the path that sum up to goal
	 */
	private void printSumPath(TreeNode node, int sum, String path, int goal) {
		if(node == null) {
			return;
		}
		
		path += "-->" + node.getValue();
		sum = sum + node.getValue();
		
		if(sum == goal) {
			System.out.println(path.toString());
		}
		
		printSumPath(node.getLeft(), sum, path, goal);
		printSumPath(node.getRight(), sum, path, goal);
	}

	@Test
	public void printSumPathTest() {
		TreeNode root = buildTree();
		printSumPath(root, 0, "", 9);
	}
	
	private int lowestCommonAncestor(TreeNode root, int num1, int num2) throws Exception {
		int small = num1;
		int large = num2;
		
		if(num1 > num2) {
			small = num2;
			large = num1;
		}
		
		while(root != null) {
			if(root.getValue() <= large && root.getValue() > small) {
				return root.getValue();
			} else if(root.getValue() > large) {
				root = root.getLeft();
			} else {
				root = root.getRight();
			}
		}
		
		throw new Exception("Error!");
	}
	
	@Test
	public void  lowestCommonAncestorTest() throws Exception {
		TreeNode root = buildTree();
		System.out.println(lowestCommonAncestor(root, 3, 12));
	}
	
	private TreeNode buildTree() {
		TreeNode root = new TreeNode(4);
		root.addNode(new TreeNode(2));
		root.addNode(new TreeNode(3));
		root.addNode(new TreeNode(8));
		root.addNode(new TreeNode(10));
		root.addNode(new TreeNode(1));
		
		root.addNode(new TreeNode(5));
		root.addNode(new TreeNode(12));
		
		return root;
	}
	
	@Test
	public void testTraversal() {
		TreeNode root = buildTree();
		System.out.println("-------In Order----------");
		inOrder(root);
		System.out.println("-------Pre Order----------");
		preOrder(root);
		System.out.println("-------Post Order----------");
		postOrder(root);
		System.out.println("-------Breadth First----------");
		breadthFirst(root);
	}
}
