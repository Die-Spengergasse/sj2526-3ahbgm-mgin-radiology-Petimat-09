package at.spengergasse.spring_thymeleaf.repository;
import at.spengergasse.spring_thymeleaf.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,String> {
}
