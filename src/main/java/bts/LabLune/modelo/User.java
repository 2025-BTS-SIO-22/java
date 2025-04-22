package bts.LabLune.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity // se comunica con la base de datos
@Table(name = "user")
@Data   // genera automáticamente getters, setters, equals, hashCode, toString
@NoArgsConstructor // constructor vacío
@AllArgsConstructor // constructor con todos los elementos
@ToString // genera el método toString

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idUser;

    public String username;
    public String password;
    public boolean admin;

    @OneToOne
    @JoinColumn(name = "idPatient")
    public Patient patient;

    @OneToOne
    @JoinColumn(name = "idDoctor")
    public Doctor doctor;

    public boolean isAdmin(){
        return this.admin;
    }
}
