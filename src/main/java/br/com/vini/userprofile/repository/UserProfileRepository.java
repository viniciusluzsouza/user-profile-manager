package br.com.vini.userprofile.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vini.userprofile.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, String> {
    
    UserProfile findByEmail(String email);
    
    UserProfile findById(UUID id);
    
}
