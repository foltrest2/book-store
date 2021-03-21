package model;
import dataStructures.HashTable;

public class Shelve implements Comparable<Shelve>{

	private String indicator;
	private HashTable<String, Book> slots;
	private HashTable<String, Integer> booksExistence;
	
	public Shelve(String ind, int slots) {
		indicator = ind;
		this.slots = new HashTable<String, Book>(slots);
		this.booksExistence =  new HashTable<String, Integer>(slots); 
	}
	
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

	@Override
	public int compareTo(Shelve arg0) {
		return 0;
	}

	public HashTable<String, Integer> getBooksExistence() {
		return booksExistence;
	}
}
