package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import exceptions.InvalidCharacterException;

public class BookStoreManagerTest {

	private BookStoreManager bs = new BookStoreManager();
	
	
	public void setupScenary_1(){
		bs.addShelveQuantity("A", 4);
		bs.addShelveQuantity("B", 5);
		bs.addShelveQuantity("C", 5);
	}
	
	@Test
	public void testAddingAndSearchingShelve() throws InvalidCharacterException{
		setupScenary_1();
		assertEquals("Fail test SEARCHING THIRD STUDENT", "A", bs.binarySearch("A").getIndicator());
		assertNull(bs.binarySearch("D"));
	}
}
