package com.ftc.flightcontrol.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ftc.flightcontrol.entitys.Avion;
import com.ftc.flightcontrol.repository.AvionRepository;
import com.ftc.flightcontrol.utils.Mensaje;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvionService {

     @Autowired
     private AvionRepository repo;
     private String line = "El avion con matricula: ";
     private String noExist = " no existe.";

     /**
      * Metodo para guardar un Avion dentro de la BBDD.
      * 
      * @param avion El Avion a guardar.
      * @return ResponseEntity<> con el estado de la operacion.
      */
     public ResponseEntity<Mensaje> save(Avion avion) {
          Optional<Avion> busqueda = repo.findById(avion.getMatricula());
          if (busqueda.isPresent()) {
               return new ResponseEntity<>(new Mensaje("Ya existe esta clase."), HttpStatus.CONFLICT);
          } else {
               repo.save(avion);
               return new ResponseEntity<>(
                         new Mensaje("Se ha creado el nuevo avion con matricula: " + avion.getMatricula()),
                         HttpStatus.CREATED);
          }
     }

     /**
      * Metodo para buscar registros dentro de la BBDD.
      * 
      * @return ResponseEntity<> con List<Avion> con todos los registros o
      *         ResponseEntity<> con un Mensaje en caso de estar vacia.
      */
     public ResponseEntity<?> readAll() {
          List<Avion> lista = repo.findAll();
          if (!lista.isEmpty()) {
               return new ResponseEntity<>(lista, HttpStatus.OK);
          } else {
               return new ResponseEntity<>(new Mensaje("No hay ningun avion en la base de datos."),
                         HttpStatus.NO_CONTENT);
          }
     }

     /**
      * Metodo para buscar un registro dentro de la BBDD a travez de su ID.
      * 
      * @param matricula String con el ID a buscar.
      * @return ResponseEntity<> con el registro en forma de Avion o ResponseEntity<>
      *         con un Mensaje en caso de no existir.
      */
     public ResponseEntity<?> readById(String matricula) {
          Optional<Avion> busqueda = repo.findById(matricula);
          if (busqueda.isPresent()) {
               return new ResponseEntity<>(busqueda.get(), HttpStatus.OK);
          } else {
               return new ResponseEntity<>(new Mensaje(line + matricula + noExist), HttpStatus.NOT_FOUND);
          }
     }

     /**
      * Metodo para actualizar un registro dentro de la BBDD a travez de su ID.
      * 
      * @param avion Avion con los datos a actualizar.
      * @return ResponseEntity<> con el estado de la operacion.
      */
     public ResponseEntity<Mensaje> updateById(Avion avion) {
          String matricula = avion.getMatricula();
          Optional<Avion> update = repo.findById(matricula);
          if (update.isPresent()) {
               update.get().setCapacidad(avion.getCapacidad());
               update.get().setCarga(avion.getCarga());
               repo.save(update.get());
               return new ResponseEntity<>(new Mensaje("Se ha actualizado el avion con matricula: " + matricula),
                         HttpStatus.OK);
          } else {
               return new ResponseEntity<>(new Mensaje(line + matricula + noExist), HttpStatus.NOT_FOUND);
          }
     }

     /**
      * Metodo para eliminar un registro dentro de la BBDD a travez de su ID.
      * 
      * @param matricula String con el ID a eliminar.
      * @return ResponseEntity<> con el estado de la operacion.
      */
     public ResponseEntity<Mensaje> deleteById(String matricula) {
          Optional<Avion> delete = repo.findById(matricula);
          if (delete.isPresent()) {
               repo.delete(delete.get());
               return new ResponseEntity<>(new Mensaje("Se ha borrado el avion con matricula: " + matricula),
                         HttpStatus.OK);
          } else {
               return new ResponseEntity<>(new Mensaje(line + matricula + noExist), HttpStatus.NOT_FOUND);
          }
     }
}
