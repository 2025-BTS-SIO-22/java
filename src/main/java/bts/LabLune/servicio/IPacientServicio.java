package bts.LabLune.servicio;

import bts.LabLune.modelo.Pacient;

import java.util.List;


public interface IPacientServicio {

    public List<Pacient> listarPacientes();

    public Pacient buscarPacientePorId(Integer idPacient);

    public void guardarPaciente(Pacient idPacient); //id de resultado nulo se hace una insercion, registro a agregar
                                                //id diferente a nulo es actualizar

    public void eliminarPaciente(Pacient idPacient);


}
