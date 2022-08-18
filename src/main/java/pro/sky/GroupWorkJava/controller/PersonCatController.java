package pro.sky.GroupWorkJava.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.GroupWorkJava.model.PersonCat;
import pro.sky.GroupWorkJava.service.PersonCatService;

import java.util.Collection;

/**
 * @author Maxon4ik
 * @date 18.08.2022 20:26
 */
@RestController
@RequestMapping("personCat")
public class PersonCatController {

    private final PersonCatService service;

    public PersonCatController(PersonCatService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public PersonCat getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping()
    public void save(@RequestBody PersonCat personDog) {
        service.create(personDog);
    }

    @PutMapping
    public ResponseEntity<PersonCat> updateFaculty(@RequestBody PersonCat personDog) {
        PersonCat personCatDelete = service.create(personDog);
        if (personCatDelete == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(personCatDelete);
    }

    @DeleteMapping("{id}")
    public void remove(@PathVariable Long id) {
        service.removeById(id);
    }

    @GetMapping("getAll")
    public ResponseEntity<Collection<PersonCat>> getAll(@RequestParam(required = false) Long chatId) {
        if (chatId != null) {
            return ResponseEntity.ok(service.getByChatId(chatId));
        }
        return ResponseEntity.ok(service.getAll());
    }
}
