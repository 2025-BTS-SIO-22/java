package bts.LabLune.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity //se va a comunicar con la bd
@Data   //generar manera automatica get y set
@NoArgsConstructor //generar de maneera automatica constructor vacio
@AllArgsConstructor //genera de manera automatica constructor con todos los elementos
@ToString //genera el metodo ToString

        public class Doctor {
                @Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                public Integer idDoctor;

                public String nameDoctor;
                public String lastnameDoctor;

                @ManyToMany(mappedBy = "doctors")
                public List<Resultat> resultats = new ArrayList<>();;
        }



