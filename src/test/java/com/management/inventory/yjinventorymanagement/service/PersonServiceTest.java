package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.domain.Person;
import com.management.inventory.yjinventorymanagement.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class PersonServiceTest {

    @Autowired
    PersonService personService;
    @Autowired
    PersonRepository personRepository;

    @Test
    void register() {
        Person person = new Person("John", "Doe", "johndoe@gmail.com");
        personService.register(person);

        Person personFound = personRepository.findByEmail(person.getEmail()).get();

        assertThat(person.getEmail()).isSameAs(personFound.getEmail());
    }
}