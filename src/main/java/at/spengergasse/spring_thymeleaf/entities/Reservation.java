package at.spengergasse.spring_thymeleaf.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name= "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_svn")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name= "device_designation")
    private Device device;

    private LocalDateTime reservationTime;

    private String bodyRegion;
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public String getBodyRegion() {
        return bodyRegion;
    }

    public void setBodyRegion(String bodyRegion) {
        this.bodyRegion = bodyRegion;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Reservation(Patient patient, Device device, LocalDateTime reservationTime, String bodyRegion, String comment) {
        this.patient = patient;
        this.device = device;
        this.reservationTime = reservationTime;
        this.bodyRegion = bodyRegion;
        this.comment = comment;
    }

    public Reservation() {
    }
}


