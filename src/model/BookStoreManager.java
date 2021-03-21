package model;
import java.util.ArrayList;
import java.util.List;
import dataStructures.*;
import exceptions.EmptyQueueException;
import exceptions.InvalidCharacterException;

public class BookStoreManager {

	private Queue<Client> clientsQueue;
	private Queue<Client> keepOrder;
	private List<Client> initialClientsList;
	private ArrayList<Shelve> shelvesOnStore;
	private Client[] cashiersArray;
	private int cashiers;
	private static int timer = 0;

	public BookStoreManager() {
		initialClientsList = new ArrayList<>();
		shelvesOnStore = new ArrayList<>();
		clientsQueue = new Queue<>();
		keepOrder = new Queue<>();
	}

	// ******* Adding algorithms **************

	public boolean addClient(String id) {
		boolean clientAdded = false;
		if(searchClient(id) == null) {
			int priorityTime = timer+=1;
			Client toAdd = new Client(id, priorityTime);	
			initialClientsList.add(toAdd);
			clientAdded = true;
		}
		return clientAdded;
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

	// ******* Sorting algorithms *************

	public ArrayList<String> countingSort(ArrayList<String> isbnList) throws InvalidCharacterException {

		Book [] books = new Book[isbnList.size()];
		for (int i = 0; i < isbnList.size(); i++) {
			books[i] = bookWithGivenIsbn(isbnList.get(i));
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
		for (int i = 0; i < shelvesOnStore.size(); i++) {
			sortedBooks.add(outputArray[i].getISBNCode());
		}
		return sortedBooks;
	}

	public ArrayList<String> heapSort(List<String> list) {
		Book [] books = new Book[list.size()];
		for (int i = 0; i < list.size(); i++) {
			books[i] = bookWithGivenIsbn(list.get(i));

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

	public void heapify(Book array[], int SizeofHeap, int i) {
		int largestelement = i; 
		int leftChild  = 2*i + 1; 
		int rightChild  = 2*i + 2; 
		if (leftChild  < SizeofHeap && array[leftChild].getShelveIndicator().compareTo(array[largestelement].getShelveIndicator()) > 0)
			largestelement = leftChild ;
		if (rightChild  < SizeofHeap && array[rightChild].getShelveIndicator().compareTo(array[largestelement].getShelveIndicator()) > 0)
			largestelement = rightChild ;
		if (largestelement != i) {
			Book temp = array[i];
			array[i] = array[largestelement];
			array[largestelement] = temp;
			heapify(array, SizeofHeap, largestelement);
		}
	}

	public ArrayList<String> insertionSort(ArrayList<String> arr) {
		for (int j = 1; j < arr.size(); j++) {
			String current = arr.get(j);
			int i = j-1;
			while ((i > -1) && (arr.get(i).compareTo(current)>0)) {
				arr.set(i+1,arr.get(i));
				i--;
			}
			arr.set(i+1, current);
		}
		return arr;
	}

	public List<Client> clientCountingSort(List<Client> clientList) throws InvalidCharacterException {
		Client [] clients = new Client[clientList.size()];
		for (int i = 0; i < clientList.size(); i++) {
			clients[i] = clientList.get(i);
		}
		int[] counts = new int[100000000];

		for (int i = 0; i < clients.length; i++) {
			counts[clients[i].getPriorityTime()]++;
		}

		int sumTillLast = 0;
		for (int i = 0; i < counts.length; i++) {
			int currentElement = counts[i];
			counts[i] = sumTillLast;
			sumTillLast = sumTillLast + currentElement;
		}
		Client[] outputArray = new Client[clients.length];
		ArrayList<Client> sortedClients = new ArrayList<>();

		for (int i = 0; i < clients.length; i++) {
			int positionOfInsert = counts[clients[i].getPriorityTime()];
			outputArray[positionOfInsert] = clients[i];
			counts[clients[i].getPriorityTime()]++;
		}
		for (int i = 0; i < outputArray.length; i++) {
			sortedClients.add(outputArray[i]);
		}
		return sortedClients;
	}

	// ********* Search algorithms ******************

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

	public HashTable<String,Integer> existenceWithGivenIsbn(String isbn) {
		boolean found = false;
		HashTable<String, Integer> existenceShelve = null;
		for (int i = 0; i < shelvesOnStore.size() && !found; i++) {
			if (shelvesOnStore.get(i).getSlots().contains(isbn)) {
				existenceShelve = shelvesOnStore.get(i).getBooksExistence();
				found = true;
			}
		}
		return existenceShelve;
	}

	public Book bookWithGivenIsbn(String isbn) {
		Book shelve = null;
		boolean found = false;
		for (int i = 0; i < shelvesOnStore.size() && !found; i++) {
			if (shelvesOnStore.get(i).getSlots().contains(isbn)) {
				shelve = shelvesOnStore.get(i).getSlots().get(isbn);
				found = true;
			}
		}
		return shelve;
	}

	public Client searchClient(String id) {
		boolean found = false;
		Client clientFound = null;
		for (int i = 0; i < initialClientsList.size() && !found; i++) {
			if(initialClientsList.get(i).getId().equals(id)) {
				found = true;
				clientFound = initialClientsList.get(i);
			}	
		}
		return clientFound;
	}

	// *************** Auxiliary algorithms ***********************

	public void booksToBag(Client client) throws InvalidCharacterException {
		for (int i = 0; i < client.getClientBooksList().size(); i++) {
			Book bookToAdd = bookWithGivenIsbn(client.getClientBooksList().get(i));
			client.getBooks().push(bookToAdd);
		}
		client.increasePriorityTime();
	}

	public void timerReset() {
		timer = 0;
	}

	public int radix128(String x) throws InvalidCharacterException{
		int result = 0;
		int cont = 0;
		for (int i = x.length()-1; i >= 0; i--) {
			if (x.charAt(i) > 127) {
				throw new InvalidCharacterException();
			}
			else {
				char y = x.charAt(i);
				result += y*Math.pow(128, cont);
				cont++;
			}
		}
		return result;
	}

	public String addAndCheckBooksToClientBookList(Client client, String isbnCode) throws InvalidCharacterException{
		String info = "";
		Book book = bookWithGivenIsbn(isbnCode);
		if (book != null) {
			Shelve bookShelve =	binaryShelveSearch(book.getShelveIndicator());
			boolean added = false;
			for (int i = 0; i <bookShelve.getBooksExistence().size() && !added; i++) {
				if(bookShelve.getBooksExistence().get(isbnCode) > 0) {
					client.addInitialBooksList(isbnCode);
					int value = bookShelve.getBooksExistence().get(isbnCode) - 1; //Less one due previous added
					bookShelve.getBooksExistence().delete(isbnCode);
					addBookPerShelve(book.getTitle(), book.getInitialChapters(), book.getCriticsAndReviews(), book.getISBNCode(), book.getPrice(), book.getShelveIndicator(), value);
					added = true;
				} else {
					info += "Book\nISBN code: "+isbnCode+"\nTitle: "+bookWithGivenIsbn(isbnCode).getTitle()+"\nThere have no more existence!\n";
				}
			}
		}
		return info;
	}

	public String finalReport() throws EmptyQueueException {
		String report = "";
		for (int i = 0; i < keepOrder.size();) {
			Client dequeued = keepOrder.dequeue(); 
			report += dequeued.getId() + " " + dequeued.getPricePaid() + "\n";
			for (int j = dequeued.getClientBooksList().size()-1; j >= 0; j--) {
				report += dequeued.getClientBooksList().get(j) + " ";	
			}
			report += "\n";
		}
		return report;
	}

	// ************* Queue and Pay algorithms *********************************

	@SuppressWarnings("unchecked")
	public void clientsToQueue(List <Client> clientsToQueue) throws EmptyQueueException, CloneNotSupportedException {
		for (int i = 0; i < clientsToQueue.size(); i++) {
			if(!clientsToQueue.get(i).getBooks().isEmpty()) {
				clientsQueue.enqueue(clientsToQueue.get(i));
			}		
		}
		keepOrder = (Queue<Client>) clientsQueue.clone();
	}

	public void payBooks() throws EmptyQueueException, CloneNotSupportedException {
		boolean emptyQueue = false, stop = false;
		cashiersArray = new Client[cashiers];
		Client client = null;
		for (int i = 0; i < cashiersArray.length && !stop; i++) {
			if (!clientsQueue.isEmpty()) {
				client = clientsQueue.dequeue();
				cashiersArray[i] = client;
			}
			else {
				stop = true;
			}
		}
		while (!emptyQueue) {      
			for (int i = 0; i < cashiersArray.length && !emptyQueue; i++) {
				if(!cashiersArray[i].getBooks().isEmpty()) {
					double priceToPay = cashiersArray[i].getBooks().pop().getPrice();
					cashiersArray[i].setPricePaid(priceToPay);
				}
				else {
					if (!clientsQueue.isEmpty()) {
						client = clientsQueue.dequeue();
						cashiersArray[i] = client;
					}else {
						if(client.getBooks().isEmpty())
							emptyQueue = true;	
					}
				}
			}
		}
	}

	// **************** Getters and setters *********************** 

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
}