package pro.sky.GroupWorkJava.service;

import org.springframework.stereotype.Service;
import pro.sky.GroupWorkJava.model.PersonCat;
import pro.sky.GroupWorkJava.model.PersonDog;
import pro.sky.GroupWorkJava.repository.PersonCatRepository;
import pro.sky.GroupWorkJava.repository.PersonDogRepository;

import java.util.Collection;

/**
 * @author Maxon4ik
 * @date 18.08.2022 20:24
 */
@Service
public class PersonCatService {
    private final PersonCatRepository repository;

    public PersonCatService(PersonCatRepository repository) {
        this.repository = repository;
    }

    public PersonCat getById(Long id) {
        return repository.getById(id);
    }

    public PersonCat create(PersonCat personCat) {
        return repository.save(personCat);
    }

    public void removeById(Long id) {
        repository.deleteById(id);
    }

    public Collection<PersonCat> getAll() {
        return repository.findAll();
    }

    public Collection<PersonCat> getByChatId(Long chatId){
        return repository.findByChatId(chatId);
    }
}
