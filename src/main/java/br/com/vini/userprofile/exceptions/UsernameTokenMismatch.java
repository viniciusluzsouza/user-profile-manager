package br.com.vini.userprofile.exceptions;

public class UsernameTokenMismatch extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public UsernameTokenMismatch(String message, Throwable cause) {
	super(message, cause);
    }
    
    public UsernameTokenMismatch(String message) {
	super(message);
    }
    
}
