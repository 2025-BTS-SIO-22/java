package bts.LabLune.servicio;

import bts.LabLune.modelo.Doctor;
import bts.LabLune.modelo.Patient;
import bts.LabLune.repositorio.DoctorRepositorio;
import bts.LabLune.repositorio.PacienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class DoctorServicio  implements IDoctorServicio{

    @Autowired
    private DoctorRepositorio doctorRepositorio;

    @Override

    public List<Doctor> listarDoctores() {
        return doctorRepositorio.findAll();  // Esto debería devolver todos los doctores desde la base de datos
    }
    

    @Override
    public Doctor buscarDoctoresporId(Integer idDoctor) {
        return null;
    }

    @Override
    public void guardarDoctor(Doctor idDoctor) {

    }

    @Override
    public void eliminarDoctor(Doctor doctor) {

    }
}

