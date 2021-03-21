package model;

public class Cashier {

	private Client client;
	
	public Cashier(Client client) {
		this.client = client;
	}
	
	public void charge() {
		double charge = 0;
		while(0 < client.getBooks().size()) {
			charge += client.getBooks().pop().getPrice();
		}
		client.setPricePaid(charge);
	}
	
	public void deleteClient() {
		client = null;
	}
}