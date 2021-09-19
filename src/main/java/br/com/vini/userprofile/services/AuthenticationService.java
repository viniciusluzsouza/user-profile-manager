package br.com.vini.userprofile.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.vini.userprofile.exceptions.InvalidEmailAndPasswordException;
import br.com.vini.userprofile.exceptions.InvalidPasswordException;
import br.com.vini.userprofile.exceptions.UsernameTokenMismatch;
import br.com.vini.userprofile.model.UserProfile;
import br.com.vini.userprofile.repository.UserProfileRepository;
import br.com.vini.userprofile.security.utils.JwtUtil;

@Service
public class AuthenticationService {
    
    @Autowired
    private UserProfileRepository userProfileRepository;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private JwtUtil jwtTokenUtil;
    
    private final String INVALID_USER_OR_PASSWORD_MESSAGE = "Usuário e/ou senha inválidos";
    
    public UserProfile login(String email, String senha) throws Exception {
	UserProfile userProfile = null;
	
	try {
	    final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
	    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, senha));
	    final String jwt = jwtTokenUtil.generateToken(userDetails);
	    
	    userProfile = userProfileRepository.findByEmail(email);
	    userProfile.setToken(jwt);
	    userProfile.setLastLogin(LocalDateTime.now());
	    userProfileRepository.save(userProfile);
	    
	} catch (UsernameNotFoundException e) {
	    throw new InvalidEmailAndPasswordException(INVALID_USER_OR_PASSWORD_MESSAGE, e);
	} catch (BadCredentialsException e) {
	    throw new InvalidPasswordException(INVALID_USER_OR_PASSWORD_MESSAGE, e);
	}
	
	return userProfile;
    }
    
    public void validateUsernameTokenFromDb(String email, String token) throws UsernameTokenMismatch {
	UserProfile userProfile = userProfileRepository.findByEmail(email);
	if (!token.equals(userProfile.getToken()))
	    throw new UsernameTokenMismatch("Token inválido");
    }
    
}
