package model;

public class Progressitem {

public final static int MAX = 300;

	private int numberOfProgress;
	private boolean isLoading;

	/**
	 * Constructor of progress item
	 */
	public Progressitem() {
		numberOfProgress = 0;
		isLoading = true;
	}
	
	public int getNumberOfProgress() {
		return numberOfProgress;
	}
	
	/**
	 * This method increase the indicator of progress
	 */
	public void advance() {
		if(numberOfProgress>=MAX) {
			isLoading = false;
		}else {			
			numberOfProgress++;
		}
	}
	
	public void setLoading(boolean act) {
		isLoading = act;
	}
	
	public boolean isLoading() {
		return isLoading;
	}
}
