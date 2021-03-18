package dataStructures;

public class HashTable <K, V> implements HashTableInterface<K, V> {

	private class Entry<K,V> {
		
		private K key;
		private V value;
		private boolean isDeleted;
		
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
			isDeleted = false;
		}
		public String toString() {
			return "[ " + key + ", " + value + " ]";
		}
	}
	
	private Entry<?,?> [] table;
	private int size;
	
	public HashTable() {
		
	}
	
	@Override
	public void put(K key, V value) {
		
	}

	@Override
	public V search(K key) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return 0;
	}

}
