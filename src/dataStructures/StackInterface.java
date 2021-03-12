package dataStructures;

public interface StackInterface<T> {

	public T top();
	public T pop();
	public void push(T data);
	public boolean isEmpty();
	public int size();
	
}
