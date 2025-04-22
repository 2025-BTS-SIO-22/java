package bts.LabLune.servicio;

import bts.LabLune.modelo.Doctor;
import bts.LabLune.repositorio.DoctorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//para que sea componente de fabrica de spring y pueda hacer uso de spring
public class DoctorServicio implements IDoctorServicio {
    @Autowired
    //Inyectar de manera automatico
    private DoctorRepositorio doctorRepositorio;

    @Override
    public List<Doctor> listarDoctores() {
        return doctorRepositorio.findAll();
    }

    @Override
    public Doctor buscarDoctoresporId(Integer idResult) {
        return doctorRepositorio.findById(idResult).orElse(null);
    }

    @Override
    public void guardarDoctor(Doctor doctor) {
        doctorRepositorio.save(doctor);
    }

    @Override
    public void eliminarDoctor(Doctor doctor) {
        doctorRepositorio.delete(doctor);
    }
}
