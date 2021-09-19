package br.com.vini.userprofile.exceptions;

public class EmailAlreadyRegisteredException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public EmailAlreadyRegisteredException(String message, Throwable cause) {
	super(message, cause);
    }
    
    public EmailAlreadyRegisteredException(String message) {
	super(message);
    }
    
}
