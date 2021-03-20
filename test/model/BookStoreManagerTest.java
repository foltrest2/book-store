package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import exceptions.InvalidCharacterException;

public class BookStoreManagerTest {

	private BookStoreManager bs;

	public void setupScenary_1() throws InvalidCharacterException{
		bs = new BookStoreManager();
		bs.addShelve("A", 4);
		bs.addShelve("B", 5);
		bs.addShelve("C", 5);
		bs.addBookPerShelve("El dia y la noche", "Capitulo 1: Erase una vez la luna y el sol...", "Buenisimo", "767", 50000, "C", 3);
	}

	public void setupScenary_2() throws InvalidCharacterException{
		bs = new BookStoreManager();
		bs.addShelve("A", 4);
		bs.addShelve("B", 5);
		bs.addShelve("C", 5);
		bs.addBookPerShelve("El dia y la noche1", "Capitulo 1: Erase una vez la luna y el sol...", "Buenisimo", "767", 50000, "C", 5);
		bs.addBookPerShelve("El dia y la noche2", "Capitulo 1: Erase una vez la luna y el sol...", "Buenisimo", "123", 50000, "A", 4);
		bs.addBookPerShelve("El dia y la noche3", "Capitulo 1: Erase una vez la luna y el sol...", "Buenisimo", "456", 50000, "B", 3);
	}
	
	public void setupScenary_3() throws InvalidCharacterException{
		bs = new BookStoreManager();
		bs.addClient("1234");
		bs.addClient("1234");
		bs.addClient("1235");
		bs.addClient("1236");
		
	}

	public void setupScenary_4() throws InvalidCharacterException {
		bs = new BookStoreManager();
		bs.addClient("123");
		bs.addClient("456");
		bs.addClient("798");
		bs.addShelve("A", 4);
		bs.addShelve("B", 5);
		bs.addShelve("C", 5);
		bs.addBookPerShelve("El dia y la noche1", "Capitulo 1: Erase una vez la luna y el sol...", "Buenisimo", "767", 50000, "C", 5);
		bs.addBookPerShelve("El dia y la noche2", "Capitulo 1: Erase una vez la luna y el sol...", "Buenisimo", "123", 50000, "A", 4);
		bs.addBookPerShelve("El dia y la noche3", "Capitulo 1: Erase una vez la luna y el sol...", "Buenisimo", "456", 50000, "B", 3);
		bs.getInitialClientsList().get(0).addBookCodeToInitialList("767");
		bs.getInitialClientsList().get(0).addBookCodeToInitialList("123");
		bs.getInitialClientsList().get(0).addBookCodeToInitialList("456");
		bs.getInitialClientsList().get(1).addBookCodeToInitialList("123");
		bs.getInitialClientsList().get(1).addBookCodeToInitialList("456");
		bs.getInitialClientsList().get(1).addBookCodeToInitialList("767");
		bs.getInitialClientsList().get(2).addBookCodeToInitialList("456");
		bs.getInitialClientsList().get(2).addBookCodeToInitialList("767");
		bs.getInitialClientsList().get(2).addBookCodeToInitialList("123");
		bs.booksToBag(bs.getInitialClientsList().get(0));
		bs.booksToBag(bs.getInitialClientsList().get(1));
		bs.booksToBag(bs.getInitialClientsList().get(2));
	}
	
	public void setupScenary_5() throws InvalidCharacterException {
		bs = new BookStoreManager();
		bs.addClient("123");
		bs.addShelve("A", 3);
		bs.addShelve("B", 2);
		bs.addShelve("C", 1);
		bs.addBookPerShelve("Holi", "Nose", "Ajá", "6545", 15500.0, "C", 5);
		bs.addBookPerShelve("Holi", "Nose", "Ajá", "9485", 15500.0, "B", 5);
		bs.addBookPerShelve("Holi", "Nose", "Ajá", "1654", 15500.0, "A", 5);
		bs.getInitialClientsList().get(0).addBookCodeToInitialList("6545");
		bs.getInitialClientsList().get(0).addBookCodeToInitialList("9485");
		bs.getInitialClientsList().get(0).addBookCodeToInitialList("1654");
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
	
	@Test
	public void booksToBagTest() throws InvalidCharacterException {
		setupScenary_4();
		assertEquals("Test failed", "456", bs.getInitialClientsList().get(0).getBooks().top().getISBNCode());
		assertEquals("Test failed", "767", bs.getInitialClientsList().get(1).getBooks().top().getISBNCode());
		assertEquals("Test failed", "123", bs.getInitialClientsList().get(2).getBooks().top().getISBNCode());
	}
	
	@Test
	public void heapSortTest() throws InvalidCharacterException {
		setupScenary_5();
		ArrayList<String> sorted = bs.heapSort(bs.getInitialClientsList().get(0).getInitialBooksList());
		assertEquals("Test failed", "1654", sorted.get(0));
		assertEquals("Test failed", "9485", sorted.get(1));
		assertEquals("Test failed", "6545", sorted.get(2));
	}
}
