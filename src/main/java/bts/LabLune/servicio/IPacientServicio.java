package bts.LabLune.servicio;

import bts.LabLune.modelo.Patient;

import java.util.List;


public interface IPacientServicio {

    public List<Patient> listarPacientes();

    public Patient buscarPacientePorId(Integer idPacient);

    public void guardarPaciente(Patient idPatient); //id de resultado nulo se hace una insercion, registro a agregar
                                                //id diferente a nulo es actualizar

    public void eliminarPaciente(Patient idPatient);


}
