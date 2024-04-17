package com.ftc.flightcontrol.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftc.flightcontrol.entitys.Usuario;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface AvionRepository extends JpaRepository<Usuario, String> {

}

