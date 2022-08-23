package pro.sky.GroupWorkJava.controller;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Получение пользователя по id")
    @GetMapping("{id}")
    public PersonCat getById(@PathVariable Long id) {
        return service.getById(id);
    }
    @Operation(summary = "Создание пользователя")
    @PostMapping()
    public PersonCat save(@RequestBody PersonCat personCat) {
        return service.create(personCat);
    }

    @Operation(summary = "Изменение данных пользователя")
    @PutMapping
    public PersonCat update(@RequestBody PersonCat personCat) {
        return service.update(personCat);
    }
    @Operation(summary = "Удаление пользователей по id")
    @DeleteMapping("{id}")
    public void remove(@PathVariable Long id) {
        service.removeById(id);
    }

    @Operation(summary = "Просмотр всех пользователей", description = "Просмотр всех пользователей, либо определенного пользователя по chat_id")
    @GetMapping("all")
    public Collection<PersonCat> getAll(@RequestParam(required = false) Long chatId) {
        if (chatId != null) {
            return service.getByChatId(chatId);
        }
        return service.getAll();
    }
}
