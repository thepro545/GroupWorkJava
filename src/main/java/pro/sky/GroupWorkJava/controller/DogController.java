package pro.sky.GroupWorkJava.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.GroupWorkJava.model.Dog;
import pro.sky.GroupWorkJava.service.DogService;

import java.util.Collection;

@RestController
@RequestMapping("dog")
public class DogController {

    private final DogService service;

    public DogController(DogService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public Dog getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping()
    public Dog save(@RequestBody Dog dog) {
        return service.create(dog);
    }

    @PutMapping()
    public Dog update(@RequestBody Dog dog) {
        return service.update(dog);
    }

    @DeleteMapping("{id}")
    public void remove(@PathVariable Long id) {
        service.removeById(id);
    }

    @GetMapping("all")
    public Collection<Dog> getAll() {
        return service.getAll();
    }
}
