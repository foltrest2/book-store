package model;
import dataStructures.HashTable;

public class Shelve {

	private String indicator;
	private HashTable<String, Book> slots;
	private HashTable<String, Integer> booksExistence;
	
	/**
	 * Shelve constructor
	 * @param ind is his indicator
	 * @param slots is how many slots the shelve has
	 */
	public Shelve(String ind, int slots) {
		indicator = ind;
		this.slots = new HashTable<String, Book>(slots);
		this.booksExistence =  new HashTable<String, Integer>(slots);  
	}
	
	/**
	 * This method adds one book to the hash table of existence of books and
	 * the slots of the shelve itself
	 * @param ISBN
	 * @param book
	 * @param booksQuantity
	 */
	public void addBook(String ISBN, Book book, int booksQuantity) {
		slots.put(ISBN, book);
		booksExistence.put(ISBN, booksQuantity);
	}
	
	public HashTable<String, Book> getSlots() {
		return slots;
	}

	public String getIndicator() {
		return indicator;
	}
	
	public void setIndicator(String ind) {
		indicator = ind;
	}

	public HashTable<String, Integer> getBooksExistence() {
		return booksExistence;
	}
}
