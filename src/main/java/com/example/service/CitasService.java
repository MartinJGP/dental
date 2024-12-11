package com.example.service;

import com.example.models.Citas;
import com.example.models.Doctor;

import java.util.List;

public interface CitasService {
    List<Citas> getAll();
    List<Citas> getAllByUser(Long id);
    List<Citas> getfecha(String fecha);
    Citas get(Long id);
    Citas create(Citas doctor);
    Citas update(Citas doctor);
    void Cancelar(Long id);

}
