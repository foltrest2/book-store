package dataStructures;

import exceptions.EmptyQueueException;

public class Queue<T> implements QueueInterface<T>, Cloneable {

	@SuppressWarnings("hiding")
	protected class Node<T> {

		private T data;
		private Node<T> prev;

		/**
		 * Queue node constructor
		 * @param d
		 */
		public Node(T d) {
			data = d;
		}
	}

	private Node<T> front;
	private Node<T> back;
	private int size;

	/**
	 * Queue constructor when it's empty
	 */
	public Queue() {
		size = 0;
	}

	/**
	 * Queue constructor with an array
	 * @param list an array
	 */
	@SafeVarargs
	public Queue(T... list) {
		for (T element : list) {
			this.enqueue(element);
		}
		size = list.length;
	}

	/**
	 * This method enqueue a node to the queue
	 */
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

	/**
	 * This method dequeue a node
	 */
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

	/**
	 * This method clones a queue
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/**
	 * This method returns the information of queue's front
	 */
	@Override
	public T peek() {
		return front.data;
	}

	/**
	 * This method verifies if the queue is empty
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * This method returns the size of the queue
	 */
	@Override
	public int size() {
		return size;
	}
}
