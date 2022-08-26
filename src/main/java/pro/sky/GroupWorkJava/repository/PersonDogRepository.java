package pro.sky.GroupWorkJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.GroupWorkJava.model.PersonDog;

import java.util.Set;

@Repository
public interface PersonDogRepository extends JpaRepository<PersonDog, Long> {
    Set<PersonDog> findByChatId(Long chatId);

}
