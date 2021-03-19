package dataStructures;

public interface GenericLinkedListMethods <T>{
	
	public boolean addElement(T t);
	public boolean searchElement(T t);
	public T getItem(int n);
	public boolean deleteElement(T t);
	public boolean isEmpty();
	public int size();
	
}
