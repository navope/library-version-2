package ru.navope.rento.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.navope.rento.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    public List<Person> getPeople(){
        return null;
    }

    public Person getPerson(int id){
        return null;
    }

    public Optional<Person> getPerson(String fullName){
        return Optional.empty();
    }

    public void save(Person person){
    }

    public void update(Person updatePerson, int id){
    }

    public void delete(int id){

    }

}
