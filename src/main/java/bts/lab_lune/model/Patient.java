package bts.lab_lune.model;

import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//se va a comunicar con la bd
@Entity
//genera el metodo ToString
@ToString
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPatient;

    private String namePatient;

    private String lastnamePatient;

    private LocalDate birthdayPatient;

    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)

    private List<Result> results = new ArrayList<>();

    public Integer getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Integer idPatient) {
        this.idPatient = idPatient;
    }

    public String getNamePatient() {
        return namePatient;
    }

    public void setNamePatient(String namePatient) {
        this.namePatient = namePatient;
    }

    public String getLastnamePatient() {
        return lastnamePatient;
    }

    public void setLastnamePatient(String lastnamePatient) {
        this.lastnamePatient = lastnamePatient;
    }

    public LocalDate getBirthdayPatient() {
        return birthdayPatient;
    }

    public void setBirthdayPatient(LocalDate birthdayPatient) {
        this.birthdayPatient = birthdayPatient;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
