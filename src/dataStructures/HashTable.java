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
	public static final int DEFAULT_CAPACITY = 2;

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
			Entry<?,?> current = elements[i];
			while (current.next != null) {
				current = current.next;		
			}
			current.next = entry;
			entry.prev = current;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public V delete(K key) {
		int hashIndex = hashFunction(key);
		Entry<?, ?> current = elements[hashIndex];
		V vFound = null;
		boolean deleted = false;
		if(current != null) {
			Entry<?, ?> aux = current;
			Entry<?, ?> prev = null;
			while(aux != null && !deleted) {
				if(aux.key.equals(key)) {
					if(aux.prev == null && aux.next == null) {
						vFound = (V) aux.value;
						elements[hashIndex] = null;
						deleted = true;
					} else if (aux.prev == null) {
						vFound = (V) aux.value;
						aux = current.next;
						current = aux;
						current.prev = null;
						elements[hashIndex] = current;
						deleted = true;
					} else {
						vFound = (V) aux.value;
						prev.next = aux.next;
						aux.next = null;
						aux = prev.next;
						deleted = true;
					}
				} else {
					prev = aux;
					aux = aux.next;
				}
			}
		}
		return vFound;
	}

	@Override
	public int size() {
		return this.size;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V get(K key) {
		Entry<?, ?> current = elements[hashFunction(key)];
		V value = null;
		boolean found = false;
		while (current != null && !found) {
			if (current.key.equals(key)) {
				found = true;
				value = (V) current.value;
			}
			current = current.next;
		}
		return value;
	}
}
