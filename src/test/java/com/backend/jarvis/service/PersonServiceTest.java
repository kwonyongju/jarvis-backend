package com.backend.jarvis.service;

import com.backend.jarvis.domain.Person;
import com.backend.jarvis.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
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