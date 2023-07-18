package ru.navope.rento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.navope.rento.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
