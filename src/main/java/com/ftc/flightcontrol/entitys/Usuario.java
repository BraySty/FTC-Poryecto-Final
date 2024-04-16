package com.ftc.flightcontrol.entitys;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ftc.flightcontrol.security.role.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "Usuarios", catalog = "flightcontrol")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name = "DNI", length = 255, unique = true)
    String dni;
    @Column(name = "Nombre", length = 255)
    String nombre;
    @Column(name = "Apellido", length = 255)
    String apellido;
    @Column(name = "Correo", length = 255, unique = true)
    String correo;
    @Column(name = "Password", length = 255)
    String password;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "roles_id", referencedColumnName = "id")
    @JsonManagedReference
    private Role role;

    @Override
    public String toString() {
        return "Usuario [DNI = " + dni + "nombre = " + nombre + " " + apellido + ", correo = " + correo + ", contraseña = " + password + "]";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return List.of(new SimpleGrantedAuthority((role.getDescripcion())));
    }
    @Override
    public String getUsername() {
        return this.nombre;
    }
    @Override
    public boolean isAccountNonExpired() {
       return true;
    }
    @Override
    public boolean isAccountNonLocked() {
       return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

}
