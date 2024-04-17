package com.ftc.flightcontrol.entitys;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase es la super clase para las demas clases que heredan de esta.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "Vuelo", catalog = "flightcontrol")
public class Vuelos {

    @Id
    @Column(name = "DNI", length = 255, unique = true)
    private String id;
    @Column(name = "Salida", length = 255)
    private String salida;
    @Column(name = "Hora_Salida")
    private Date horaSalida;
    @Column(name = "Llegada", length = 255)
    private String llegada;
    @Column(name = "Hora_Llegada")
    private Date horaLlegada;

}
