package model;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import dataStructures.*;

public class BookStoreManager {

	
	
	private Queue<Client> clientsQueue;
	private List<Client> initialClientsList;
	private ArrayList<Shelve> shelvesOnStore;
	private int cashiers;


	public BookStoreManager() {
		initialClientsList = new ArrayList<>();
		shelvesOnStore = new ArrayList<>();
		clientsQueue = new Queue<>();
	}

	public void addClient(String id, int priorityTime) {
		Client toAdd = new Client(id, priorityTime);
		initialClientsList.add(toAdd);
	}
	
	public void addShelveQuantity(String indicator, int slots ) {
		shelvesOnStore.add(new Shelve(indicator, slots));
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

	public int getCashiers() {
		return cashiers;
	}

	public void setCashiers(int cashiers) {
		this.cashiers = cashiers;
	}

	public Queue<Client> getClientsQueue() {
		return clientsQueue;
	}

	public void setClientsQueue(Queue<Client> clientsQueue) {
		this.clientsQueue = clientsQueue;
	}

	public void sort(char arr[]) {
		int n = arr.length;

		// The output character array that will have sorted arr
		char output[] = new char[n];

		// Create a count array to store count of inidividul
		// characters and initialize count array as 0
		int count[] = new int[256];
		for (int i = 0; i < 256; ++i)
			count[i] = 0;

		// store count of each character
		for (int i = 0; i < n; ++i)
			++count[arr[i]];

		// Change count[i] so that count[i] now contains actual
		// position of this character in output array
		for (int i = 1; i <= 255; ++i)
			count[i] += count[i - 1];

		// Build the output character array
		// To make it stable we are operating in reverse order.
		for (int i = n - 1; i >= 0; i--) {
			output[count[arr[i]] - 1] = arr[i];
			--count[arr[i]];
		}

		// Copy the output array to arr, so that arr now
		// contains sorted characters
		for (int i = 0; i < n; ++i)
			arr[i] = output[i];
	}
	
	
}
