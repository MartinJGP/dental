package com.example.repositories;

import com.example.models.HorarioAtencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioAtencionRepository extends JpaRepository<HorarioAtencion, Long> {
    @Query("SELECT h FROM HorarioAtencion h WHERE h.fecha = :fecha")
    List<HorarioAtencion> findAllByFecha(String fecha);
}
