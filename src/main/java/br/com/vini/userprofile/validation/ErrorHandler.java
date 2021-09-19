package br.com.vini.userprofile.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.vini.userprofile.exceptions.EmailAlreadyRegisteredException;
import br.com.vini.userprofile.exceptions.InvalidEmailAndPasswordException;
import br.com.vini.userprofile.exceptions.InvalidPasswordException;
import br.com.vini.userprofile.exceptions.UserProfileNotFoundException;
import io.jsonwebtoken.SignatureException;

@RestControllerAdvice
public class ErrorHandler {
    
    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ErrorMessageDto handleEmailAlreadyRegisteredException(EmailAlreadyRegisteredException emailAlreadyRegisteredException) {
	return new ErrorMessageDto(emailAlreadyRegisteredException.getMessage());
    }
    
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidPasswordException.class)
    public ErrorMessageDto handleInvalidPasswordException(InvalidPasswordException invalidPasswordException) {
	return new ErrorMessageDto(invalidPasswordException.getMessage());
    }
    
    @ResponseStatus(code = HttpStatus.OK)
    @ExceptionHandler(InvalidEmailAndPasswordException.class)
    public ErrorMessageDto handleInvalidEmailAndPasswordException(Exception exception) {
	return new ErrorMessageDto(exception.getMessage());
    }
    
    @ResponseStatus(code = HttpStatus.OK)
    @ExceptionHandler(UserProfileNotFoundException.class)
    public ErrorMessageDto handleUserProfileNotFoundException(UserProfileNotFoundException userProfileNotFoundException) {
	return new ErrorMessageDto(userProfileNotFoundException.getMessage());
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorMessageDto handleException(Exception exception) {
	return new ErrorMessageDto(exception.getMessage());
    }
    
}
