package pro.sky.GroupWorkJava.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.GroupWorkJava.model.PersonDog;
import pro.sky.GroupWorkJava.service.PersonDogService;

import java.util.Collection;

@RestController
@RequestMapping("person-dog")
public class PersonDogController {

    private final PersonDogService service;

    public PersonDogController(PersonDogService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public PersonDog getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public PersonDog save(@RequestBody PersonDog personDog) {
        return service.create(personDog);
    }

    @PutMapping
    public PersonDog update(@RequestBody PersonDog personDog) {
        return service.update(personDog);
    }

    @DeleteMapping("{id}")
    public void remove(@PathVariable Long id) {
        service.removeById(id);
    }

    @GetMapping("all")
    public Collection<PersonDog> getAll(@RequestParam(required = false) Long chatId) {
        if (chatId != null) {
            return service.getByChatId(chatId);
        }
        return service.getAll();
    }
}
