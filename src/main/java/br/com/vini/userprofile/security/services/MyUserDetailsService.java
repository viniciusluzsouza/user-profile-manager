package br.com.vini.userprofile.security.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.vini.userprofile.model.UserProfile;
import br.com.vini.userprofile.repository.UserProfileRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserProfileRepository userProfileRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	UserProfile userProfile = userProfileRepository.findByEmail(username);

	if (userProfile == null)
	    throw new UsernameNotFoundException("Email or password not found!");

	return new User(userProfile.getEmail(), userProfile.getPassword(), new ArrayList<>());
    }
}
