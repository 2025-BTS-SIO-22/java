package bts.lab_lune.service;

import bts.lab_lune.model.Patient;

import java.util.List;

public interface IPatientService {
    public List<Patient> listPatient();

    Patient findPatientById(Integer idResult);

    //id de resultado nulo se hace una insercion, registro a agregar
    //id diferente a nulo es actualizar
    public void savePatient(Patient idPatient);

    public void deletePatient(Patient idPatient);
}
