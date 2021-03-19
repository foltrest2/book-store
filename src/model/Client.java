package model;
import java.util.ArrayList;
import java.util.List;

import dataStructures.*;

public class Client {

	private String id;
	private int priorityTime;
	private double pricePaid;
	private Stack<Book> books;
	private List<String> initialBooksList;

	public Client(String id, int priorityTime, double pricePaid, Stack<Book> books) {
		initialBooksList = new ArrayList<>();
		this.id = id;
		this.priorityTime = priorityTime;
		this.pricePaid = pricePaid;
		this.books = books;
	}

	public Client(String id, int priorityTime, double pricePaid) {
		initialBooksList = new ArrayList<>();
		this.id = id;
		this.priorityTime = priorityTime;
		this.pricePaid = pricePaid;
	}

	public Client(String id, int priorityTime) {
		initialBooksList = new ArrayList<>();
		this.id = id;
		this.priorityTime = priorityTime;
		this.pricePaid = 0;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPriorityTime() {
		return priorityTime;
	}

	public void setPriorityTime(int priorityTime) {
		this.priorityTime = priorityTime;
	}

	public Stack<Book> getBooks() {
		return books;
	}

	public void setBooks(Stack<Book> books) {
		this.books = books;
	}

	public double getPricePaid() {
		return pricePaid;
	}

	public void setPricePaid(double pricePaid) {
		this.pricePaid = pricePaid;
	}

	public List<String> getInitialBooksList() {
		return initialBooksList;
	}

	public void addInitialBooksList(String ISBNcode) {
		initialBooksList.add(ISBNcode);
	}

}
