package com.mush.spiceShop.dto;

import com.mush.spiceShop.domain.Person;
import lombok.Data;

import java.io.Serializable;

@Data
public class PersonOutputDTO implements Serializable {
    Long id;
    String name;
    String phone;
    String address;
    String description;
    String type;
    public PersonOutputDTO(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.phone=person.getPhone();
        this.address=person.getAddress();
        this.description = person.getDescription();
        this.type= person.getType();
    }
}
