package br.com.vini.userprofile.validation;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

public class AuthorizationErrorHandler {
    
    private final String DEFAULT_ERROR = "Não autorizado";
    private final String EXPIRED_SESSION_MESSAGE = "Sessão inválida";
    
    public void setDefaultErrorResponse(HttpStatus status, HttpServletResponse response) {
	response.setStatus(status.value());
	setMessageDto(response, DEFAULT_ERROR);
    }
    
    public void setExpiredSessionResponse(HttpServletResponse response) {
	response.setStatus(HttpStatus.UNAUTHORIZED.value());
	setMessageDto(response, EXPIRED_SESSION_MESSAGE);
    }
    
    private void setMessageDto(HttpServletResponse response, String message) {
	response.setContentType("application/json");
	
	ErrorMessageDto errorMessageDto = new ErrorMessageDto(message);
	try {
	    response.getWriter().write(errorMessageDto.convertToJson());
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
