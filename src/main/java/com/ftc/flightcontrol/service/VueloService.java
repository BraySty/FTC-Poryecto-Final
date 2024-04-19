package com.ftc.flightcontrol.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ftc.flightcontrol.entitys.Avion;
import com.ftc.flightcontrol.entitys.Clase;
import com.ftc.flightcontrol.entitys.Vuelo;
import com.ftc.flightcontrol.repository.AvionRepository;
import com.ftc.flightcontrol.repository.ClaseRepository;
import com.ftc.flightcontrol.repository.VueloRepository;
import com.ftc.flightcontrol.utils.Mensaje;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VueloService {

    @Autowired
    private VueloRepository repo;
    @Autowired
    private ClaseRepository claseRepo;
    @Autowired
    private AvionRepository avionRepo;

    private String line = "El vuelo con ID: ";
    private String noExist = " no existe.";

    /**
     * Metodo para guardar un Vuelo dentro de la BBDD.
     * 
     * @param vuelo El Vuelo a guardar.
     * @return ResponseEntity<> con el estado de la operacion.
     */
    public ResponseEntity<Mensaje> save(Vuelo vuelo) {
        Optional<Vuelo> busqueda = repo.findById(vuelo.getId());
        if (busqueda.isPresent()) {
            return new ResponseEntity<>(new Mensaje("Ya existe esta clase."), HttpStatus.CONFLICT);
        } else {
            repo.save(vuelo);
            return new ResponseEntity<>(new Mensaje("Se ha creado el nuevo vuelo: " + vuelo.getId()),
                    HttpStatus.CREATED);
        }
    }

    /**
     * Metodo para buscar registros dentro de la BBDD.
     * 
     * @return ResponseEntity<> con List<Vuelo> con todos los registros o
     *         ResponseEntity<> con un Mensaje en caso de estar vacia.
     */
    public ResponseEntity<?> readAll() {
        List<Vuelo> lista = repo.findAll();
        if (!lista.isEmpty()) {


            return new ResponseEntity<>(lista, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Mensaje("No hay ningun vuelo en la base de datos."), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Metodo para buscar un registro dentro de la BBDD a travez de su ID.
     * 
     * @param id String con el ID a buscar.
     * @return ResponseEntity<> con el registro en forma de Vuelo o ResponseEntity<>
     *         con un Mensaje en caso de no existir.
     */
    public ResponseEntity<?> readById(String id) {
        Optional<Vuelo> busqueda = repo.findById(id);
        if (busqueda.isPresent()) {
            return new ResponseEntity<>(busqueda.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Mensaje(line + id + noExist), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Metodo para actualizar un registro dentro de la BBDD a travez de su ID.
     * 
     * @param vuelo Vuelo con los datos a actualizar.
     * @return ResponseEntity<> con el estado de la operacion.
     */
    public ResponseEntity<Mensaje> updateById(Vuelo vuelo) {
        String id = vuelo.getId();
        Optional<Vuelo> update = repo.findById(id);
        if (update.isPresent()) {
            update.get().setSalida(vuelo.getSalida());
            update.get().setHoraSalida(vuelo.getHoraSalida());
            update.get().setLlegada(vuelo.getLlegada());
            update.get().setHoraLlegada(vuelo.getHoraLlegada());
            repo.save(update.get());
            return new ResponseEntity<>(new Mensaje("Se ha actualizado el vuelo con ID: " + id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Mensaje(line + id + noExist), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Mensaje> updateClase(String id, String descripcion) {
        Optional<Vuelo> update = repo.findById(id);
        if (update.isPresent()) {
            Clase clase = claseRepo.findFirstByDescripcion(descripcion).orElse(null);
            if (clase != null) {
                update.get().setClase(clase);
            } else {
                return new ResponseEntity<>(new Mensaje("La clase: " + descripcion + " no existe"), HttpStatus.NOT_ACCEPTABLE);
            }
            repo.save(update.get());
            return new ResponseEntity<>(new Mensaje("Se ha actualizado el vuelo con ID: " + id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Mensaje(line + id + noExist), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Mensaje> updateAvion(String id, String matricula) {
        Optional<Vuelo> update = repo.findById(id);
        if (update.isPresent()) {
            Avion avion = avionRepo.findById(matricula).orElse(null);
            if (avion != null) {
                update.get().setAvion(avion);
            } else {
                return new ResponseEntity<>(new Mensaje("El avion con matricula: " + matricula + " no existe"), HttpStatus.NOT_ACCEPTABLE);
            }
            repo.save(update.get());
            return new ResponseEntity<>(new Mensaje("Se ha actualizado el vuelo con ID: " + id), HttpStatus.OK);
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
    public ResponseEntity<Mensaje> deleteById(String id) {
        Optional<Vuelo> delete = repo.findById(id);
        if (delete.isPresent()) {
            repo.delete(delete.get());
            return new ResponseEntity<>(new Mensaje("Se ha borrado el vuelo con ID: " + id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Mensaje(line + id + noExist), HttpStatus.NOT_FOUND);
        }
    }


}
