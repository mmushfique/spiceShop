package com.mush.spiceShop.controller;

import com.mush.spiceShop.domain.Person;
import com.mush.spiceShop.dto.PersonOutputDTO;
import com.mush.spiceShop.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person){
        return ResponseEntity.ok(personService.save(person));
    }

    @GetMapping("/{id}")
    public PersonOutputDTO getPersonById(@PathVariable("id") Long personId){
        return new PersonOutputDTO(personService.getPersonById(personId));
    }

    @GetMapping("/buyer")
    public List<Person> getAllPersonsBuyer(){
        return personService.getAllPersonsBuyer();
    }

    @GetMapping("/supplier")
    public List<Person> getAllPersonsSupplier(){
        return personService.getAllPersonsSupplier();
    }

    @PutMapping
    public ResponseEntity<Person> updatePerson(@RequestBody Person person){
        return ResponseEntity.ok(personService.save(person));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonById(@PathVariable("id") Long personId){
        personService.deletePersonById(personId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}