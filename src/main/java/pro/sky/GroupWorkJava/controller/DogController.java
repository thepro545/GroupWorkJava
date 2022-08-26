package pro.sky.GroupWorkJava.controller;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Получение собаки по id")
    @GetMapping("{id}")
    public Dog getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @Operation(summary = "Создание собаки")
    @PostMapping()
    public Dog save(@RequestBody Dog dog) {
        return service.create(dog);
    }

    @Operation(summary = "Изменение данных у собаки")
    @PutMapping()
    public Dog update(@RequestBody Dog dog) {
        return service.update(dog);
    }

    @Operation(summary = "Удаленеи собаки по id")
    @DeleteMapping("{id}")
    public void remove(@PathVariable Long id) {
        service.removeById(id);
    }

    @Operation(summary = "Просмотр всех собак")
    @GetMapping("all")
    public Collection<Dog> getAll() {
        return service.getAll();
    }
}
