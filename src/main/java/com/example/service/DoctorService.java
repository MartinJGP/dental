package com.example.service;


import com.example.models.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> getAll();
    Doctor get(Long id);
    Doctor create(Doctor doctor);
    Doctor update(Doctor doctor);
    void delete(Long id);


}
