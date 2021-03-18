package dataStructures;

import exceptions.InvalidCapacityException;

public class HashTable <K, V> implements HashTableInterface<K, V> {

	private static class Entry<K,V> {

		private K key;
		private V value;
		private Entry<?,?> next;
		private Entry<?,?> prev;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		public String toString() {
			return "[ " + key + ", " + value + " ]";
		}
	}

	private Entry<?,?> [] elements;
	private int size;
	public static final int DEFAULT_CAPACITY = 10;

	public HashTable() {
		elements = new Entry<?,?>[DEFAULT_CAPACITY];
		size = DEFAULT_CAPACITY;
	}

	public HashTable(int initialCapacity) throws InvalidCapacityException  {
		if (initialCapacity < 0) {
			throw new InvalidCapacityException();
		}
		else {
			elements = new Entry<?,?>[initialCapacity];
			size = initialCapacity;
		}
	}
	
	public int hashFunction(K key) {
		return key.hashCode() % size;
	}

	@Override
	public void put(K key, V value) {
		Entry<K,V> entry = new Entry<>(key, value);
		int i = hashFunction(key);
		if (elements[i] == null) {
			elements[i] = entry;
		}
		else {
			elements[i].next = entry;
			entry.prev = elements[i];
		}
	}

	@Override
	public V delete(K key) {
		
		Entry<K,V> toFind = search(key);
		V vToDelete = null;
		
		if(toFind != null && toFind.prev == null) {
			
			
			vToDelete = toFind.value;
			toFind.value = null;
			toFind.key = null;
			
			
		}else if(toFind.prev != null) {
			
			
			vToDelete = toFind.value;
			toFind.
			
		}
			
		
		
		
		}

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return size;
	}

}
