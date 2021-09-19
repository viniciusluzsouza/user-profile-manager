package br.com.vini.userprofile.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.vini.userprofile.model.Phone;
import br.com.vini.userprofile.model.UserProfile;

public class UserProfileForm {
    
    @NotNull @NotEmpty
    private String name;
    
    @NotNull @NotEmpty
    private String email;
    
    @NotNull @NotEmpty
    private String password;

    private List<Phone> phones;

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
    
    public UserProfile getUserProfile() {
	return new UserProfile(name, email, password, phones);
    }
    
}
