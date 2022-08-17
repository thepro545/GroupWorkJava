package pro.sky.GroupWorkJava.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pro.sky.GroupWorkJava.model.Cat;
import pro.sky.GroupWorkJava.service.CatService;

@RestController
@RequestMapping("cat")
public class CatController {

    private final CatService service;


    public CatController(CatService service) {
        this.service = service;
    }

    @GetMapping("give")
    public Cat give(){
        Cat c = new Cat();
        c.setId(1L);
        c.setBreed("bread");
        c.setName("name");
        c.setYearOfBirth(2020);
        c.setDescription("description");
        return c;
    }

    @GetMapping("{id}")
    public Cat getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Cat save(Cat cat) {
        return service.create(cat);
    }

    @DeleteMapping("{id}")
    public void remove(@PathVariable Long id) {
        service.removeById(id);
    }
}
