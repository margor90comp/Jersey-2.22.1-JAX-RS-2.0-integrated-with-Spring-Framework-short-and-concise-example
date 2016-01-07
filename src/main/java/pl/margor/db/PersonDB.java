package pl.margor.db;

import org.springframework.stereotype.Component;
import pl.margor.dto.InputPerson;
import pl.margor.db.domain.Person;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


@Component
public class PersonDB {

    private final ConcurrentHashMap<UUID, Person> personMap = new ConcurrentHashMap<>();

    @PostConstruct
    private void init() {
        Person first = new Person(UUID.randomUUID(), "John", "Smith");
        personMap.put(first.getUID(), first);
        Person second = new Person(UUID.randomUUID(), "Adrian", "Brown");
        personMap.put(second.getUID(), second);
    }


    public UUID create(InputPerson newPer) {
        Person person = new Person(UUID.randomUUID(), newPer.getName(), newPer.getSurname());
        personMap.put(person.getUID(), person);
        return person.getUID();
    }

    public List<Person> findAllBySurname(String surname) {
        return personMap.entrySet()
                .stream()
                .map(key -> key.getValue())
                .filter(person -> person.getSurname().contains(surname))
                .sorted((e1, e2) -> e1.getSurname().compareTo(e2.getSurname()))
                .sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Person> findAll() {
        return personMap.entrySet()
                .stream()
                .map(key -> key.getValue())
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
