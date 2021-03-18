package dataStructures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HashTableTest {

	HashTable<Integer, String> ht;
	
	
	public void setupScenary1() {
		ht = new HashTable<>();
		ht.put(2, "tuma2");
		ht.put(3, "tuma3");
		ht.put(4, "tuma3");
	}
	
	@Test
	public void searchTest1() {
		setupScenary1();
		assertEquals("tuma3", ht.search(4), "Fail test");
	}
}
