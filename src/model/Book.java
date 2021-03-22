package model;

public class Book {

	private String title;
	private String initialChapters;
	private String criticsAndReviews;
	private String ISBNCode;
	private double price;
	private String shelveIndicator;
	
	/**
	 * Book constructor
	 * @param title is the book's title
	 * @param initialChapters are the book's initial chapters
	 * @param criticsAndReviews are the book's critics and reviews
	 * @param iSBNCode is the book's ISBN code
	 * @param price is the book's price
	 * @param shelveIndicator is the shelve where the book are
	 */
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
