package dataStructures;

public class Stack<T> implements StackInterface<T> {

	@SuppressWarnings("hiding")
	protected class Node<T> {

		private T data;
		private Node<T> under;

		public Node(T d){
			data = d;
		}
	}

	private Node<T> top;
	private int size;

	public Stack() {
		top = null;
	}
	
	@SafeVarargs
	public Stack(T... list) {
		for (T element : list) {
			this.push(element);
		}
	}

	@Override
	public T top() {
		return (top == null) ? null : top.data;
	}

	@Override
	public T pop() {
		if (top == null) {
			return null;
		}else {
			Node<T> deleted = top;
			Node<T> next = top.under;
			top = next;
			size--;
			return deleted.data;
		}
	}

	@Override
	public void push(T data) {
		if (top == null) {
			top = new Node<T>(data);
			size++;
		}
		else {
			Node<T> newNode = new Node<T>(data);
			newNode.under = top;
			top = newNode;
			size++;
		}
	}

	@Override
	public boolean isEmpty() {
		return top == null;
	}

	@Override
	public int size() {
		return size;
	}

}
