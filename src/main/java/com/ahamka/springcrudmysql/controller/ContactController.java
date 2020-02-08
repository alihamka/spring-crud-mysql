package com.ahamka.springcrudmysql.controller;

import java.util.List;
import java.util.Optional;

import com.ahamka.springcrudmysql.model.Contact;
import com.ahamka.springcrudmysql.repository.ContactRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/contacts" })
public class ContactController {

    private ContactRepository contactRepository;

    ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    // CRUD methods
    @GetMapping
    public List<?> findAll() {
        return contactRepository.findAll();
    }

    @GetMapping(path = { "/{id}" })
    public ResponseEntity<Contact> findById(@PathVariable long id) {
        return contactRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = { "/email/{email}" })
    public ResponseEntity<Contact> findByEmail(@PathVariable String email) {
        return contactRepository.findByEmail(email).map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Contact create(@RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Contact> update(@PathVariable("id") long id, @RequestBody Contact contact) {
        return contactRepository.findById(id).map(record -> {
            record.setName(contact.getName());
            record.setEmail(contact.getEmail());
            record.setPhone(contact.getPhone());
            Contact updated = contactRepository.save(record);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = { "/email/{email}" })
    public void deleteByEmail(@PathVariable String email) {
        Optional<Contact> contact = contactRepository.findByEmail(email);
        
        if(!contact.isEmpty()){
            contactRepository.deleteById(contact.get().getId());
        }
    }

}
