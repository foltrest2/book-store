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

	private Entry<?,?> [] elements;
	private int size;
	public static final int DEFAULT_CAPACITY = 10;

	public HashTable() {
		elements = new Entry<?,?>[DEFAULT_CAPACITY];
		size = DEFAULT_CAPACITY;
	}

	public HashTable(int initialCapacity) throws InvalidCapacityException {
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

	}

	@Override
	public V delete(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V get(K key) {
		V returnValue = null;
		if(key != null) {
			Entry<K,V> localized = searchEntry(key);
			if(localized != null) {
				returnValue = localized.value;
			}
		} else {
			return returnValue;
		}
		return returnValue;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
