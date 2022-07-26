package pro.sky.GroupWorkJava.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity

public class Person {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private int yearOfBirth;
    private int phone;
    private String mail;
    private String address;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dog_id")
    private Dog dog;

    public Person(){

    }

    public Person(long id, String name, int yearOfBirth, int phone, String mail, String address) {
        this.id = id;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.phone = phone;
        this.mail = mail;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && yearOfBirth == person.yearOfBirth && phone == person.phone && Objects.equals(name, person.name) && Objects.equals(mail, person.mail) && Objects.equals(address, person.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, yearOfBirth, phone, mail, address);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", phone=" + phone +
                ", mail='" + mail + '\'' +
                ", address='" + address + '\'' +
                ", dog=" + dog +
                '}';
    }
}
