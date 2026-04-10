package at.spengergasse.spring_thymeleaf.repository;

import at.spengergasse.spring_thymeleaf.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByDeviceDesignation(String designation);
}
