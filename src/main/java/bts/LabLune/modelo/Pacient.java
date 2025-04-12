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
    public class Pacient {
        //Se agregan los atributos
        @Id //Llave primaria
        @GeneratedValue(strategy = GenerationType.IDENTITY) //valor autoincrementable
        private Integer idPacient;
        private String namePacient;
        private String lastnamePacient;
        private Integer idResult;
        private Integer idDoctor;
        private LocalDate date;


    }
