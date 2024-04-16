package com.ftc.flightcontrol.security.role;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ftc.flightcontrol.entitys.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Roles", catalog = "flightcontrol")
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "ID", length = 255, unique = true)
    private int id;
    @Column(name = "Descripcion", length = 255)
    private String descripcion;
    @OneToOne(mappedBy = "address")
    @JsonBackReference
    private Usuario usuario;

}
