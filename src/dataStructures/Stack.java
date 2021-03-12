package dataStructures;

public class Stack<T> implements StackInterface<T> {

	@SuppressWarnings("hiding")
	protected class Node<T> {

		private T data;
		private Node<T> under;

		public Node(T d){
			data = d;
		}
		public T getData() {
			return data;
		}
		public Node<T> getUnder(){
			return under;
		}
		public void setUnder(Node<T> under){
			this.under = under;
		}
	}

	private Node<T> top;
	private int size;

	public Stack() {
		top = null;
	}

	@Override
	public T top() {
		return (top == null) ? null : top.getData();
	}

	@Override
	public T pop() {
		if (top == null) {
			return null;
		}else {
			Node<T> next = top.getUnder();
			next = top;
			return next.getData();
		}
	}

	@Override
	public void push(T data) {
		if (top == null) {
			top = new Node<T>(data);
		}
		else {
			Node<T> newNode = new Node<T>(data);
			newNode.setUnder(top);
			newNode = top;
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
