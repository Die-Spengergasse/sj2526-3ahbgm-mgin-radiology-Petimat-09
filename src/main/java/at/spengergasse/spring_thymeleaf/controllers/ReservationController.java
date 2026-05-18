package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.Device;
import at.spengergasse.spring_thymeleaf.entities.Patient;
import at.spengergasse.spring_thymeleaf.entities.Reservation;
import at.spengergasse.spring_thymeleaf.repository.DeviceRepository;
import at.spengergasse.spring_thymeleaf.repository.PatientRepository;
import at.spengergasse.spring_thymeleaf.repository.ReservationRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationRepository reservationRepo;
    private final PatientRepository patientRepo;
    private final DeviceRepository deviceRepo;

    public ReservationController(ReservationRepository reservationRepo, PatientRepository patientRepo, DeviceRepository deviceRepo) {
        this.reservationRepo = reservationRepo;
        this.patientRepo = patientRepo;
        this.deviceRepo = deviceRepo;
    }

    @GetMapping("/new")
    public String newReservationForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("patients", patientRepo.findAll());
        model.addAttribute("devices", deviceRepo.findAll());
        return "reservation-form";
    }

    @PostMapping("/save")
    public String saveReservation(@ModelAttribute Reservation reservation, Model model) {
        Patient patient = patientRepo.findById(reservation.getPatient().getSvn()).orElse(null);
        Device device = deviceRepo.findById(reservation.getDevice().getDesignation()).orElse(null);

        reservation.setPatient(patient);
        reservation.setDevice(device);

        if (reservation.getReservationTime() != null && reservation.getReservationTime().isBefore(LocalDateTime.now())) {
            model.addAttribute("reservation", reservation);
            model.addAttribute("patients", patientRepo.findAll());
            model.addAttribute("devices", deviceRepo.findAll());
            model.addAttribute("error", "Der Reservierungszeitpunkt darf nicht in der Vergangenheit liegen.");
            return "reservation-form";
        }

        List<Reservation> deviceConflicts = reservationRepo.findByDeviceDAndReservation(
                        reservation.getDevice().getDesignation(),
                        reservation.getReservationTime());

        if (!deviceConflicts.isEmpty()) {
            model.addAttribute("reservation", reservation);
            model.addAttribute("patients", patientRepo.findAll());
            model.addAttribute("devices", deviceRepo.findAll());
            model.addAttribute("error", "Das Gerät ist zu diesem Zeitpunkt bereits belegt.");
            return "reservation-form";
        }

        List<Reservation> patientConflicts = reservationRepo
                .findByPatientSvnAndReservation(
                        reservation.getPatient().getSvn(),
                        reservation.getReservationTime());

        if (!patientConflicts.isEmpty()) {
            model.addAttribute("reservation", reservation);
            model.addAttribute("patients", patientRepo.findAll());
            model.addAttribute("devices", deviceRepo.findAll());
            model.addAttribute("error", "Der Patient hat zu diesem Zeitpunkt bereits einen Termin.");
            return "reservation-form";
        }

        try {
            reservationRepo.save(reservation);
        } catch (DataAccessException e) {
            model.addAttribute("reservation", reservation);
            model.addAttribute("patients", patientRepo.findAll());
            model.addAttribute("devices", deviceRepo.findAll());
            model.addAttribute("error", "Datenbankfehler: Die Verbindung zur Datenbank ist fehlgeschlagen. Bitte stellen Sie sicher, dass MySQL ausgeführt wird.");
            return "reservation-form";
        }
        return "redirect:/reservations/list";
    }

    @GetMapping("/list")
    public String listReservations(@RequestParam(defaultValue = "") String device, Model model) {
        List<Reservation> reservations;
        try {
            if (device.isEmpty()) {
                reservations = reservationRepo.findAll();
            } else {
                reservations = reservationRepo.findByDeviceDesignation(device);
            }
        }
        catch (DataAccessException e) {
            model.addAttribute("reservations", List.of());
            model.addAttribute("devices", List.of());
            model.addAttribute("error", "Datenbankfehler: Die Verbindung zur Datenbank ist fehlgeschlagen.");
            return "reservation-list";
        }
        model.addAttribute("reservations", reservations);
        model.addAttribute("devices", deviceRepo.findAll());
        model.addAttribute("selectedDevice", device);
        return "reservation-list";
    }
}