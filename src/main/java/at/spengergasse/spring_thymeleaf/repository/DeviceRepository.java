package at.spengergasse.spring_thymeleaf.repository;

import at.spengergasse.spring_thymeleaf.entities.Device;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, String> {
}
