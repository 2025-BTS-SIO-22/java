package bts.LabLune.servicio;

import bts.LabLune.modelo.Pacient;
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
    public List<Pacient> listarPacientes() {
        return pacienteRepositorio.findAll();
    }

    @Override
    public Pacient buscarPacientePorId(Integer idResult) {
      Pacient pacient = pacienteRepositorio.findById(idResult).orElse(null);
      return pacient;
    }

    @Override
    public void guardarPaciente(Pacient pacient) {
            pacienteRepositorio.save(pacient);
    }

    @Override
    public void eliminarPaciente(Pacient pacient) {
              pacienteRepositorio.delete(pacient);
    }
}
