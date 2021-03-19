package model;

import dataStructures.HashTable;

public class Shelve {
	
	private String indicator;
	private HashTable<String, Book> slots;
	
	public Shelve(String ind, int slots) {
		indicator = ind;
		this.slots = new HashTable<String, Book>(slots);
	}
	public Shelve(int slots) {
		this.slots = new HashTable<String, Book>(slots);
	}
	
	public void addBook(String ISBN, Book book) {
		slots.put(ISBN, book);
	}
	
	public HashTable<String, Book> getSlots() {
		return slots;
	}

	public void setSlots(HashTable<String, Book> slots) {
		this.slots = slots;
	}

	public String getIndicator() {
		return indicator;
	}

	public void setIndicator(String ind) {
		indicator = ind;
	}

}
