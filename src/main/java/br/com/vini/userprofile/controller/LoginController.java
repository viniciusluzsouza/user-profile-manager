package br.com.vini.userprofile.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.vini.userprofile.messages.dto.UserProfileDto;
import br.com.vini.userprofile.messages.form.LoginForm;
import br.com.vini.userprofile.services.AuthenticationService;

@RestController
@RequestMapping(value = "/login")
public class LoginController {
    
    @Autowired
    private AuthenticationService authenticationService;
    
    @PostMapping
    public UserProfileDto register(@RequestBody @Valid LoginForm form, UriComponentsBuilder uriBuilder) throws Exception {
	return new UserProfileDto(authenticationService.login(form.getEmail(), form.getPassword()));
    }
}
