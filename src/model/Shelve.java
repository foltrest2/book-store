package model;

import dataStructures.HashTable;

public class Shelve {
	
	private String indicator;
	private HashTable<String, Book> books;
	
	public Shelve(String ind) {
		indicator = ind;
	}
	
	public String getIndicator() {
		return indicator;
	}

	public void setIndicator(String ind) {
		indicator = ind;
	}

}
