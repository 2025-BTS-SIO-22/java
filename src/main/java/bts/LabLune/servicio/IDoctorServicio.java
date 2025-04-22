package bts.LabLune.servicio;

import bts.LabLune.modelo.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface IDoctorServicio  {


        public List<Doctor> listarDoctores();

        public Doctor buscarDoctoresporId(Integer idDoctor);

        public void guardarDoctor(Doctor idDoctor); //id de resultado nulo se hace una insercion, registro a agregar
        //id diferente a nulo es actualizar

        public void eliminarDoctor(Doctor doctor);




}
