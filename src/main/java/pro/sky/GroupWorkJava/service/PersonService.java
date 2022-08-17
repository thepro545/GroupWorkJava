package pro.sky.GroupWorkJava.service;

import org.springframework.stereotype.Service;
import pro.sky.GroupWorkJava.model.Person;
import pro.sky.GroupWorkJava.repository.PersonRepository;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Person getById(Long id) {
        return repository.getById(id);
    }

    public Person create(Person person) {
        return repository.save(person);
    }

    public void removeById(Long id) {
        repository.deleteById(id);
    }
}
