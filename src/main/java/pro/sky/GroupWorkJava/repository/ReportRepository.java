package pro.sky.GroupWorkJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.GroupWorkJava.model.ReportData;


public interface ReportRepository extends JpaRepository<ReportData, Long> {
}
