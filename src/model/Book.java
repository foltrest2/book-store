package model;

public class Book {

	private String ISBNCode;
	private double price;
	private int booksQuantity;
	
	public Book(String iSBNCode, double price, int booksQuantity) {
		ISBNCode = iSBNCode;
		this.price = price;
		this.booksQuantity = booksQuantity;
	}
	
	public Book(String iSBNCode, double price) {
		ISBNCode = iSBNCode;
		this.price = price;
		this.booksQuantity = 0;
	}
	
	public String getISBNCode() {
		return ISBNCode;
	}

	public void setISBNCode(String iSBNCode) {
		ISBNCode = iSBNCode;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getBooksQuantity() {
		return booksQuantity;
	}

	public void setBooksQuantity(int booksQuantity) {
		this.booksQuantity = booksQuantity;
	}
}
