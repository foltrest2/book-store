package dataStructures;

import java.util.EmptyStackException;

public class Stack<T> implements StackInterface<T> {

	@SuppressWarnings("hiding")
	protected class Node<T> {

		private T data;
		private Node<T> under;

		/**
		 * Stack node constructor 
		 * @param d node's data
		 */
		public Node(T d){
			data = d;
		}
	}

	private Node<T> top;
	private int size;

	/**
	 * Stack constructor when it's empty
	 */
	public Stack() {
		top = null;
		size = 0;
	}
	
	/**
	 * Stack constructor with an array
	 * @param list an array
	 */
	@SafeVarargs
	public Stack(T... list) {
		for (T element : list) {
			this.push(element);
		}
		size = list.length;
	}

	/**
	 * This method returns the information of stack's top
	 */
	@Override
	public T top() {
		return (top == null) ? null : top.data;
	}
	/**
	 * This method returns and deletes the node on top
	 */
	@Override
	public T pop() {
		if (top == null) {
			throw new EmptyStackException();
		}else {
			Node<T> deleted = top;
			Node<T> next = top.under;
			top = next;
			size--;
			return deleted.data;
		}
	}
	/**
	 * This method put a node on top 
	 */
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

	/**
	 * This method verifies if the stack is empty
	 */
	@Override
	public boolean isEmpty() {
		return top == null;
	}

	/**
	 * This method returns the size of the stack
	 */
	@Override
	public int size() {
		return size;
	}

}
