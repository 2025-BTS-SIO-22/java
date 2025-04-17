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
    private Integer idUser;

    private String username;
    private String password;
    private boolean admin;

    @OneToOne
    @JoinColumn(name = "idPatient")
    private Pacient patient;

    @OneToOne
    @JoinColumn(name = "idDoctor")
    private Doctor doctor;
}
