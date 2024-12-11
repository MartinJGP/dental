package com.example.repositories;

import com.example.models.Citas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CitasRepository extends JpaRepository<Citas, Long> {

    @Query("select c from Citas c where c.estado = true")
    List<Citas> findAllEnable();

    @Query("select c from Citas c where c.usuario.id = ?1 and c.estado = true")
    List<Citas> findByUsuario(Long id);

    @Query("select c from Citas c where c.horario.fecha = ?1 and c.estado = true order by c.horario.fecha desc ")
    List<Citas> findAllByFecha(String fecha);

}
