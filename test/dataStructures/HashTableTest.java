package dataStructures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HashTableTest {

	HashTable<Integer, String> ht;
	HashTable<Integer, String> ht2 = new HashTable<>(11);
	
	public void setupScenary1() {
		ht = new HashTable<>();
		ht.put(2, "tuma2");
		ht.put(4, "tuma4");
		ht.put(8, "tuma8");
		ht.put(16, "tuma16");
		ht.put(18, "tuma18");
	}
	
	public void setupScenary2() {
		ht = new HashTable<>();
		ht.put(2, "tuma2");
		ht.put(4, "tuma4");
		ht.put(6, "tuma6");
		ht.put(8, "tuma8");
		ht.put(10, "tuma10");
	}
	public void setupScenary3() {
		ht.put(2, "tuma2");
		ht.put(4, "tuma4");
		ht.put(6, "tuma6");
		ht.put(8, "tuma8");
		ht.put(10, "tuma10");
	}
	public void setupScenary4() {
		ht = new HashTable<>();
		ht.put(123, "tuma1");
		ht.put(345, "tuma2");
		ht.put(678, "tuma3");
		ht.put(91011, "tuma4");
		ht.put(111213, "tuma5");
	}
	
	@Test
	public void searchTest1() {
		setupScenary1();
		assertEquals("tuma2", ht.get(2), "Fail test");
		assertEquals("tuma4", ht.get(4), "Fail test");
		assertEquals("tuma8", ht.get(8), "Fail test");
		assertEquals("tuma16", ht.get(16), "Fail test");
		assertEquals("tuma18", ht.get(18), "Fail test");
	}
	
	@Test
	public void deleteTest1() {
		setupScenary1();
		assertEquals("tuma2", ht.get(2), "Fail test");
		assertEquals("tuma4", ht.get(4), "Fail test");
		assertEquals("tuma8", ht.get(8), "Fail test");
		assertEquals("tuma16", ht.delete(16), "Fail test");
		assertNull(ht.get(16));
		assertEquals("tuma2", ht.delete(2), "Fail test");
		assertNull(ht.get(2));
		assertEquals("tuma4", ht.get(4), "Fail test");
		assertEquals("tuma8", ht.get(8), "Fail test");
		assertEquals("tuma8", ht.delete(8), "Fail test");
		assertNull(ht.get(8));
		assertEquals("tuma4", ht.delete(4), "Fail test");
		assertNull(ht.get(4));
		assertEquals("tuma18", ht.delete(18), "Fail test");
		assertNull(ht.get(18));
	}
	
	@Test
	public void deleteTest2() {
		setupScenary2();
		assertEquals("tuma2", ht.get(2), "Fail test");
		assertEquals("tuma4", ht.get(4), "Fail test");
		assertEquals("tuma6", ht.get(6), "Fail test");
		assertEquals("tuma8", ht.get(8), "Fail test");
		assertEquals("tuma10", ht.get(10), "Fail test");
		ht.put(12, "tuma12");
		assertEquals("tuma12", ht.get(12), "Fail test");
		assertEquals("tuma10", ht.delete(10), "Fail test");
		assertNull(ht.get(10));
		assertEquals("tuma8", ht.delete(8), "Fail test");
		assertNull(ht.get(8));
		assertEquals("tuma12", ht.delete(12), "Fail test");
		assertNull(ht.get(12));
		assertEquals("tuma6", ht.delete(6), "Fail test");
		assertNull(ht.get(6));
		assertEquals("tuma4", ht.delete(4), "Fail test");
		assertNull(ht.get(4));
		assertEquals("tuma2", ht.delete(2), "Fail test");
		assertNull(ht.get(2));
		ht.put(14, "tuma14");
		assertEquals("tuma14", ht.get(14), "Fail test");
		ht.put(16, "tuma16");
		assertEquals("tuma16", ht.get(16), "Fail test");
		assertEquals("tuma14", ht.delete(14), "Fail test");
		assertNull(ht.get(14));
		assertEquals("tuma16", ht.delete(16), "Fail test");
		assertNull(ht.get(16));
	}
	
	@Test
	public void hashTest1() {
		setupScenary4();
		assertEquals("tuma1", ht.get(123), "Fail test");
		assertEquals("tuma2", ht.get(345), "Fail test");
		assertEquals("tuma3", ht.get(678), "Fail test");
		assertEquals("tuma4", ht.get(91011), "Fail test");
		assertEquals("tuma5", ht.get(111213), "Fail test");
	}
	
	@Test
	public void containsTest1() {
		setupScenary4();
		assertEquals("tuma1", ht.get(123), "Fail test");
		assertEquals("tuma2", ht.get(345), "Fail test");
		assertEquals("tuma3", ht.get(678), "Fail test");
		assertEquals("tuma4", ht.get(91011), "Fail test");
		assertEquals("tuma5", ht.get(111213), "Fail test");
		assertTrue(ht.contains(123));
		ht.put(123, "tumaxd");
	}
	
	@Test
	public void deleteReturnEntryTest1() {
		setupScenary4();
		assertEquals("tuma1", ht.get(123), "Fail test");
		assertEquals("tuma2", ht.get(345), "Fail test");
		assertEquals("tuma3", ht.get(678), "Fail test");
		assertEquals("tuma4", ht.get(91011), "Fail test");
		assertEquals("tuma5", ht.get(111213), "Fail test");
		assertTrue(ht.contains(123));
		assertEquals("tuma5", ht.deleteReturnsEntry(123), "Fail test");
		assertTrue(!ht.contains(123));
		
	}
	
}
