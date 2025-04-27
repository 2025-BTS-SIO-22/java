package bts.lab_lune.model;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

//se va a comunicar con la bd
@Entity
@ToString(exclude = "results")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDoctor;

    private String nameDoctor;

    private String lastnameDoctor;

    @ManyToMany(mappedBy = "doctors")
    private List<Result> results = new ArrayList<>();

    public Integer getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Integer idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getNameDoctor() {
        return nameDoctor;
    }

    public void setNameDoctor(String nameDoctor) {
        this.nameDoctor = nameDoctor;
    }

    public String getLastnameDoctor() {
        return lastnameDoctor;
    }

    public void setLastnameDoctor(String lastnameDoctor) {
        this.lastnameDoctor = lastnameDoctor;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
