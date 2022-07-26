package pro.sky.GroupWorkJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.GroupWorkJava.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
