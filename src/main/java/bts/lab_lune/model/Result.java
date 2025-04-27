package bts.lab_lune.model;

import jakarta.persistence.*;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Result {
    // Puedes agregar más campos si hay contenido del resultado, fecha, etc.
    // Getters y Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idResult;

    private String description;

    @ManyToOne
    @JoinColumn(name = "idPatient")
    private Patient patient;

    @ManyToMany
    @JoinTable(
            name = "result_doctor",
            joinColumns = @JoinColumn(name = "idResult"),
            inverseJoinColumns = @JoinColumn(name = "idDoctor")
    )
    private List<Doctor> doctors = new ArrayList<>();

    public Integer getIdResult() {
        return idResult;
    }

    public void setIdResult(Integer idResult) {
        this.idResult = idResult;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }
}
