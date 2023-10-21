package com.mush.spiceShop.repository;

import com.mush.spiceShop.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {
    List<Person> findAllByType(String buyer);

    int countAllPersonByType(String type);
}
