package model;
import java.util.ArrayList;
import java.util.List;
import dataStructures.*;

public class BookStoreManager {
	
	private LinkedList<Shelve> shelves;
	private Queue<Client> clientsQueue;
	private List<Client> initialClientsList;
	private int cashiers;
	
	public BookStoreManager() {
		initialClientsList = new ArrayList<>();
		shelves = new LinkedList<>();
		clientsQueue = new Queue<>();
	}

	public void addClient(String id, int priorityTime) {
		Client toAdd = new Client(id, priorityTime);
		initialClientsList.add(toAdd);
	}

	
	
	public int getCashiers() {
		return cashiers;
	}

	public void setCashiers(int cashiers) {
		this.cashiers = cashiers;
	}

	public LinkedList<Shelve> getShelves() {
		return shelves;
	}

	public void setShelves(LinkedList<Shelve> shelves) {
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
	
}
