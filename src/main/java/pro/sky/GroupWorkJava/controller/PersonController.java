package pro.sky.GroupWorkJava.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.GroupWorkJava.model.Person;
import pro.sky.GroupWorkJava.service.PersonService;

@RestController
@RequestMapping("person")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public Person getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping()
    public void save(@RequestBody Person person) {
        service.create(person);
    }

    @DeleteMapping("{id}")
    public void remove(@PathVariable Long id) {
        service.removeById(id);
    }
}
