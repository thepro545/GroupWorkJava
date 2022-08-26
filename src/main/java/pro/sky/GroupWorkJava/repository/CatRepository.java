package pro.sky.GroupWorkJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.GroupWorkJava.model.Cat;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
}
