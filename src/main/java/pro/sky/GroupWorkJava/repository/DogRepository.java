package pro.sky.GroupWorkJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.sky.GroupWorkJava.model.Dog;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {

//    @Modifying
//    @Query("update Dog d set d.breed = :breed, d.name = :name, d.yearOfBirth = :yearOfBirth, d.description = :description where d.id = :id")
//    void update(@Param(value = "id") long id, @Param(value = "bread") String breed,
//                @Param(value = "description") String description, @Param(value = "yearOfBirth") int yearOfBirth);
}
