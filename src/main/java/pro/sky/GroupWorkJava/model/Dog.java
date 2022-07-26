package pro.sky.GroupWorkJava.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Dog {
    @Id
    @GeneratedValue
    private long id;
    private String breed;
    private String name;
    private int yearOfBirth;
    private String description;

    @JsonManagedReference
    @OneToOne(mappedBy = "dog")
    private Person persons;

    public Dog(){

    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", description='" + description + '\'' +
                ", persons=" + persons +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return id == dog.id && yearOfBirth == dog.yearOfBirth && Objects.equals(breed, dog.breed) && Objects.equals(name, dog.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, breed, name, yearOfBirth);
    }
}
