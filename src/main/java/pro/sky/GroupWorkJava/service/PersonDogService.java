package pro.sky.GroupWorkJava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.GroupWorkJava.exceptions.PersonDogNotFoundException;
import pro.sky.GroupWorkJava.model.PersonDog;
import pro.sky.GroupWorkJava.repository.PersonDogRepository;

import java.util.Collection;

@Service
public class PersonDogService {

    private final PersonDogRepository repository;

    private final Logger logger = LoggerFactory.getLogger(PersonDogService.class);

    public PersonDogService(PersonDogRepository repository) {
        this.repository = repository;
    }

    public PersonDog getById(Long id) {
        logger.info("Was invoked method to get a personDog by id={}", id);
        return repository.findById(id).orElseThrow(PersonDogNotFoundException::new);
    }

    public PersonDog create(PersonDog personDog) {
        logger.info("Was invoked method to create a personDog");
        return repository.save(personDog);
    }

    public PersonDog update(PersonDog personDog) {
        logger.info("Was invoked method to update a personDog");
        if (personDog.getId() != null) {
            if (getById(personDog.getId()) != null) {
                return repository.save(personDog);
            }
        }
        throw new PersonDogNotFoundException();
    }

    public void removeById(Long id) {
        logger.info("Was invoked method to remove a personDog by id={}", id);
        repository.deleteById(id);
    }

    public Collection<PersonDog> getAll() {
        logger.info("Was invoked method to get all personsDod");
        return repository.findAll();
    }

    public Collection<PersonDog> getByChatId(Long chatId) {
        logger.info("Was invoked method to remove a personsDog by chatId={}", chatId);
        return repository.findByChatId(chatId);
    }
}
