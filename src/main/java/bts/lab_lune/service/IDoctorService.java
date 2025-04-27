package bts.lab_lune.service;

import bts.lab_lune.model.Doctor;

import java.util.List;

public interface IDoctorService {
    public List<Doctor> listDoctor();

    public Doctor findDoctorById(Integer idDoctor);

    //id de resultado nulo se hace una insercion, registro a agregar
    //id diferente a nulo es actualizar
    public void saveDoctor(Doctor idDoctor);

    public void removeDoctor(Doctor doctor);
}
