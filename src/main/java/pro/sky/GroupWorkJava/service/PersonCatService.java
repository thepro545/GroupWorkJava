package pro.sky.GroupWorkJava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.GroupWorkJava.exceptions.PersonCatNotFoundException;
import pro.sky.GroupWorkJava.model.PersonCat;
import pro.sky.GroupWorkJava.repository.PersonCatRepository;

import java.util.Collection;

/**
 * @author Maxon4ik
 * @date 18.08.2022 20:24
 */
@Service
public class PersonCatService {
    private final PersonCatRepository repository;

    private final Logger logger = LoggerFactory.getLogger(PersonCatService.class);

    public PersonCatService(PersonCatRepository repository) {
        this.repository = repository;
    }

    public PersonCat getById(Long id) {
        logger.info("Was invoked method to get a personCat by id={}", id);
        return repository.findById(id).orElseThrow(PersonCatNotFoundException::new);
    }

    public PersonCat create(PersonCat personCat) {
        logger.info("Was invoked method to create a personCat");
        return repository.save(personCat);
    }

    public PersonCat update(PersonCat personCat) {
        logger.info("Was invoked method to update a personCat");
        if (personCat.getId() != null) {
            if (getById(personCat.getId()) != null) {
                return repository.save(personCat);
            }
        }
        throw new PersonCatNotFoundException();
    }

    public void removeById(Long id) {
        logger.info("Was invoked method to remove a personCat by id={}", id);
        repository.deleteById(id);
    }

    public Collection<PersonCat> getAll() {
        logger.info("Was invoked method to get all personsCat");
        return repository.findAll();
    }

    public Collection<PersonCat> getByChatId(Long chatId) {
        logger.info("Was invoked method to remove a personsCat by chatId={}", chatId);
        return repository.findByChatId(chatId);
    }
}
