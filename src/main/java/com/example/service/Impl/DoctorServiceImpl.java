package com.example.service.Impl;

import com.example.models.Doctor;
import com.example.repositories.DoctorRepository;
import com.example.service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository repository;

    public DoctorServiceImpl(DoctorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Doctor> getAll() {
        return repository.findAllEnable();
    }

    @Override
    public Doctor get(Long id) {
        Optional<Doctor> doc = repository.findByIdEnable(id);
        return doc.orElse(null);
    }

    @Override
    public Doctor create(Doctor doctor) {
        doctor.setEstado(true);
        return repository.save(doctor);
    }

    @Override
    public Doctor update(Doctor doctor) {
        Optional<Doctor> doc = repository.findById(doctor.getId());
        if (doc.isPresent()){
            doc.get().setCodigointerno(doctor.getCodigointerno());
            doc.get().setNombres(doctor.getNombres());
            doc.get().setApellidos(doctor.getApellidos());
            doc.get().setTelefono(doctor.getTelefono());
            doc.get().setEmail(doctor.getEmail());
            return repository.save(doc.get());
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<Doctor> doc = repository.findById(id);
        doc.ifPresent(horario->{
            horario.setEstado(false);
            repository.save(horario);
        });
    }


}
