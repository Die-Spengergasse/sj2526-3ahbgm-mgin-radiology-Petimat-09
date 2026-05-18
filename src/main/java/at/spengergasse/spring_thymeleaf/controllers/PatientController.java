package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.Patient;
import at.spengergasse.spring_thymeleaf.repository.PatientRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/patient")
public class PatientController {
    private final PatientRepository patientRepo;

    public PatientController(PatientRepository patientRepo) {
        this.patientRepo = patientRepo;
    }
    @GetMapping("")
    public String newPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient-form";
    }
    
    @PostMapping("/save")
    public String savePatient(@ModelAttribute Patient patient, Model model) {
        if (patient.getSvn() == null || !patient.getSvn().matches("\\d{10}")) {
            model.addAttribute("patient", patient);
            model.addAttribute("error", "Ungültige Sozialversicherungsnummer. Bitte geben Sie genau 10 Ziffern ein.");
            return "patient-form";
        }
        if (patient.getBirthDate() != null && patient.getBirthDate().isAfter(LocalDate.now())) {
            model.addAttribute("patient", patient);
            model.addAttribute("error", "Das Geburtsdatum darf nicht in der Zukunft liegen.");
            return "patient-form";
        }
        try {
            patientRepo.save(patient);
        } catch (DataAccessException e) {
            model.addAttribute("patient", patient);
            model.addAttribute("error", "Datenbankfehler: Die Verbindung zur Datenbank ist fehlgeschlagen. Bitte stellen Sie sicher, dass MySQL läuft.");
            return "patient-form";
        }
        return "redirect:/reservations/new";
    }

}
