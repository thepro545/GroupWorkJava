package pro.sky.GroupWorkJava.service;

import org.springframework.stereotype.Service;
import pro.sky.GroupWorkJava.model.Cat;
import pro.sky.GroupWorkJava.repository.CatRepository;

/**
 * @author Maxon4ik
 * @date 16.08.2022 13:13
 */

@Service
public class CatService {

    private final CatRepository repository;

    public CatService(CatRepository repository) {
        this.repository = repository;
    }

    public Cat getById(Long id) {
        return repository.getById(id);
    }

    public Cat create(Cat cat) {
        return repository.save(cat);
    }

    public void removeById(Long id) {
        repository.deleteById(id);
    }
}
