package dataStructures;

public interface HashTableInterface<K, V> {

	public void put(K key, V value);
	public V delete(K key);
	public V get(K key);
	public int size();
	
}