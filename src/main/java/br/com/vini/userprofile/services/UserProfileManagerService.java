package br.com.vini.userprofile.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vini.userprofile.exceptions.EmailAlreadyRegisteredException;
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
    
    public UserProfile create(String name, String email, String password, List<Phone> phones) {
	UserProfile userProfile = new UserProfile(name, email, password, phones);
	
	if (userProfileRepository.findByEmail(userProfile.getEmail()) != null)
	    throw new EmailAlreadyRegisteredException("E-mail já existente");
	
	userProfileRepository.save(userProfile);
	userProfile.getPhones().forEach(phone -> {
	    phone.setUserProfile(userProfile);
	    phoneRepository.save(phone);
	});
	
	return userProfile;
    }
    
}
