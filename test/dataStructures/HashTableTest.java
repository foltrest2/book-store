package dataStructures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HashTableTest {

	HashTable<Integer, String> ht;
	
	public void setupScenary1() {
		ht = new HashTable<>();
		ht.put(2, "value2");
		ht.put(4, "value4");
		ht.put(8, "value8");
		ht.put(16, "value16");
		ht.put(18, "value18");
	}
	
	public void setupScenary2() {
		ht = new HashTable<>();
		ht.put(2, "value2");
		ht.put(4, "value4");
		ht.put(6, "value6");
		ht.put(8, "value8");
		ht.put(10, "value10");
	}
	public void setupScenary3() {
		ht = new HashTable<>();
		ht.put(123, "value1");
		ht.put(345, "value2");
		ht.put(678, "value3");
		ht.put(91011, "value4");
		ht.put(111213, "value5");
	}
	
	@Test
	public void searchTest1() {
		setupScenary1();
		assertEquals("value2", ht.get(2), "Fail test");
		assertEquals("value4", ht.get(4), "Fail test");
		assertEquals("value8", ht.get(8), "Fail test");
		assertEquals("value16", ht.get(16), "Fail test");
		assertEquals("value18", ht.get(18), "Fail test");
	}
	
	@Test
	public void deleteTest1() {
		setupScenary1();
		assertEquals("value2", ht.get(2), "Fail test");
		assertEquals("value4", ht.get(4), "Fail test");
		assertEquals("value8", ht.get(8), "Fail test");
		assertEquals("value16", ht.delete(16), "Fail test");
		assertNull(ht.get(16));
		assertEquals("value2", ht.delete(2), "Fail test");
		assertNull(ht.get(2));
		assertEquals("value4", ht.get(4), "Fail test");
		assertEquals("value8", ht.get(8), "Fail test");
		assertEquals("value8", ht.delete(8), "Fail test");
		assertNull(ht.get(8));
		assertEquals("value4", ht.delete(4), "Fail test");
		assertNull(ht.get(4));
		assertEquals("value18", ht.delete(18), "Fail test");
		assertNull(ht.get(18));
	}
	
	@Test
	public void deleteTest2() {
		setupScenary2();
		assertEquals("value2", ht.get(2), "Fail test");
		assertEquals("value4", ht.get(4), "Fail test");
		assertEquals("value6", ht.get(6), "Fail test");
		assertEquals("value8", ht.get(8), "Fail test");
		assertEquals("value10", ht.get(10), "Fail test");
		ht.put(12, "value12");
		assertEquals("value12", ht.get(12), "Fail test");
		assertEquals("value10", ht.delete(10), "Fail test");
		assertNull(ht.get(10));
		assertEquals("value8", ht.delete(8), "Fail test");
		assertNull(ht.get(8));
		assertEquals("value12", ht.delete(12), "Fail test");
		assertNull(ht.get(12));
		assertEquals("value6", ht.delete(6), "Fail test");
		assertNull(ht.get(6));
		assertEquals("value4", ht.delete(4), "Fail test");
		assertNull(ht.get(4));
		assertEquals("value2", ht.delete(2), "Fail test");
		assertNull(ht.get(2));
		ht.put(14, "value14");
		assertEquals("value14", ht.get(14), "Fail test");
		ht.put(16, "value16");
		assertEquals("value16", ht.get(16), "Fail test");
		assertEquals("value14", ht.delete(14), "Fail test");
		assertNull(ht.get(14));
		assertEquals("value16", ht.delete(16), "Fail test");
		assertNull(ht.get(16));
	}
	
	@Test
	public void containsTest1() {
		setupScenary3();
		assertTrue(ht.contains(123));
		ht.put(123, "valuexd");
		assertEquals("value1", ht.get(123), "Fail test");
	}
	
}
