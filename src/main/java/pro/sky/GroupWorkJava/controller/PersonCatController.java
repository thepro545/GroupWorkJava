package pro.sky.GroupWorkJava.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.GroupWorkJava.model.PersonCat;
import pro.sky.GroupWorkJava.service.PersonCatService;

import java.util.Collection;

/**
 * @author Maxon4ik
 * @date 18.08.2022 20:26
 */
@RestController
@RequestMapping("person-cat")
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
    public PersonCat save(@RequestBody PersonCat personCat) {
        return service.create(personCat);
    }

    @PutMapping
    public PersonCat update(@RequestBody PersonCat personCat) {
        return service.update(personCat);
    }

    @DeleteMapping("{id}")
    public void remove(@PathVariable Long id) {
        service.removeById(id);
    }

    @GetMapping("all")
    public Collection<PersonCat> getAll(@RequestParam(required = false) Long chatId) {
        if (chatId != null) {
            return service.getByChatId(chatId);
        }
        return service.getAll();
    }
}
