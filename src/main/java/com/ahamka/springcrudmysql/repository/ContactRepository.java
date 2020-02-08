package com.ahamka.springcrudmysql.repository;

import java.util.Optional;

import com.ahamka.springcrudmysql.model.Contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    
    
    Optional<Contact> findByEmail(String email); 
}
