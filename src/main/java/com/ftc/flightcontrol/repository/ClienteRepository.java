package com.ftc.flightcontrol.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftc.flightcontrol.entitys.Cliente;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface ClienteRepository extends JpaRepository<Cliente, String> {

    Optional<Cliente> findByCorreo(String correo);
    Optional<Cliente> findByNombre(String userName);

}
