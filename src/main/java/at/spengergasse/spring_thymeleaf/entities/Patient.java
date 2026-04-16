package at.spengergasse.spring_thymeleaf.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Patient {

    @Id
    private String svn;

    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate birthDate;

    public Patient() {}

    public String getSvn() { return svn; }
    public void setSvn(String svn) { this.svn = svn; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }//modelbinding alle parameter die mit request mitgeschickt werden

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
}