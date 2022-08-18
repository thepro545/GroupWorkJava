package pro.sky.GroupWorkJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.GroupWorkJava.model.PersonCat;

import java.util.Set;

/**
 * @author Maxon4ik
 * @date 18.08.2022 20:23
 */
public interface PersonCatRepository extends JpaRepository<PersonCat, Long> {
    Set<PersonCat> findByChatId(Long chatId);

}
