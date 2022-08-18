package pro.sky.GroupWorkJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.GroupWorkJava.model.Person;

import java.util.Set;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Set<Person> findByChatId(Long chatId);

}
