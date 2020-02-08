package com.ahamka.springcrudmysql.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 
 * The following annotations are from project Lombok and help us keep our
 * classes (specially model/POJO) cleaner without the getters and setters:
 * AllArgsConstructor: automatically creates a class construtor with all arguments (properties). 
 * NoArgsConstructor: automatically creates an empty class construtor with all arguments (properties). 
 * Entity: specifies that the class is an entity and is mapped to a database table
 * JsonPropertyOrder: 
 */

@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonPropertyOrder({ "id", "name", "email", "phone" })
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    private String name;
    private String email;
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }
}
