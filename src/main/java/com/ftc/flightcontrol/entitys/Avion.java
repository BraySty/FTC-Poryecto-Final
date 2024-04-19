package com.ftc.flightcontrol.entitys;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ftc.flightcontrol.serializer.AvionSerializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@JsonSerialize(using = AvionSerializer.class)
public class Avion implements Serializable {

    @Id
    @Column(name = "Matricula", length = 255, unique = true)
    private String matricula;
    @Column(name = "Capadicad")
    private int capacidad;
    @Column(name = "Carga")
    private int carga;
    @OneToMany(mappedBy = "avion", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Vuelo> vuelo;

}
