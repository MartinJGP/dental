package com.example.repositories;

import com.example.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query("SELECT d FROM Doctor d WHERE d.estado = true ")
    List<Doctor> findAllEnable();

    @Query("SELECT d FROM Doctor d WHERE d.estado = true AND d.id = ?1")
    Optional<Doctor> findByIdEnable(Long id);

}
