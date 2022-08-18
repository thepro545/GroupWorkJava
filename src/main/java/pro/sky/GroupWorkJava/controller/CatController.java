package pro.sky.GroupWorkJava.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.GroupWorkJava.model.Cat;
import pro.sky.GroupWorkJava.model.ReportData;
import pro.sky.GroupWorkJava.service.CatService;

import java.util.Collection;

@RestController
@RequestMapping("cat")
public class CatController {

    private final CatService service;

    public CatController(CatService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public Cat getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping()
    public Cat save(@RequestBody Cat cat) {
        return service.create(cat);
    }

    @DeleteMapping("{id}")
    public void remove(@PathVariable Long id) {
        service.removeById(id);
    }

    @GetMapping("getAll")
    public ResponseEntity<Collection<Cat>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
