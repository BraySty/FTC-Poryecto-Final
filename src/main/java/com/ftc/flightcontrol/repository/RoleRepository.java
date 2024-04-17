package com.ftc.flightcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftc.flightcontrol.entitys.Role;

import jakarta.transaction.Transactional;
import java.util.Optional;


@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findFirstByDescripcion(String descripcion);
}
