package com.backend.jarvis.service;

import com.backend.jarvis.domain.Person;
import com.backend.jarvis.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional(readOnly = true)
public class PersonService {

    private final long FAIL = -1L;

    private final PersonRepository personRepository;

    @Transactional
    public Long register(Person person) {
        log.info("[PERSON SERVICE] register");
        //check duplicate person
        if (validateDuplicateEmail(person)) {
            personRepository.save(person);
            return person.getId();
        }
        return FAIL;
    }

    private boolean validateDuplicateEmail(Person person) {
        Optional<Person> data = personRepository.findByEmail(person.getEmail());

        if (data.isEmpty()) return true;
        else
            throw new IllegalStateException("The email was already registered");
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(Long id) {
        Optional<Person> data = personRepository.findById(id);

        if (data.isPresent())
            return data.get();

        throw new IllegalStateException("No member with the id exists");
    }


}
