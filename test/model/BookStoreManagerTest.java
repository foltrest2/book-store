package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import exceptions.InvalidCharacterException;

public class BookStoreManagerTest {

	private BookStoreManager bs = new BookStoreManager();
	public void setupScenary_1() throws InvalidCharacterException{
		
		bs.addShelve("A", 4);
		bs.addShelve("B", 5);
		bs.addShelve("C", 5);
		
	}

	public void setupScenary_2() throws InvalidCharacterException{
		bs.addShelve("A", 4);
		bs.addShelve("B", 5);
		bs.addShelve("C", 5);
		bs.addBookPerShelve("El dia y la noche1", "Capitulo 1: Erase una vez la luna y el sol...", "Buenisimo", "767", 50000, "C", 5);
		bs.addBookPerShelve("El dia y la noche2", "Capitulo 1: Erase una vez la luna y el sol...", "Buenisimo", "123", 50000, "A", 4);
		bs.addBookPerShelve("El dia y la noche3", "Capitulo 1: Erase una vez la luna y el sol...", "Buenisimo", "456", 50000, "B", 3);
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
		assertEquals("Fail test SEARCHING THIRD STUDENT", "C", bs.binaryShelveSearch("C").getIndicator());
		assertTrue(bs.addBookPerShelve("El dia y la noche", "Capitulo 1: Erase una vez la luna y el sol...", "Buenisimo", "767", 50000, "C", 3));
		assertEquals("Fail test SEARCHING THIRD STUDENT", "El dia y la noche", bs.binaryShelveSearch("C").getSlots().get("767").getTitle());
		assertEquals("Fail test SEARCHING THIRD STUDENT", new Integer(3), bs.binaryShelveSearch("C").getBooksExistence().get("767"));
	}
	
	@Test
	public void testCountingSort() throws InvalidCharacterException{
		setupScenary_2();
		ArrayList<String> noSortedBooks = new ArrayList<>();
		ArrayList<String> sortedBooks = new ArrayList<>();
		noSortedBooks.add("767");
		noSortedBooks.add("123");
		noSortedBooks.add("456");
		sortedBooks = bs.countingSort(noSortedBooks);
		assertEquals("Fail test", "123", sortedBooks.get(0));
		assertEquals("Fail test", "456", sortedBooks.get(1));
		assertEquals("Fail test", "767", sortedBooks.get(2));
	}
	
	@Test

	public void testInsertionSort() throws InvalidCharacterException{
		
		setupScenary_2();
		ArrayList<String> ns = new ArrayList<>();
		ns.add("767");
		ns.add("123");
		ns.add("234");
		ns.add("457");
		ns.add("293");
		ns.add("324");
		ns.add("125");
		ns.add("210");
		ns.add("320");
		ns.add("115");
		ns.add("105");
		ns.add("987");
		bs.insertionSort(ns);
		assertEquals("Fail test", "105", ns.get(0));
		assertEquals("Fail test", "115", ns.get(1));
		assertEquals("Fail test", "123", ns.get(2));
		assertEquals("Fail test", "125", ns.get(3));
		assertEquals("Fail test", "210", ns.get(4));
		assertEquals("Fail test", "234", ns.get(5));
		assertEquals("Fail test", "293", ns.get(6));
		assertEquals("Fail test", "320", ns.get(7));
		assertEquals("Fail test", "324", ns.get(8));
		assertEquals("Fail test", "457", ns.get(9));
		assertEquals("Fail test", "767", ns.get(10));
		assertEquals("Fail test", "987", ns.get(11));
		
		
		 
		
		
		
		
		
		
	}

}
