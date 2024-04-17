package com.ftc.flightcontrol.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase es una entidad mapeada de la BBDD que contiene los aviones registrados de la aplicacion.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Avion", catalog = "flightcontrol")
public class Avion {

    @Id
    @Column(name = "Matricula", length = 255, unique = true)
    private String matricula;
    @Column(name = "Capadicad")
    private int capacidad;
    @Column(name = "Carga")
    private int carga;

}
