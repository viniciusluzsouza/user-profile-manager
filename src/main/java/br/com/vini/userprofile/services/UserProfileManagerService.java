package br.com.vini.userprofile.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.vini.userprofile.exceptions.EmailAlreadyRegisteredException;
import br.com.vini.userprofile.exceptions.UserProfileNotFoundException;
import br.com.vini.userprofile.model.Phone;
import br.com.vini.userprofile.model.UserProfile;
import br.com.vini.userprofile.repository.PhoneRepository;
import br.com.vini.userprofile.repository.UserProfileRepository;

@Service
public class UserProfileManagerService {
    
    @Autowired
    private UserProfileRepository userProfileRepository;
    
    @Autowired
    private PhoneRepository phoneRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public UserProfile create(String name, String email, String password, List<Phone> phones) {
	UserProfile userProfile = new UserProfile(name, email, password, phones);
	
	if (userProfileRepository.findByEmail(userProfile.getEmail()) != null)
	    throw new EmailAlreadyRegisteredException("E-mail já existente");
	
	userProfile.setPassword(this.bCryptPasswordEncoder.encode(password));
	userProfileRepository.save(userProfile);
	userProfile.getPhones().forEach(phone -> {
	    phone.setUserProfile(userProfile);
	    phoneRepository.save(phone);
	});
	
	return userProfile;
    }
    
    public UserProfile getUserProfileById(String auth, String id) {
	UserProfile userProfile = null;
	String token = auth.substring(7);
	UserProfile userProfileFromToken = userProfileRepository.findByToken(token);
	
	if (id.equals(userProfileFromToken.getId().toString())) {
	    userProfile = userProfileRepository.findById(UUID.fromString(id));
	} else {
	    throw new UserProfileNotFoundException("Perfil de usuário não encontrado");
	}
	
	return userProfile;
    }
    
}
