package bts.lab_lune.repository;

import bts.lab_lune.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    //Indicamos clase de Entidad que vamos a usar y llave primaria de la entidad a utilizar
    //De esto modo tenemos capa de repositorio para conectarnos a bd
}
