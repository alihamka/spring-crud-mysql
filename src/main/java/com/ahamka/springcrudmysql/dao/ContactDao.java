package com.ahamka.springcrudmysql.dao;

import java.util.Optional;

import com.ahamka.springcrudmysql.model.Contact;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactDao extends JpaRepository<Contact, Long> {
      
    Optional<Contact> findByEmail(String email);
}
