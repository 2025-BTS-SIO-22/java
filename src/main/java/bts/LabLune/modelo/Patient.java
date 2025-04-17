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

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idPatient;

    public String namePatient;
    public String lastnamePatient;
    public LocalDate birthdayPatient;

    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)
    public List<Resultat> resultats = new ArrayList<>();


}


