package pro.sky.GroupWorkJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.GroupWorkJava.model.Dog;

public interface DogRepository extends JpaRepository<Dog, Long> {
}
