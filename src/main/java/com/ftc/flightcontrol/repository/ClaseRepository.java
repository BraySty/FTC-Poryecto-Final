package com.ftc.flightcontrol.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftc.flightcontrol.entitys.Clase;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface ClaseRepository extends JpaRepository<Clase, Integer> {

    Optional<Clase> findFirstByDescripcion(String descripcion);
    int deleteFirstByDescripcion(String descripcion);

}
