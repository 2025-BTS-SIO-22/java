package bts.LabLune.repositorio;

import bts.LabLune.modelo.Pacient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepositorio extends JpaRepository<Pacient,Integer> { //Indicamos clase de Entidad que vamos a usar y llave primaria de la entidad a utilizar
//De esto modo tenemos capa de repositorio para conectarnos a bd



}
