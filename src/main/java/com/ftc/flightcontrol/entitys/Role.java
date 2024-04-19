package com.ftc.flightcontrol.entitys;

import java.util.Collection;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase es una entidad mapeada de la BBDD que contiene los roles de autentificacion registrados de la aplicacion.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Roles", catalog = "flightcontrol")
public class Role implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private int id;
    @Column(name = "Descripcion", unique = true, length = 255)
    private String descripcion;
    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private Collection<Usuario> usuario;

    public Role(String descripcion) {
        this.descripcion = descripcion;
    }

    public String toString() {
        return "Role [ ID: " + id + ", Descripcion: " + descripcion  + " ]";
    }

}
