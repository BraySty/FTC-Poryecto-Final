package com.ftc.flightcontrol.entitys;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Avion_Vuelo", 
      joinColumns = @JoinColumn(name = "Vuelo_ID", referencedColumnName = "id"), 
      inverseJoinColumns = @JoinColumn(name = "Avion_Matricula", referencedColumnName = "matricula"))
    private List<Avion> avion;
    @ManyToOne
    @JoinColumn(name = "Clase_ID")
    private Clase clase;
    @OneToMany(mappedBy = "vuelo")
    private List<VueloPersona> vueloPersona;

}
