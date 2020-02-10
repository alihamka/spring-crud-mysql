package com.ahamka.springcrudmysql.service;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.ahamka.springcrudmysql.model.Contact;

import javassist.NotFoundException;


public interface ContactService {
      
    
    public List<Contact> findAll();
    public Optional<Contact> findById(@NotBlank long id) throws NotFoundException;
    public Optional<Contact> findByEmail(@NotBlank String email);

    public Contact saveContact(@NotNull Contact contact);

    public Boolean deleteContactByEmail(@NotBlank String email);

}
