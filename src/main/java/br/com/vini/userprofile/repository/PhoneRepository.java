package br.com.vini.userprofile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vini.userprofile.model.Phone;

public interface PhoneRepository extends JpaRepository<Phone, String> {
    
}
