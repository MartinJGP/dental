package com.example.service.Impl;

import com.example.models.Citas;
import com.example.repositories.CitasRepository;
import com.example.service.CitasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitasServiceImpl implements CitasService {
    private final CitasRepository repository;
    public CitasServiceImpl(CitasRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Citas> getAll() {
        return repository.findAllEnable();
    }

    @Override
    public List<Citas> getAllByUser(Long id) {
        return repository.findByUsuario(id);
    }

    @Override
    public List<Citas> getfecha(String fecha) {
        return repository.findAllByFecha(fecha);
    }

    @Override
    public Citas get(Long id) {
        Optional<Citas> doc = repository.findById(id);
        return doc.orElse(null);
    }

    @Override
    public Citas create(Citas doctor) {
        doctor.setEstado(true);
        return repository.save(doctor);
    }

    @Override
    public Citas update(Citas doctor) {
        Optional<Citas> doc = repository.findById(doctor.getId());
        if (doc.isPresent()){
            doc.get().setHorario(doctor.getHorario());
            doc.get().setDocumento(doctor.getDocumento());
            doc.get().setEdad(doctor.getEdad());
            doc.get().setEspecialidad(doctor.getEspecialidad());
            doc.get().setNombresapellidos(doctor.getNombresapellidos());
            doc.get().setNumeroCelular(doctor.getNumeroCelular());
            doc.get().setComentarios(doctor.getComentarios());
            return repository.save(doc.get());
        }
        return null;
    }

    @Override
    public void Cancelar(Long id) {
        Optional<Citas> doc = repository.findById(id);
        doc.ifPresent(horario->{
            horario.setEstado(false);
            repository.save(horario);
        });
    }
}
