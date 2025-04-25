package bts.LabLune.repositorio;
import bts.LabLune.modelo.Resultat;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ResultatRepositorio extends JpaRepository<Resultat,Integer> { //Indicamos clase de Entidad que vamos a usar y llave primaria de la entidad a utilizar
//De esto modo tenemos capa de repositorio para conectarnos a bd



}
