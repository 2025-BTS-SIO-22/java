package bts.lab_lune.service;

import bts.lab_lune.model.Patient;
import bts.lab_lune.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//para que sea componente de fabrica de spring y pueda hacer uso de spring
public class PatientService implements IPatientService {
    @Autowired
    //Inyectar de manera automatico
    private PatientRepository patientRepository;

    @Override
    public List<Patient> listPatient() {
        return patientRepository.findAll();
    }

    @Override
    public Patient findPatientById(Integer idResult) {
        return patientRepository.findById(idResult).orElse(null);
    }

    @Override
    public void savePatient(Patient patient) {
        patientRepository.save(patient);
    }

    @Override
    public void deletePatient(Patient patient) {
        patientRepository.delete(patient);
    }
}
