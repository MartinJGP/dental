package com.example.service.Impl;

import com.example.models.HorarioAtencion;
import com.example.repositories.HorarioAtencionRepository;
import com.example.service.HorarioAtencionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioAtencionServiceImpl implements HorarioAtencionService {
    private final HorarioAtencionRepository repository;
    public HorarioAtencionServiceImpl(HorarioAtencionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<HorarioAtencion> getAll() {
        return repository.findAll();
    }

    @Override
    public HorarioAtencion get(Long id) {
        Optional<HorarioAtencion> horarioAtencion = repository.findById(id);
        return horarioAtencion.orElse(null);
    }

    @Override
    public HorarioAtencion create(HorarioAtencion horarioAtencion) {
        horarioAtencion.setHabilitado(true);
        return repository.save(horarioAtencion);
    }

    @Override
    public HorarioAtencion update(HorarioAtencion horarioAtencion) {
        Optional<HorarioAtencion> horarioAtencion1 = repository.findById(horarioAtencion.getId());
        if (horarioAtencion1.isPresent()){
            horarioAtencion1.get().setFecha(horarioAtencion.getFecha());
            horarioAtencion1.get().setHoraInicio(horarioAtencion.getHoraInicio());
            horarioAtencion1.get().setHoraFin(horarioAtencion.getHoraFin());
            horarioAtencion1.get().setDoctor(horarioAtencion.getDoctor());
            horarioAtencion1.get().setHabilitado(horarioAtencion.getHabilitado());
            return repository.save(horarioAtencion1.get());
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<HorarioAtencion> horarioAtencion = repository.findById(id);
        horarioAtencion.ifPresent(repository::delete);
    }

    @Override
    public List<HorarioAtencion> getallFecha(String fecha) {
        return repository.findAllByFecha(fecha);
    }

    @Override
    public void disable(Long id) {
        Optional<HorarioAtencion> horarioAtencion = repository.findById(id);
        horarioAtencion.ifPresent(horario->{
            horario.setHabilitado(false);
            repository.save(horario);
        });
    }

    @Override
    public void enable(Long id) {
        Optional<HorarioAtencion> horarioAtencion = repository.findById(id);
        horarioAtencion.ifPresent(horario->{
            horario.setHabilitado(true);
            repository.save(horario);
        });
    }
}
