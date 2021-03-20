package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import exceptions.InvalidCharacterException;

public class BookStoreManagerTest {

	private BookStoreManager bs = new BookStoreManager();
	
	
	public void setupScenary_1() throws InvalidCharacterException{
		bs.addShelve("A", 4);
		bs.addShelve("B", 5);
		bs.addShelve("C", 5);
		bs.addBookPerShelve("El dia y la noche", "Capitulo 1: Erase una vez la luna y el sol...", "Buenisimo", "767", 50000, "C", 3);
	}
	
	@Test
	public void testAddingAndSearchingShelve() throws InvalidCharacterException{
		setupScenary_1();
		assertEquals("Fail test SEARCHING THIRD STUDENT", "A", bs.binaryShelveSearch("A").getIndicator());
		assertNull(bs.binaryShelveSearch("D"));
	}
	
	@Test
	public void testAddingBooksToShelve() throws InvalidCharacterException{
		setupScenary_1();
		assertEquals("Fail test SEARCHING THIRD STUDENT", "C", bs.binaryShelveSearch("C").getBooksExistence());
		assertNull(bs.binaryShelveSearch("D"));
	}
}
