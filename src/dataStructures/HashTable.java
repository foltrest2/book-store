package dataStructures;

import exceptions.InvalidCapacityException;

public class HashTable <K, V> implements HashTableInterface<K, V> {

	private static class Entry<K,V> {

		private K key;
		private V value;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		public String toString() {
			return "[ " + key + ", " + value + " ]";
		}
	}

	private Entry<?,?> [] table;
	private int size;
	public static final int DEFAULT_CAPACITY = 10;

	public HashTable() {
		table = new Entry<?,?>[DEFAULT_CAPACITY];
		size = DEFAULT_CAPACITY;
	}

	public HashTable(int initialCapacity) throws InvalidCapacityException {
		if (initialCapacity < 0) {
			throw new InvalidCapacityException();
		}
		else {
			table = new Entry<?,?>[initialCapacity];
			size = initialCapacity;
		}
	}

	@Override
	public void put(K key, V value) {

	}

	@Override
	public V delete(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
