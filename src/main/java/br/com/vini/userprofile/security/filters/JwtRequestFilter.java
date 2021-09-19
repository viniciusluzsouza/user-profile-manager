package br.com.vini.userprofile.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.vini.userprofile.error.AuthorizationErrorHandler;
import br.com.vini.userprofile.exceptions.InvalidEmailAndPasswordException;
import br.com.vini.userprofile.exceptions.UsernameTokenMismatch;
import br.com.vini.userprofile.messages.error.ErrorMessageDto;
import br.com.vini.userprofile.security.services.MyUserDetailsService;
import br.com.vini.userprofile.security.utils.JwtUtil;
import br.com.vini.userprofile.services.AuthenticationService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    
    @Autowired
    private MyUserDetailsService userDetailService;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private AuthorizationErrorHandler authorizationErrorHandler;
    
    @Autowired
    private AuthenticationService authenticationService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
	try {
	    final String authorizationHeader = request.getHeader("Authorization");
	    
	    String username = null;
	    String jwt = null;
	    
	    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
		jwt = authorizationHeader.substring(7);
		username = jwtUtil.extractUsername(jwt);
		authenticationService.validateUsernameTokenFromDb(username, jwt);
	    }
	    
	    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
		UserDetails userDetails = this.userDetailService.loadUserByUsername(username);
		if (jwtUtil.validateToken(jwt, userDetails)) {
		    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
	    }
	    
	    filterChain.doFilter(request, response);
	    
	} catch (SignatureException | UsernameTokenMismatch e) {
	    authorizationErrorHandler.setDefaultErrorResponse(HttpStatus.UNAUTHORIZED, response);
	} catch (ExpiredJwtException e) {
	    authorizationErrorHandler.setExpiredSessionResponse(response);
	}
	
    }
    
}
