package pro.sky.GroupWorkJava.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.GroupWorkJava.model.Person;
import pro.sky.GroupWorkJava.model.ReportData;
import pro.sky.GroupWorkJava.service.PersonService;

import java.util.Collection;

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

    @PutMapping
    public ResponseEntity<Person> updateFaculty(@RequestBody Person person) {
        Person personDelete = service.create(person);
        if (personDelete == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(personDelete);
    }
    @DeleteMapping("{id}")
    public void remove(@PathVariable Long id) {
        service.removeById(id);
    }

    @GetMapping("getAll")
    public ResponseEntity<Collection<Person>> getAll(@RequestParam(required = false) Long chatId) {
            if (chatId != null){
                return ResponseEntity.ok(service.getByChatId(chatId));
            }
            return ResponseEntity.ok(service.getAll());
    }
}
