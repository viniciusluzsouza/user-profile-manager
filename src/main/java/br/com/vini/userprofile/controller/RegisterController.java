package br.com.vini.userprofile.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.vini.userprofile.dto.UserProfileDto;
import br.com.vini.userprofile.form.UserProfileForm;
import br.com.vini.userprofile.model.UserProfile;
import br.com.vini.userprofile.services.AuthenticationService;
import br.com.vini.userprofile.services.UserProfileManagerService;

@RestController
@RequestMapping(value = "/register")
public class RegisterController {
    
    @Autowired
    private AuthenticationService authenticationService;
    
    @Autowired
    private UserProfileManagerService userProfileManagerService;
    
    @PostMapping
    @Transactional
    public ResponseEntity<UserProfileDto> register(@RequestBody @Valid UserProfileForm form, UriComponentsBuilder uriBuilder) throws Exception {
	UserProfile userProfile = userProfileManagerService.create(form.getName(), form.getEmail(), form.getPassword(), form.getPhones());
	authenticationService.login(userProfile.getEmail(), userProfile.getPassword());
	UserProfileDto userProfileDto = new UserProfileDto(userProfile);
	URI uri = uriBuilder.path("/register/{id}").buildAndExpand(userProfile.getId()).toUri();
	return ResponseEntity.created(uri).body(userProfileDto);
    }
    
}
