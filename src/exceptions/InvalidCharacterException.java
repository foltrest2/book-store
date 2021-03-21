package exceptions;

@SuppressWarnings("serial")
public class InvalidCharacterException extends Exception {

	public InvalidCharacterException() {
		super("Character is invalid");
	}
}