package pro.sky.GroupWorkJava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.GroupWorkJava.exceptions.DogNotFoundException;
import pro.sky.GroupWorkJava.model.Dog;
import pro.sky.GroupWorkJava.repository.DogRepository;

import java.util.Collection;

@Service
public class DogService {

    private final DogRepository repository;

    private final Logger logger = LoggerFactory.getLogger(DogService.class);

    public DogService(DogRepository dogRepository) {
        this.repository = dogRepository;
    }

    public Dog getById(Long id) {
        logger.info("Was invoked method to get a dog by id={}", id);
        return repository.findById(id).orElseThrow(DogNotFoundException::new);
    }

    public Dog create(Dog dog) {
        logger.info("Was invoked method to create a dog");
        return repository.save(dog);
    }

    public Dog update(Dog dog) {
        logger.info("Was invoked method to update a dog");
        if (dog.getId() != null) {
            if (getById(dog.getId()) != null) {
                return repository.save(dog);
            }
        }
        throw new DogNotFoundException();
    }

    public void removeById(Long id) {
        logger.info("Was invoked method to remove a cat by id={}", id);
        repository.deleteById(id);
    }

    public Collection<Dog> getAll() {
        logger.info("Was invoked method to get all dogs");
        return repository.findAll();
    }
}
