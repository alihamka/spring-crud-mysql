package com.ahamka.springcrudmysql.controller;

import java.util.List;

import com.ahamka.springcrudmysql.model.Contact;
import com.ahamka.springcrudmysql.service.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

@RestController
@RequestMapping({ "/contacts" })
public class ContactController {

    @Autowired
    private ContactService contactService;


    // CRUD methods
    @GetMapping
    public List<?> findAll() {
        return contactService.findAll();
    }

    @GetMapping(path = { "/{id}" })
    public ResponseEntity<Contact> findById(@PathVariable long id) throws NotFoundException {
        return contactService.findById(id).map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = { "/email/{email}" })
    public ResponseEntity<Contact> findByEmail(@PathVariable String email) {
        return contactService.findByEmail(email).map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Contact create(@RequestBody Contact contact) {
        return contactService.saveContact(contact);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Contact> update(@PathVariable("id") long id, @RequestBody Contact contact)
            throws NotFoundException {
        return contactService.findById(id).map(record -> {
            record.setName(contact.getName());
            record.setEmail(contact.getEmail());
            record.setPhone(contact.getPhone());
            Contact updated = contactService.saveContact(record);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/email/{email}")
    public ResponseEntity<Contact> updateByEmail(@PathVariable String email, @RequestBody Contact contact)
            throws NotFoundException {
        return contactService.findByEmail(email).map(record -> {
            record.setName(contact.getName());
            record.setEmail(contact.getEmail());
            record.setPhone(contact.getPhone());
            Contact updated = contactService.saveContact(record);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = { "/email/{email}" })
    public ResponseEntity<?> deleteByEmail(@PathVariable String email) {
        
        Boolean result = contactService.deleteContactByEmail(email);
        if(result){
            return ResponseEntity.ok().body("deleted");
        }

        return ResponseEntity.notFound().build();
    }

}
