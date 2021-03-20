package model;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import dataStructures.*;
import exceptions.InvalidCharacterException;

public class BookStoreManager {
	
	private Queue<Client> clientsQueue;
	private List<Client> initialClientsList;
	private ArrayList<Shelve> shelvesOnStore;
	private int cashiers;
	Comparator<String> comp;

	public BookStoreManager() {
		initialClientsList = new ArrayList<>();
		shelvesOnStore = new ArrayList<>();
		clientsQueue = new Queue<>();
	}


	public void addClient(String id, int priorityTime) {
		Client toAdd = new Client(id, priorityTime);
		initialClientsList.add(toAdd);
	}

	public boolean addShelve(String indicator, int slots) throws InvalidCharacterException {
		boolean shelveAdded = false;
		if (binaryShelveSearch(indicator) == null) {
			shelveAdded = true;
			shelvesOnStore.add(new Shelve(indicator, slots));
		}
		return shelveAdded;
	}

	public boolean addBookPerShelve(String title, String initialChapters, String criticsAndReviews, String iSBNCode, double price, String shelveIndicator, int booksQuantity) throws InvalidCharacterException {

		boolean bookAdded = false;
		Shelve shelveToAddBook = binaryShelveSearch(shelveIndicator);
		if (shelveToAddBook != null) {
			Book bookToAdd = new Book(title, initialChapters, criticsAndReviews, iSBNCode, price, shelveIndicator);
			shelveToAddBook.addBook(iSBNCode, bookToAdd, booksQuantity);
			bookAdded = true;
		}
		return bookAdded;
	}

	public Shelve binaryShelveSearch(String k) throws InvalidCharacterException {
		boolean found = false;
		int toFindShelve = radix128(k);
		Shelve shelveFound = null;
		int i = 0;
		int j = shelvesOnStore.size() - 1;
		int m = 0;
		while (i <= j && !found) {
			m = (i + j) / 2;
			if (radix128(shelvesOnStore.get(m).getIndicator()) == toFindShelve) {
				found = true;
				shelveFound = shelvesOnStore.get(m);
			} else {
				if (radix128(shelvesOnStore.get(m).getIndicator()) > toFindShelve) {

					j = m - 1;
				} else {
					i = m + 1;
				}
			}
		}
		return shelveFound;
	}
	
	public boolean binarySearchShelve(String indicator) {
		boolean found = false; 
		for (int i = 0; i < shelvesOnStore.size(); i++) {
			
		}
		return found;
	}
	
    public static String binarySearch(int[] array, int k) {
		boolean found = false;
		int i = 0;
		int j = array.length - 1;
		int m=0;
		String info = "";
		while (i <= j && !found) {
			m = (i + j) / 2;
			if (array[m] == k) {
				found = true;
			} else {
				if (array[m] > k) {
					j = m - 1;
				} else {
					i = m + 1;
				}
			}
		}
		return info;
    }

	public ArrayList<String> sort(ArrayList<String> isbnList) {
		Book [] books = new Book[isbnList.size()];
		for (int i = 0; i < isbnList.size(); i++) {
			books[i] = bookOfShelve(isbnList.get(i));
		}
		int size = books.length; 
		for (int i = size / 2 - 1; i >= 0; i--)
			heapify(books, size, i);
		for (int i=size-1; i>=0; i--) {
			Book x = books[0];
			books[0] = books[i];
			books[i] = x;
			heapify(books, i, 0);
		}
		ArrayList<String> isbnSorted = new ArrayList<>();
		for (int i = 0; i < books.length; i++) {
			isbnSorted.add(books[i].getISBNCode());
		}
		return isbnSorted;
	}

	void heapify(Book array[], int SizeofHeap, int i) {
		int largestelement = i; // Set largest element as root
		int leftChild  = 2*i + 1; // index of left child = 2*i + 1
		int rightChild  = 2*i + 2; //index of right child  = 2*i + 2
		// left child is greater than root
		if (leftChild  < SizeofHeap && array[leftChild].getShelveIndicator().compareTo(array[largestelement].getShelveIndicator()) > 0)
			largestelement = leftChild ;
		//right child is greater than largest
		if (rightChild  < SizeofHeap && array[rightChild].getShelveIndicator().compareTo(array[largestelement].getShelveIndicator()) > 0)
			largestelement = rightChild ;
		// If largestelement is not root
		if (largestelement != i) {
			Book temp = array[i];
			array[i] = array[largestelement];
			array[largestelement] = temp;
			heapify(array, SizeofHeap, largestelement);
		}
	}
	
	public void insertionSort(ArrayList<String> arr) {
	    for (int j = 1; j < arr.size(); j++) {
	        String current = arr.get(j);
	        int i = j-1;
	        while ((i > -1) && (arr.get(i).compareTo(current)>0)) {
	            arr.set(i+1,arr.get(i));
	            i--;
	        }
	        arr.set(i+1, current);
	    }
	}

	public int getCashiers() {
		return cashiers;
	}

	public void setCashiers(int cashiers) {
		this.cashiers = cashiers;
	}

	public List<Client> getInitialClientsList() {
		return initialClientsList;
	}

	public void setInitialClientsList(List<Client> initialClientsList) {
		this.initialClientsList = initialClientsList;
	}

	public Queue<Client> getClientsQueue() {
		return clientsQueue;
	}

	public void setClientsQueue(Queue<Client> clientsQueue) {
		this.clientsQueue = clientsQueue;
	}

	public ArrayList<Shelve> getShelvesOnStore() {
		return shelvesOnStore;
	}

	public ArrayList<String> countingSort(ArrayList<String> isbnList) throws InvalidCharacterException {
		Book [] books = new Book[isbnList.size()];
		for (int i = 0; i < isbnList.size(); i++) {
			books[i] = bookOfShelve(isbnList.get(i));
		}
		int[] counts = new int[127];

		for (int i = 0; i < books.length; i++) {
			counts[radix128(books[i].getShelveIndicator())]++;
		}

		int sumTillLast = 0;
		for (int i = 0; i < counts.length; i++) {
			int currentElement = counts[i];
			counts[i] = sumTillLast;
			sumTillLast = sumTillLast + currentElement;
		}
		Book[] outputArray = new Book[books.length];
		ArrayList<String> sortedBooks = new ArrayList<>();

		for (int i = 0; i < books.length; i++) {
			int positionOfInsert = counts[radix128(books[i].getShelveIndicator())];
			outputArray[positionOfInsert] = books[i];
			counts[radix128(books[i].getShelveIndicator())]++;
		}
		for (int i = 0; i < outputArray.length; i++) {
			sortedBooks.add(outputArray[i].getISBNCode());
		}
		return sortedBooks;
	}

	public Book bookOfShelve(String isbn) {
		Book shelve = null;
		for (int i = 0; i < shelvesOnStore.size(); i++) {
			if (shelvesOnStore.get(i).getSlots().contains(isbn)) {
				shelve = shelvesOnStore.get(i).getSlots().get(isbn);
			}
		}
		return shelve;
	}

	public static int radix128(String x) throws InvalidCharacterException{
		int result = 0;
		int cont = 0;
		for (int i = x.length()-1; i >= 0; i--) {
			if (x.charAt(i) > 127) {
				throw new InvalidCharacterException();
			}
			else {
				char y = x.charAt(i);
				System.out.println((int)y);
				result += y*Math.pow(128, cont);
				cont++;
				System.out.println(result);
			}
		}
		return result;
	}

}
