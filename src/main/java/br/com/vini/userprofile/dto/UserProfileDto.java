package br.com.vini.userprofile.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.vini.userprofile.model.UserProfile;

public class UserProfileDto {
    
    private String id;
    private String name;
    private String email;
    private String password;
    private List<PhoneDto> phones = new ArrayList<>();
    
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime last_login;
    private String token;
    
    public UserProfileDto(UserProfile userProfile) {
	this.setId(userProfile.getId().toString());
	this.name = userProfile.getName();
	this.email = userProfile.getEmail();
	this.password = userProfile.getPassword();
	this.created = userProfile.getCreated();
	this.modified = userProfile.getModified();
	this.last_login = userProfile.getLastLogin();
	this.token = userProfile.getToken();
	this.phones = userProfile.getPhones().stream().map(PhoneDto::new).collect(Collectors.toList()); 
    }

    public String getName() {
	return name;
    }
    
    public void setName(String name) {
	this.name = name;
    }
    
    public String getEmail() {
	return email;
    }
    
    public void setEmail(String email) {
	this.email = email;
    }
    
    public String getPassword() {
	return password;
    }
    
    public void setPassword(String password) {
	this.password = password;
    }
    
    public List<PhoneDto> getPhones() {
	return phones;
    }
    
    public void setPhones(List<PhoneDto> phones) {
	this.phones = phones;
    }
    
    public LocalDateTime getCreated() {
	return created;
    }
    
    public void setCreated(LocalDateTime created) {
	this.created = created;
    }
    
    public LocalDateTime getModified() {
	return modified;
    }
    
    public void setModified(LocalDateTime modified) {
	this.modified = modified;
    }
    
    public LocalDateTime getLast_login() {
	return last_login;
    }
    
    public void setLast_login(LocalDateTime last_login) {
	this.last_login = last_login;
    }
    
    public String getToken() {
	return token;
    }
    
    public void setToken(String token) {
	this.token = token;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }
    
}
