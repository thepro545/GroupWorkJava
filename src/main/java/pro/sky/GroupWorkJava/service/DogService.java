package pro.sky.GroupWorkJava.service;

import org.springframework.stereotype.Service;
import pro.sky.GroupWorkJava.model.Dog;
import pro.sky.GroupWorkJava.repository.DogRepository;

@Service
public class DogService {

    private final DogRepository repository;

    public DogService(DogRepository dogRepository) {
        this.repository = dogRepository;
    }

    public Dog getById(Long id) {
        return repository.getById(id);
    }

    public Dog create(Dog dog) {
        return repository.save(dog);
    }

    public void removeById(Long id) {
        repository.deleteById(id);
    }
}