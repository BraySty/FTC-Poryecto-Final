package com.ftc.flightcontrol.entitys.usuarios;

import jakarta.persistence.Entity;

/**
 * Esta clase son los clientes de la aplicacion.
 * Heredade la clase Persona e implementa UserDetails para 
 * su uso con seguridad con JWT
 */
@Entity
public class Cliente extends Usuario {

}
