package br.com.vini.userprofile.messages.dto;

import br.com.vini.userprofile.model.Phone;

public class PhoneDto {
    private String number;
    private String ddd;
    
    public PhoneDto(Phone phone) {
	this.number = phone.getNumber();
	this.ddd = phone.getDdd();
    }
    
    public String getPhone() {
	return number;
    }
    
    public void setPhone(String number) {
	this.number = number;
    }
    
    public String getDdd() {
	return ddd;
    }
    
    public void setDdd(String ddd) {
	this.ddd = ddd;
    }
    
}
