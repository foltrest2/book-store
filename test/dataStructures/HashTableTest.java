package dataStructures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HashTableTest {

	HashTable<Integer, String> ht;
	
	
	public void setupScenary1() {
		ht = new HashTable<>();
		ht.put(2, "tuma2");
		ht.put(4, "tuma3");
		ht.put(8, "tuma4");
		ht.put(16, "tuma4");
	}
	
	@Test
	public void searchTest1() {
		setupScenary1();
		assertEquals("tuma2", ht.get(2), "Fail test");
		assertEquals("tuma3", ht.get(4), "Fail test");
		assertEquals("tuma4", ht.get(8), "Fail test");
		assertEquals("tuma4", ht.get(16), "Fail test");
	}
}
