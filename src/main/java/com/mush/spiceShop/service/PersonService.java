package com.mush.spiceShop.service;

import com.mush.spiceShop.domain.Person;

import java.util.List;

public interface PersonService {
    public Person save(Person person);
    public Person getPersonById(Long personId) ;
    public List<Person> getAllPersonsBuyer();
    public List<Person> getAllPersonsSupplier();
    public void deletePersonById(Long personId);

}
