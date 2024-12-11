package com.example.service;

import com.example.models.HorarioAtencion;

import java.util.List;

public interface HorarioAtencionService {
    List<HorarioAtencion> getAll();
    HorarioAtencion get(Long id);
    HorarioAtencion create(HorarioAtencion horarioAtencion);
    HorarioAtencion update(HorarioAtencion horarioAtencion);
    void delete(Long id);

    List<HorarioAtencion> getallFecha(String fecha);
    void disable(Long id);
    void enable(Long id);

}
