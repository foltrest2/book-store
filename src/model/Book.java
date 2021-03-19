package model;

public class Book {

	private String title;
	private String initialChapters;
	private String criticsAndReviews;
	private String ISBNCode;
	private double price;
	private String shelveIndicator;

	public Book(String title, String initialChapters, String criticsAndReviews, String iSBNCode, double price, String shelveIndicator) {
		this.title = title;
		this.initialChapters = initialChapters;
		this.criticsAndReviews = criticsAndReviews;
		this.ISBNCode = iSBNCode;
		this.price = price;
		this.shelveIndicator = shelveIndicator;
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

	public String getTitle() {
		return title;
	}

	public String getInitialChapters() {
		return initialChapters;
	}

	public String getCriticsAndReviews() {
		return criticsAndReviews;
	}

	public String getShelveIndicator() {
		return shelveIndicator;
	}
}
