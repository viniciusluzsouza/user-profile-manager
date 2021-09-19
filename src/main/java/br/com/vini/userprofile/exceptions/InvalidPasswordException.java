package br.com.vini.userprofile.exceptions;

public class InvalidPasswordException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public InvalidPasswordException(String message, Throwable cause) {
	super(message, cause);
    }
    
    public InvalidPasswordException(String message) {
	super(message);
    }
    
}
