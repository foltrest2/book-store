package dataStructures;

import exceptions.EmptyQueueException;

public interface QueueInterface<T> {

	public void enqueue(T data);
	public T dequeue() throws EmptyQueueException;
	public T peek();
	public boolean isEmpty();
	public int size();
	
}
