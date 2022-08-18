package pro.sky.GroupWorkJava.service;

import org.springframework.stereotype.Service;
import pro.sky.GroupWorkJava.model.Person;
import pro.sky.GroupWorkJava.model.ReportData;
import pro.sky.GroupWorkJava.repository.PersonRepository;

import java.util.Collection;

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

    public Collection<Person> getAll() {
        return repository.findAll();
    }

    public Collection<Person> getByChatId(Long chatId){
        return repository.findByChatId(chatId);
    }
}
