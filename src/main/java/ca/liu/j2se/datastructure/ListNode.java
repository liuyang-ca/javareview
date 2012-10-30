package ca.liu.j2se.datastructure;

public class ListNode<T> {
	private T value;
	private ListNode<T> next;
	
	public ListNode(T value) {
		this.setValue(value);
	}
	
	public void addNode(T value) {
		if(this.next == null) {
			this.next = new ListNode<T>(value);
		} else {
			this.next.addNode(value);
		}
	}
	
	public void addNode(ListNode<T> node) {
		if(this.next == null) {
			this.next = node;
		} else {
			this.next.addNode(node);
		}
	}
	
	public ListNode<T> getNext() {
		return next;
	}

	public T getValue() {
		return value;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
}
