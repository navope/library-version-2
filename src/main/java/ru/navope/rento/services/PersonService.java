package ru.navope.rento.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.navope.rento.models.Book;
import ru.navope.rento.models.Person;
import ru.navope.rento.repositories.PersonRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPeople(){
        return personRepository.findAll();
    }

    public Person getPerson(int id){
        return personRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<Book> getPersonBooks(int id){
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()){
            Hibernate.initialize(person.get().getBooks());
            return person.get().getBooks();
        }
        return Collections.emptyList();
    }

    public Optional<Person> getPerson(String fullName){
        return personRepository.findByFullName(fullName);
    }

    @Transactional
    public void save(Person person){
        personRepository.save(person);
    }

    @Transactional
    public void update(Person updatePerson, int id){
        updatePerson.setId(id);
        personRepository.save(updatePerson);
    }

    @Transactional
    public void delete(int id){
        personRepository.deleteById(id);
    }

}
