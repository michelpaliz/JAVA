package Testing.Objects.person;

import Tema11.Propios.ejercicio05.models.objNames;

public class Person {

    // Attributes
    private String ID;
    private String name;
    private String lastName;

    public Person() {
        this.ID = null;
        this.name = null;
        this.lastName = null;
    }

    public Person(String ID, String name, String lastName) {
        this.ID = ID;
        this.name = name;
        this.lastName = lastName;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Person [ID=" + ID + ", lastName=" + lastName + ", name=" + name + "]";
    }

    @Override
    public boolean equals(Object persona) {
        if (this == persona)
            return true;
        if (persona == null || this.getClass() != persona.getClass())
            return false;
        Person p = (Person) persona;
        return this.ID.equals(p.ID);

    }
}
