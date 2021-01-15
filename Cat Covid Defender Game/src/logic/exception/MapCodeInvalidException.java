package logic.exception;

public class MapCodeInvalidException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public MapCodeInvalidException(char c, int row, int col) {
		System.out.println("Code \"" + c + "\" at " + col + "x" + row +" (column x row) in map code is invalid.");
		System.out.println("There's no entity for code \"" + c + "\".");
	}
}
