package at.spengergasse.spring_thymeleaf.repository;

import at.spengergasse.spring_thymeleaf.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByDeviceDesignation(String designation);
    @Query("SELECT r FROM Reservation r WHERE r.device.designation = :designation AND r.reservationTime = :time")
    List<Reservation> findByDeviceDAndReservation(@Param("designation") String designation, @Param("time") LocalDateTime time);

    @Query("SELECT r FROM Reservation r WHERE r.patient.svn = :svn AND r.reservationTime = :time")
    List<Reservation> findByPatientSvnAndReservation(@Param("svn") String svn, @Param("time") LocalDateTime time);
}