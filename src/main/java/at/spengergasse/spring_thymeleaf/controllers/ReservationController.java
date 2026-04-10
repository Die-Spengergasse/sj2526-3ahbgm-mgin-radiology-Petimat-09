package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.Reservation;
import at.spengergasse.spring_thymeleaf.repository.DeviceRepository;
import at.spengergasse.spring_thymeleaf.repository.PatientRepository;
import at.spengergasse.spring_thymeleaf.repository.ReservationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String saveReservation(@ModelAttribute Reservation reservation) {
        reservationRepo.save(reservation);
        return "redirect:/reservations/list";
    }

    @GetMapping("/list")
    public String listReservations(@RequestParam(defaultValue = "") String device, Model model) {
        List<Reservation> reservations;
        if (device.isEmpty()) {
            reservations = reservationRepo.findAll();
        } else {
            reservations = reservationRepo.findByDeviceDesignation(device);
        }
        model.addAttribute("reservations", reservations);
        model.addAttribute("devices", deviceRepo.findAll());
        model.addAttribute("selectedDevice", device);
        return "reservation-list";
    }
}