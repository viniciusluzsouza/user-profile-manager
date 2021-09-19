package br.com.vini.userprofile.exceptions;

public class InvalidEmailAndPasswordException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public InvalidEmailAndPasswordException(String message, Throwable cause) {
	super(message, cause);
    }
    
    public InvalidEmailAndPasswordException(String message) {
	super(message);
    }
    
}
