package logic.exception;

public class GameStatusNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public GameStatusNotFoundException(String status) {
		System.out.println("The status \"" + status + "\" is invalid.");
	}
}