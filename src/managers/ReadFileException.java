package managers;

public class ReadFileException extends Exception {

	private static final long serialVersionUID = -5485159436896335678L;

	public ReadFileException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReadFileException(String message) {
		super(message);
	}
}
