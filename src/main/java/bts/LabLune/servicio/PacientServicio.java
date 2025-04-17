package bts.LabLune.servicio;

import bts.LabLune.modelo.Patient;
import bts.LabLune.repositorio.PacienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//para que sea componente de fabrica de spring y pueda hacer uso de spring
public class PacientServicio implements IPacientServicio {
    @Autowired
    //Inyectar de manera automatico
    private PacienteRepositorio pacienteRepositorio;

    @Override
    public List<Patient> listarPacientes() {
        return pacienteRepositorio.findAll();
    }

    @Override
    public Patient buscarPacientePorId(Integer idResult) {
        return pacienteRepositorio.findById(idResult).orElse(null);
    }

    @Override
    public void guardarPaciente(Patient patient) {
            pacienteRepositorio.save(patient);
    }

    @Override
    public void eliminarPaciente(Patient patient) {
              pacienteRepositorio.delete(patient);
    }
}
