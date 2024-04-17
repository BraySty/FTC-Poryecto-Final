package com.ftc.flightcontrol.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ftc.flightcontrol.entitys.Mensaje;
import com.ftc.flightcontrol.entitys.Role;
import com.ftc.flightcontrol.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {

    @Autowired
    private RoleRepository repo;

    public ResponseEntity<Mensaje> saveById(Role role) {
        Optional<Role> busqueda = repo.findFirstByDescripcion(role.getDescripcion());
        if (busqueda.isPresent()) {
            return new ResponseEntity<>(new Mensaje("Ya existe este rol."), HttpStatus.CONFLICT);
        } else {
            repo.save(role);
            return new ResponseEntity<>(new Mensaje("Se ha creado el nuevo rol: " + role.getDescripcion()),
                    HttpStatus.CREATED);
        }
    }

    public ResponseEntity<?> readAll() {
        List<Role> lista = repo.findAll();
        if (!lista.isEmpty()) {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Mensaje("No hay ningun rol en la base de datos."), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> readById(int idRol) {
        Optional<Role> busqueda = repo.findById(idRol);
        if (busqueda.isPresent()) {
            return new ResponseEntity<>(busqueda.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Mensaje("El rol con ID: " + idRol + " no existe."), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Mensaje> updateById(Role role) {
        int idRol = role.getId();
        Optional<Role> update = repo.findById(role.getId());
        if (update.isPresent()) {
            update.get().setDescripcion(role.getDescripcion());
            repo.save(update.get());
            return new ResponseEntity<>(new Mensaje("Se ha actualizado el rol con ID: " + idRol), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Mensaje("El rol con ID: " + idRol + " no existe."),
                    HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Mensaje> deleteById(int idRol) {
        Optional<Role> delete = repo.findById(idRol);
		if (delete.isPresent()) {
			repo.delete(delete.get());
			return new ResponseEntity<>(new Mensaje("Se ha borrado el rol con ID: " + idRol),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Mensaje("El rol con ID: " + idRol + " no existe."), HttpStatus.NOT_FOUND);
		}
    }

}
