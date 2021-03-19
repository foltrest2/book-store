package model;
import java.util.ArrayList;
import java.util.List;
import dataStructures.*;
import exceptions.InvalidCharacterException;

public class BookStoreManager {
	
	private ArrayList<Shelve> shelves;
	private Queue<Client> clientsQueue;
	private List<Client> initialClientsList;
	private ArrayList<Shelve> shelvesOnStore;
	private int cashiers;
	
	public BookStoreManager() {
		initialClientsList = new ArrayList<>();
		shelves = new ArrayList<>();
		shelvesOnStore = new ArrayList<>();
		clientsQueue = new Queue<>();
	}

	public void addClient(String id, int priorityTime) {
		Client toAdd = new Client(id, priorityTime);
		initialClientsList.add(toAdd);
	}

	public boolean addShelveQuantity(String indicator, int slots) throws InvalidCharacterException {
		boolean shelveAdded = false;
		if (binaryShelveSearch(indicator) == null) {
			shelveAdded = true;
			shelvesOnStore.add(new Shelve(indicator, slots));
		}
		return shelveAdded;
	}
	
	public boolean addBokPerShelve(String title, String initialChapters, String criticsAndReviews, String iSBNCode, double price, String shelveIndicator, int booksQuantity) throws InvalidCharacterException {
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
		int toFindShelve = convertirCadenaANatural(k);
		Shelve shelveFound = null;
		int i = 0;
		int j = shelvesOnStore.size() - 1;
		int m=0;
		while (i <= j && !found) {
			m = (i + j) / 2;
			if (convertirCadenaANatural(shelvesOnStore.get(m).getIndicator()) == toFindShelve) {
				found = true;
				shelveFound = shelvesOnStore.get(m);
			} else {
				if (convertirCadenaANatural(shelvesOnStore.get(m).getIndicator()) > toFindShelve) {
					j = m - 1;
				} else {
					i = m + 1;
				}
			}
		}
		return shelveFound;
	}

	public int getCashiers() {
		return cashiers;
	}

	public void setCashiers(int cashiers) {
		this.cashiers = cashiers;
	}

	public ArrayList<Shelve> getShelves() {
		return shelves;
	}

	public void setShelves(ArrayList<Shelve> shelves) {
		this.shelves = shelves;
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

	public void sort(ArrayList<String> arr) {
		int n = arr.size();
		int[] A = new int[n]; // 1
        int k = 0; // 1
        for (int i = 0; i < n; i++) { // n+1
            int value =  0;// n
            A[i] = value; // n
            if (k < value) // n
                k = value; // n
        }
        int[] C = new int[k + 1]; // 1
        for (int i = 0; i <= k; i++) // k+2
            C[i] = 0; // k+1
        for (int i = 0; i < n; i++) // n+1
            C[A[i]] = C[A[i]] + 1; // n
        for (int i = 1; i <= k; i++) { // k+1
            C[i] = C[i] + C[i - 1]; // k
        }
        int[] B = new int[n]; // 1
        for (int i = n - 1; i >= 0; i--) { // n+1
            B[C[A[i]] - 1] = A[i]; // n
            C[A[i]] = C[A[i]] - 1; // n
        }
        for (int i = 0; i < B.length; i++) { // n+1
            
        }		
	}
	public static int convertirCadenaANatural(String x) throws InvalidCharacterException{
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
