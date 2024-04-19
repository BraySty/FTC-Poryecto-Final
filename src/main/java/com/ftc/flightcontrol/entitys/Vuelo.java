package com.ftc.flightcontrol.entitys;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ftc.flightcontrol.serializer.VueloSerializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@JsonSerialize(using = VueloSerializer.class)
public class Vuelo implements Serializable {

  @Id
  @Column(name = "ID", length = 255, unique = true)
  private String id;

  @Column(name = "Salida", length = 255)
  private String salida;

  @Column(name = "Hora_Salida")
  private Date horaSalida;
  
  @Column(name = "Llegada", length = 255)
  private String llegada;

  @Column(name = "Hora_Llegada")
  private Date horaLlegada;

  @ManyToOne
  @JoinColumn(name = "Avion_Matricula")
  private Avion avion;

  @ManyToOne
  @JoinColumn(name = "Clase_ID")
  private Clase clase;

  @OneToMany(mappedBy = "vuelo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List <VueloPersona> vueloPersona;

}
