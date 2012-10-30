package ca.liu.j2se.datastructure;

import org.junit.Test;

import ca.liu.j2se.datastructure.ListNode;

public class ListTest {

	private boolean isCycle(ListNode<Integer> root) {		
		if(root != null && root.getNext() != null && root.getNext().getNext() != null) {
			ListNode<Integer> slowNode = root.getNext();
			ListNode<Integer> fastNode = root.getNext().getNext();
			
			while(slowNode != null && fastNode != null) {
				if(slowNode == fastNode || slowNode == fastNode.getNext()) {
					return true;
				}
				slowNode = slowNode.getNext();
				fastNode = fastNode.getNext() == null ? null : fastNode.getNext().getNext();
			}
		}
		return false;
	}
	
	@Test
	public void isCycleTest() {
		ListNode<Integer> root = buildIntList();
		System.out.println(isCycle(root));
	}
	
	/**
	 * Given a singel linked list, find mth-to-list element
	 * @throws Exception 
	 */
	private int lastMElementOfList(ListNode<Integer> root, int m) throws Exception {
		ListNode<Integer> mNode = root;
		for(int i=0; i<m; i++) {
			if(mNode.getNext() == null) {
				throw new Exception("M is bigger than list lenght!");
			} else {
				mNode = mNode.getNext();
			}
		}
		
		while(mNode.getNext() != null) {
			mNode = mNode.getNext();
			root = root.getNext();
		}
		
		return root.getValue();
	}
	
	@Test
	public void lastMElementOfListTest() throws Exception {
		ListNode<Integer> root = buildIntList();
		int value = lastMElementOfList(root, 5);
		System.out.println(value);
	}
	
	private ListNode<Integer> buildIntList() {
		ListNode<Integer> root = new ListNode<Integer>(4);
		root.addNode(10);
		ListNode<Integer> node = new ListNode<Integer>(2);
		root.addNode(node);
		root.addNode(3);
		root.addNode(5);
		root.addNode(1);
		//root.addNode(node);
		
		return root;
	}
	
	@Test
	public void listTest() {
		ListNode<Integer> root = buildIntList();
		while(root != null) {
			System.out.println(root.getValue());
			root = root.getNext();
		}
	}
}
