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
    private String line = "El rol con ID: ";
    private String noExist = " no existe.";

    /**
     * Metodo para guardar un Role dentro de la BBDD.
     * 
     * @param role El Role a guardar.
     * @return ResponseEntity<> con el estado de la operacion.
     */
    public ResponseEntity<Mensaje> save(Role role) {
        Optional<Role> busqueda = repo.findFirstByDescripcion(role.getDescripcion());
        if (busqueda.isPresent()) {
            return new ResponseEntity<>(new Mensaje("Ya existe este rol."), HttpStatus.CONFLICT);
        } else {
            repo.save(role);
            return new ResponseEntity<>(new Mensaje("Se ha creado el nuevo rol: " + role.getDescripcion()),
                    HttpStatus.CREATED);
        }
    }

    /**
     * Metodo para buscar registros dentro de la BBDD.
     * 
     * @return ResponseEntity<> con List<Role> con todos los registros o
     *         ResponseEntity<> con un Mensaje en caso de estar vacia.
     */
    public ResponseEntity<?> readAll() {
        List<Role> lista = repo.findAll();
        if (!lista.isEmpty()) {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Mensaje("No hay ningun rol en la base de datos."), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Metodo para buscar un registro dentro de la BBDD a travez de su ID.
     * 
     * @param id String con el ID a buscar.
     * @return ResponseEntity<> con el registro en forma de Role o ResponseEntity<>
     *         con un Mensaje en caso de no existir.
     */
    public ResponseEntity<?> readById(int id) {
        Optional<Role> busqueda = repo.findById(id);
        if (busqueda.isPresent()) {
            return new ResponseEntity<>(busqueda.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Mensaje(line + id + noExist), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Metodo para actualizar un registro dentro de la BBDD a travez de su ID.
     * 
     * @param role Role con los datos a actualizar.
     * @return ResponseEntity<> con el estado de la operacion.
     */
    public ResponseEntity<Mensaje> updateById(Role role) {
        int id = role.getId();
        Optional<Role> update = repo.findById(role.getId());
        if (update.isPresent()) {
            update.get().setDescripcion(role.getDescripcion());
            repo.save(update.get());
            return new ResponseEntity<>(new Mensaje("Se ha actualizado el rol con ID: " + id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Mensaje(line + id + noExist), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Metodo para eliminar un registro dentro de la BBDD a travez de su ID.
     * 
     * @param id String con el ID a eliminar.
     * @return ResponseEntity<> con el estado de la operacion.
     */
    public ResponseEntity<Mensaje> deleteById(int id) {
        Optional<Role> delete = repo.findById(id);
        if (delete.isPresent()) {
            repo.delete(delete.get());
            return new ResponseEntity<>(new Mensaje("Se ha borrado el rol con ID: " + id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Mensaje(line + id + noExist), HttpStatus.NOT_FOUND);
        }
    }

}
