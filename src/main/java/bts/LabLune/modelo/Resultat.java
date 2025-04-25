package bts.LabLune.modelo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Resultat {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer idResultat;

        @ManyToOne
        @JoinColumn(name = "idPatient")
        private Patient patient;

        @ManyToMany
        @JoinTable(
                name = "resultat_doctor",
                joinColumns = @JoinColumn(name = "idResultat"),
                inverseJoinColumns = @JoinColumn(name = "idDoctor")
        )
        private List<Doctor> doctors = new ArrayList<>();

        private String descripcion; // Agregamos la descripción para los resultados

        // Getters y Setters

        public Integer getIdResultat() {
                return idResultat;
        }

        public void setIdResultat(Integer idResultat) {
                this.idResultat = idResultat;
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

        public String getDescripcion() {
                return descripcion;
        }

        public void setDescripcion(String descripcion) {
                this.descripcion = descripcion;
        }
}