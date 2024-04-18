package com.ftc.flightcontrol.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ftc.flightcontrol.entitys.Clase;
import com.ftc.flightcontrol.entitys.Mensaje;
import com.ftc.flightcontrol.repository.ClaseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClaseService {

    @Autowired
    private ClaseRepository repo;
    private String line = "La clase con ID: ";
    private String noExist = " no existe.";

    /**
     * Metodo para guardar una Clase dentro de la BBDD.
     * 
     * @param clase La Clase a guardar.
     * @return ResponseEntity<> con el estado de la operacion.
     */
    public ResponseEntity<Mensaje> save(Clase clase) {
        Optional<Clase> busqueda = repo.findById(clase.getId());
        if (busqueda.isPresent()) {
            return new ResponseEntity<>(new Mensaje("Ya existe esta clase."), HttpStatus.CONFLICT);
        } else {
            repo.save(clase);
            return new ResponseEntity<>(new Mensaje("Se ha creado la nueva clase: " + clase.getDescripcion()),
                    HttpStatus.CREATED);
        }
    }

    /**
     * Metodo para buscar registros dentro de la BBDD.
     * 
     * @return ResponseEntity<> con List<Clase> con todos los registros o
     *         ResponseEntity<> con un Mensaje en caso de estar vacia.
     */
    public ResponseEntity<?> readAll() {
        List<Clase> lista = repo.findAll();
        if (!lista.isEmpty()) {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Mensaje("No hay ninguna clase en la base de datos."), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Metodo para buscar un registro dentro de la BBDD a travez de su ID.
     * 
     * @param id String con el ID a buscar.
     * @return ResponseEntity<> con el registro en forma de Clase o ResponseEntity<>
     *         con un Mensaje en caso de no existir.
     */
    public ResponseEntity<?> readById(int id) {
        Optional<Clase> busqueda = repo.findById(id);
        if (busqueda.isPresent()) {
            return new ResponseEntity<>(busqueda.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Mensaje(line + id + noExist), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Metodo para actualizar un registro dentro de la BBDD a travez de su ID.
     * 
     * @param clase Clase con los datos a actualizar.
     * @return ResponseEntity<> con el estado de la operacion.
     */
    public ResponseEntity<Mensaje> updateById(Clase clase) {
        int id = clase.getId();
        Optional<Clase> update = repo.findById(id);
        if (update.isPresent()) {
            update.get().setDescripcion(clase.getDescripcion());
            repo.save(update.get());
            return new ResponseEntity<>(new Mensaje("Se ha actualizado la clase con ID: " + id), HttpStatus.OK);
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
        Optional<Clase> delete = repo.findById(id);
        if (delete.isPresent()) {
            repo.delete(delete.get());
            return new ResponseEntity<>(new Mensaje("Se ha borrado la clase con ID: " + id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Mensaje(line + id + noExist), HttpStatus.NOT_FOUND);
        }
    }

}
