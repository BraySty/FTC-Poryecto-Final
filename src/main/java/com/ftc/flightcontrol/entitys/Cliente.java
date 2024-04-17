package com.ftc.flightcontrol.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

/**
 * Esta clase son los clientes de la aplicacion.
 * Heredade la clase Persona e implementa UserDetails para 
 * su uso con seguridad con JWT
 */
@Entity
public class Cliente extends Usuario {

    @Column(name = "Equipaje")
    private int equipaje;

    public Cliente() {
        super();
    }

    public Cliente(String dni, String nombre, String apellido, String correo, String password, Role role, int equipaje) {
        super(dni, nombre, apellido, correo, password, role);
        this.equipaje = equipaje;
    }

    @Override
    public String toString() {
        return "Cliente [DNI = " + dni + "nombre = " + nombre + " " + apellido + ", correo = " + correo + ", contraseña = " + password + "]";
    }

}
