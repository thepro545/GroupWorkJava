package pro.sky.GroupWorkJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.GroupWorkJava.model.ReportData;

@Repository
public interface ReportDataRepository extends JpaRepository<ReportData, Long> {

    ReportData findByChatId(Long id);
}
