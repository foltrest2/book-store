package model;
import java.util.ArrayList;
import java.util.List;

import dataStructures.*;

public class Client {

	private String id;
	private int priorityTime;
	private double pricePaid;
	private Stack<Book> toPayBooks;
	private List<String> clientBooksList;

	public Client(String id, int priorityTime) {
		clientBooksList = new ArrayList<>();
		toPayBooks = new Stack<>(); 
		this.id = id;
		this.priorityTime = priorityTime;
		this.pricePaid = 0;
	}
	
	public void increasePriorityTime() {
		priorityTime = priorityTime + toPayBooks.size();
	}
	
	public Stack<Book> getToPayBooks() {
		return toPayBooks;
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
		return toPayBooks;
	}

	public void setBooks(Stack<Book> books) {
		this.toPayBooks = books;
	}

	public double getPricePaid() {
		return pricePaid;
	}

	public void setPricePaid(double pricePaid) {
		this.pricePaid = pricePaid;
	}

	public List<String> getClientBooksList() {
		return clientBooksList;
	}

	public void addInitialBooksList(String ISBNcode) {
		clientBooksList.add(ISBNcode);
	}

}
