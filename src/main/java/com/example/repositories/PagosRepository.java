package com.example.repositories;

import com.example.models.Pagos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagosRepository extends JpaRepository<Pagos, Long> {
    @Query("select p from Pagos p where p.cita.id = ?1")
    Pagos findByCita_Id(Long id);

    Optional<Pagos> findByCodigo(Long codigo);
}
