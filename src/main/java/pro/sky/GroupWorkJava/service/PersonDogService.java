package pro.sky.GroupWorkJava.service;

import org.springframework.stereotype.Service;
import pro.sky.GroupWorkJava.model.PersonDog;
import pro.sky.GroupWorkJava.repository.PersonDogRepository;

import java.util.Collection;

@Service
public class PersonDogService {

    private final PersonDogRepository repository;

    public PersonDogService(PersonDogRepository repository) {
        this.repository = repository;
    }

    public PersonDog getById(Long id) {
        return repository.getById(id);
    }

    public PersonDog create(PersonDog personDog) {
        return repository.save(personDog);
    }

    public void removeById(Long id) {
        repository.deleteById(id);
    }

    public Collection<PersonDog> getAll() {
        return repository.findAll();
    }

    public Collection<PersonDog> getByChatId(Long chatId){
        return repository.findByChatId(chatId);
    }
}
