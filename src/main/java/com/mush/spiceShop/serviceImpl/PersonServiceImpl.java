package com.mush.spiceShop.serviceImpl;


import com.mush.spiceShop.domain.Person;
import com.mush.spiceShop.repository.PersonRepository;
import com.mush.spiceShop.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }
    @Override
    public Person getPersonById(Long personId){
        return personRepository.getById(personId);
    }

    @Override
    public List<Person> getAllPersonsBuyer() {
        return personRepository.findAllByType("BUYER");
    }

    @Override
    public List<Person> getAllPersonsSupplier() {
        return personRepository.findAllByType("SUPPLIER");
    }

    @Override
    public void deletePersonById(Long personId) {
        personRepository.deleteById(personId);
    }
}
