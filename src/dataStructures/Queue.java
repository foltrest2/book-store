package dataStructures;

import exceptions.EmptyQueueException;

public class Queue<T> implements QueueInterface<T>, Cloneable {

	@SuppressWarnings("hiding")
	protected class Node<T> {
		
		private T data;
		private Node<T> prev;
		
		public Node(T d) {
			data = d;
		}
	}
	
	private Node<T> front;
	private Node<T> back;
	private int size;
	
	public Queue() {}
	
	@SafeVarargs
	public Queue(T... list) {
		for (T element : list) {
			this.enqueue(element);
		}
	}
	
	@Override
	public void enqueue(T data) {
		Node<T> newNode = new Node<T>(data);
		if (back == null) {
			front = newNode;
			back = newNode;
		}
		else {
			back.prev = newNode;
			back = newNode;
		}
		size++;
	}

	@Override
	public T dequeue() throws EmptyQueueException {
		if (front == null) {
			throw new EmptyQueueException();
		}
		else {
			Node<T> dequeued = front;
			front = front.prev;
			size--;
			return dequeued.data;
		}
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
	    return super.clone();
	}

	@Override
	public T peek() {
		return front.data;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

}
