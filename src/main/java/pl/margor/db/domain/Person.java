package pl.margor.db.domain;

import java.util.UUID;

/*
 * @author margor
 */
public class Person {

    private final UUID id;
    private final String name;
    private final String surname;

    public Person(UUID id, String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.id = id;
    }

    public UUID getUID() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }
}
