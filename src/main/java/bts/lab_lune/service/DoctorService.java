package bts.lab_lune.service;

import bts.lab_lune.model.Doctor;
import bts.lab_lune.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//para que sea componente de fabrica de spring y pueda hacer uso de spring
public class DoctorService implements IDoctorService {
    @Autowired
    //Inyectar de manera automatico
    private DoctorRepository doctorRepository;

    @Override
    public List<Doctor> listDoctor() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor findDoctorById(Integer idResult) {
        return doctorRepository.findById(idResult).orElse(null);
    }

    @Override
    public void saveDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    @Override
    public void removeDoctor(Doctor doctor) {
        doctorRepository.delete(doctor);
    }
}
