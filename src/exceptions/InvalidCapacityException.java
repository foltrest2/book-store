package exceptions;

@SuppressWarnings("serial")
public class InvalidCapacityException extends Exception {

	public InvalidCapacityException() {
		super("The initial capacity can't be less than 0");
	}
}
