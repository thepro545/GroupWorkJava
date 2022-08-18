package pro.sky.GroupWorkJava.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.GroupWorkJava.model.PersonDog;
import pro.sky.GroupWorkJava.service.PersonDogService;

import java.util.Collection;

@RestController
@RequestMapping("personDog")
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
    public void save(@RequestBody PersonDog personDog) {
        service.create(personDog);
    }

    @PutMapping
    public ResponseEntity<PersonDog> update(@RequestBody PersonDog personDog) {
        PersonDog personDogDelete = service.create(personDog);
        if (personDogDelete == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(personDogDelete);
    }
    @DeleteMapping("{id}")
    public void remove(@PathVariable Long id) {
        service.removeById(id);
    }

    @GetMapping("getAll")
    public ResponseEntity<Collection<PersonDog>> getAll(@RequestParam(required = false) Long chatId) {
            if (chatId != null){
                return ResponseEntity.ok(service.getByChatId(chatId));
            }
            return ResponseEntity.ok(service.getAll());
    }
}
