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
		bs.addBookPerShelve("El dia y la noche", "Capitulo 1: Erase una vez la luna y el sol...", "Buenisimo", "767", 50000, "C", 3);
	}

	public void setupScenary_2() throws InvalidCharacterException{
		bs.addShelve("A", 4);
		bs.addShelve("B", 5);
		bs.addShelve("C", 5);
		bs.addBookPerShelve("El dia y la noche1", "Capitulo 1: Erase una vez la luna y el sol...", "Buenisimo", "767", 50000, "C", 5);
		bs.addBookPerShelve("El dia y la noche2", "Capitulo 1: Erase una vez la luna y el sol...", "Buenisimo", "123", 50000, "A", 4);
		bs.addBookPerShelve("El dia y la noche3", "Capitulo 1: Erase una vez la luna y el sol...", "Buenisimo", "456", 50000, "B", 3);
	}
	
	public void setupScenary_3() throws InvalidCharacterException{
		bs.addClient("1234");
		bs.addClient("1234");
		bs.addClient("1235");
		bs.addClient("1236");

	}

	public void setupScenary_4() {
		bs.addClient("123");
		bs.addClient("456");
		bs.addClient("798");
		
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
	public void testAddingClient() throws InvalidCharacterException{
		setupScenary_3();
		assertEquals("Fail test", "1234", bs.getInitialClientsList().get(0).getId());
		assertEquals("Fail test", 3, bs.getInitialClientsList().size());
		assertEquals("Fail test", 1, bs.getInitialClientsList().get(0).getPriorityTime());
		assertEquals("Fail test", 2, bs.getInitialClientsList().get(1).getPriorityTime());
		assertEquals("Fail test", 3, bs.getInitialClientsList().get(2).getPriorityTime());
	}
}
