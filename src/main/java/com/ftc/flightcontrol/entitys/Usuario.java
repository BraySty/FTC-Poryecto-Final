package com.ftc.flightcontrol.entitys;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Esta clase es la super clase para las demas clases que heredan de esta.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Persona", catalog = "flightcontrol")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
public class Usuario implements UserDetails {

    @Id
    @Column(name = "DNI", length = 255, unique = true)
    protected String dni;
    @Column(name = "Nombre", length = 255)
    protected String nombre;
    @Column(name = "Apellido", length = 255)
    protected String apellido;
    @Column(name = "Correo", length = 255, unique = true)
    protected String correo;
    @Column(name = "Password", length = 255)
    protected String password;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "roles_id", referencedColumnName = "id")
    protected Role role;
    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private Collection<VueloPersona> vueloPersona;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return List.of(new SimpleGrantedAuthority((role.getDescripcion())));
    }
    @JsonIgnore
    @Override
    public String getUsername() {
        return this.nombre;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
       return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
       return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

}
