package dataStructures;

public class HashTable <K, V> implements HashTableInterface<K, V> {

	private static class Entry<K,V> {

		private K key;
		private V value;
		private Entry<?,?> next;
		private Entry<?,?> prev;

		/**
		 * HashTable entry constructor
		 * @param key
		 * @param value
		 */
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	private Entry<?,?> [] elements;
	private int size;
	public static final int DEFAULT_CAPACITY = 11;

	/**
	 * HashTable constructor without given initial capacity, a default capacity is assign
	 */
	public HashTable() {
		elements = new Entry<?,?>[DEFAULT_CAPACITY];
		size = DEFAULT_CAPACITY;
	}

	/**
	 * HashTable constructor with initial capacity
	 * @param initialCapacity
	 */
	public HashTable(int initialCapacity)  {
		if (initialCapacity < 0) {
			throw new IllegalArgumentException("xd bro?");
		}
		else {
			elements = new Entry<?,?>[initialCapacity];
			size = initialCapacity;
		}
	}

	/**
	 * This method obtains the location of an entry
	 * @param key is the identifier of an entry
	 * @return a int with the location in the table
	 */
	public int hashFunction(K key) {
		return key.hashCode() % this.size;
	}

	/**
	 * This method put an entry into the table
	 */
	@Override
	public void put(K key, V value) {
		if(!contains(key)) {
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
	}

	/**
	 * This method deletes an entry and returns it's value
	 */
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

	/**
	 * This method returns the size of the hashTable
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * This method verifies if a key is inside the table
	 * @param key a key
	 * @return is the table contains the key or not
	 */
	public boolean contains(K key) {
		Entry<?, ?> current = elements[hashFunction(key)];
		boolean found = false;
		while (current != null && !found) {
			if (current.key.equals(key)) {
				found = true;
			}
			current = current.next;
		}
		return found;
	}

	/**
	 * This method gets the value associated to one key
	 */
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
