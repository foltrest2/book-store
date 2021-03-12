package dataStructures;

public interface QueueInterface<T> {

	public void enqueue(T data);
	public T dequeue();
	public T peek();
	public boolean isEmpty();
	public int size();
	
}
