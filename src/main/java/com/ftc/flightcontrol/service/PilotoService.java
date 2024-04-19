package com.ftc.flightcontrol.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ftc.flightcontrol.entitys.Role;
import com.ftc.flightcontrol.entitys.usuarios.Piloto;
import com.ftc.flightcontrol.repository.PilotoRepository;
import com.ftc.flightcontrol.repository.RoleRepository;
import com.ftc.flightcontrol.utils.Mensaje;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PilotoService {

    @Autowired
    private PilotoRepository repo;
    @Autowired
    private RoleRepository roleRepo;

    private final PasswordEncoder passwordEncoder;
    private String userEmailMsg = "El piloto con correo: ";

    public ResponseEntity<Mensaje> save(Piloto piloto) {
        String dni = piloto.getDni();
        Optional<Piloto> busqueda = repo.findById(dni);
        if (busqueda.isPresent()) {
            return new ResponseEntity<>(new Mensaje(userEmailMsg + dni + " ya existe"),
                    HttpStatus.CONFLICT);
        } else {
            String password = piloto.getPassword();
            piloto.setPassword(passwordEncoder.encode(password));
            repo.save(piloto);
            return new ResponseEntity<>(new Mensaje(userEmailMsg + dni + " ha sido creado"), HttpStatus.CREATED);
        }
    }

    public ResponseEntity<?> read() {
        List<Piloto> lista = repo.findAll();
        if (!lista.isEmpty()) {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Mensaje("No hay ningun piloto."), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Mensaje> update(Piloto piloto) {
        String dni = piloto.getDni();
        Optional<Piloto> update = repo.findById(dni);
        if (update.isPresent()) {
            update.get().setNombre(piloto.getNombre());
            update.get().setApellido(piloto.getApellido());
            update.get().setCorreo(piloto.getCorreo());
            update.get().setPassword(piloto.getPassword());
            update.get().setHorasVuelo(piloto.getHorasVuelo());
            repo.save(update.get());
            return new ResponseEntity<>(new Mensaje("El piloto fue actualizado."), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Mensaje(userEmailMsg + piloto.getCorreo() + " no existe."),
                    HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Mensaje> updateRole(String dni, String rol) {
        Optional<Piloto> update = repo.findById(dni);
        if (update.isPresent()) {
            Role role = roleRepo.findFirstByDescripcion(rol).orElse(null);
            if (role != null) {
                return new ResponseEntity<>(new Mensaje("El rol: " + rol + " no existe"), HttpStatus.NOT_ACCEPTABLE);
            } else {
                roleRepo.save(new Role(rol));
                update.get().setRole(role);
            }
            repo.save(update.get());
            return new ResponseEntity<>(new Mensaje("El piloto fue actualizado."), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Mensaje(userEmailMsg + dni + " no existe."), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Mensaje> delete(String dni) {
        Optional<Piloto> delete = repo.findById(dni);
        if (delete.isPresent()) {
            repo.delete(delete.get());
            return new ResponseEntity<>(new Mensaje("Se ha borrado el usuario con id: " + dni),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Mensaje(userEmailMsg + dni + " no existe"), HttpStatus.NOT_FOUND);
        }
    }

}
