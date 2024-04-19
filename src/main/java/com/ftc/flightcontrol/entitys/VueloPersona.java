package com.ftc.flightcontrol.entitys;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase es una entidad mapeada de la BBDD que contiene la clase conectora de Vuelo y Persona.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "Vuelo_Persona", catalog = "flightcontrol")
public class VueloPersona implements Serializable{

    @Id
    @ManyToOne
    @JoinColumn(name = "Vuelo_ID")
    private Vuelo vuelo;
    @Column(name = "Precio")
    private Double precio;
    @Column(name = "Carga")
    private int carga;
    @ManyToOne
    @JoinColumn(name = "Persona_DNI")
    private Usuario usuario;

}
