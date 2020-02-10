package com.ahamka.springcrudmysql.service;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.ahamka.springcrudmysql.dao.ContactDao;
import com.ahamka.springcrudmysql.model.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class ContactServiceImpl implements ContactService {
    
    @Autowired
    private ContactDao contactDao;

    @Override
    public List<Contact> findAll() {
        return contactDao.findAll();
    }

    @Override
    public Optional<Contact> findById(@NotBlank long id) {        
        return contactDao.findById(id);
    }

    @Override
    public Optional<Contact> findByEmail(@NotBlank String email) {
        return contactDao.findByEmail(email);
    }

    @Override
    public Contact saveContact(@NotNull Contact contact) {
        return contactDao.save(contact);
    }


    @Override
    public Boolean deleteContactByEmail(@NotBlank String email) {

        Optional<Contact> contact = contactDao.findByEmail(email);
        
        if(!contact.isEmpty()){
            contactDao.deleteById(contact.get().getId());
            return true;
        }
        return false;

    }

    
}

