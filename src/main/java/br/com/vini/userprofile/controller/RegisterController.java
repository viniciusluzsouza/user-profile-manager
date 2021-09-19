package br.com.vini.userprofile.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.vini.userprofile.dto.UserProfileDto;
import br.com.vini.userprofile.exceptions.EmailAlreadyRegisteredException;
import br.com.vini.userprofile.form.UserProfileForm;
import br.com.vini.userprofile.model.UserProfile;
import br.com.vini.userprofile.repository.PhoneRepository;
import br.com.vini.userprofile.repository.UserProfileRepository;
import br.com.vini.userprofile.security.utils.JwtUtil;
import br.com.vini.userprofile.services.AuthenticationService;

@RestController
@RequestMapping(value="/register")
public class RegisterController {
    
    @Autowired
    private UserProfileRepository userProfileRepository;
    
    @Autowired
    private PhoneRepository phoneRepository;
    
    @Autowired
    private AuthenticationService authenticationService;
    
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @PostMapping
    @Transactional
    public ResponseEntity<UserProfileDto> register(@RequestBody @Valid UserProfileForm form, UriComponentsBuilder uriBuilder) throws Exception {
	UserProfile userProfile = form.getUserProfile();
	
	if (userProfileRepository.findByEmail(userProfile.getEmail()) != null)
	    throw new EmailAlreadyRegisteredException("E-mail já existente");
	
//	userProfile.setPassword(bCryptPasswordEncoder.encode(userProfile.getPassword()));
	userProfileRepository.save(userProfile);
	userProfile.getPhones().forEach(phone -> {
	    phone.setUserProfile(userProfile);
	    phoneRepository.save(phone);
	});
	
	authenticationService.login(userProfile.getEmail(), userProfile.getPassword());
	
	UserProfileDto userProfileDto = new UserProfileDto(userProfile);
	URI uri = uriBuilder.path("/register/{id}").buildAndExpand(userProfile.getId()).toUri();
	return ResponseEntity.created(uri).body(userProfileDto);
    }
    
    @GetMapping
    public List<UserProfileDto> getUserProfiles() {
	List<UserProfile> userProfiles = userProfileRepository.findAll();
	return userProfiles.stream().map(UserProfileDto::new).collect(Collectors.toList());
    }
}
