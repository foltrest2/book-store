package model;

import dataStructures.HashTable;

public class Shelve implements Comparable<Shelve>{
	
	private String indicator;
	private HashTable<String, Book> slots;
	
	public Shelve(String ind, int slots) {
		indicator = ind;
		this.slots = new HashTable<>(slots);
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

	@Override
	public int compareTo(Shelve arg0) {
		return 0;
	}

}
