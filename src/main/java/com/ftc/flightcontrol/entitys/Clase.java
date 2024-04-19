package com.ftc.flightcontrol.entitys;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ftc.flightcontrol.serializer.ClaseSerializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase es una entidad mapeada de la BBDD que contiene las clases de vuelo de la aplicacion.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Clase", catalog = "flightcontrol")
@JsonSerialize(using = ClaseSerializer.class)
public class Clase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private int id;
    @Column(name = "Descripcion", unique = true, length = 255)
    private String descripcion;
    @OneToMany(mappedBy = "clase", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Vuelo> vuelo;

    public Clase(String descripcion) {
        this.descripcion = descripcion;
    }

}
