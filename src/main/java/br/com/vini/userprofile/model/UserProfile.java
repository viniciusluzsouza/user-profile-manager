package br.com.vini.userprofile.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class UserProfile {
    
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    
    @Column(unique = true)
    private String email;
    private String password;
    
    @OneToMany(mappedBy = "userProfile")
    private List<Phone> phones = new ArrayList<>();
    
    private String token;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    
    public UserProfile() {
	super();
    }
    
    public UserProfile(String name, String email, String password, List<Phone> phones) {
	super();
	this.name = name;
	this.email = email;
	this.password = password;
	this.phones = phones;
	this.created = LocalDateTime.now();
	this.modified = this.created;
    }
    
    public UUID getId() {
	return id;
    }
    
    public void setId(UUID id) {
	this.id = id;
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
    
    public List<Phone> getPhones() {
	return phones;
    }
    
    public void setPhones(List<Phone> phones) {
	this.phones = phones;
    }
    
    public String getToken() {
	return token;
    }
    
    public void setToken(String token) {
	this.token = token;
    }
    
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((password == null) ? 0 : password.hashCode());
	result = prime * result + ((phones == null) ? 0 : phones.hashCode());
	return result;
    }
    
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	UserProfile other = (UserProfile) obj;
	if (email == null) {
	    if (other.email != null)
		return false;
	} else if (!email.equals(other.email))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (password == null) {
	    if (other.password != null)
		return false;
	} else if (!password.equals(other.password))
	    return false;
	if (phones == null) {
	    if (other.phones != null)
		return false;
	} else if (!phones.equals(other.phones))
	    return false;
	return true;
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
    
    public LocalDateTime getLastLogin() {
	return lastLogin;
    }
    
    public void setLastLogin(LocalDateTime last_login) {
	this.lastLogin = last_login;
    }
    
    // public void encryptPassword(BCryptPasswordEncoder bCryptPasswordEncoder) {
    // this.password = bCryptPasswordEncoder.encode(password);
    // }
    
}
