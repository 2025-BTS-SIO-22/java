package bts.LabLune.modelo;


import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;


@Entity //se va a comunicar con la bd
@Data   //generar manera automatica get y set
@NoArgsConstructor //generar de maneera automatica constructor vacio
@AllArgsConstructor //genera de manera automatica constructor con todos los elementos
@ToString //genera el metodo ToString
public class User {
    // los atributos


    private Integer idPacient;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String username;
        private String password;
    }

