package br.com.vini.userprofile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vini.userprofile.messages.dto.UserProfileDto;
import br.com.vini.userprofile.services.UserProfileManagerService;

@RestController
@RequestMapping(value = "/userProfile")
public class UserProfileController {
    
    @Autowired
    private UserProfileManagerService userProfileServiceManager;
    
    @GetMapping
    public UserProfileDto getUserProfileById(@RequestHeader("Authorization") String auth, String id) {
	return new UserProfileDto(userProfileServiceManager.getUserProfileById(auth, id));
    }
    
}
