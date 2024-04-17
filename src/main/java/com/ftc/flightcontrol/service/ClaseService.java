package com.ftc.flightcontrol.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ftc.flightcontrol.entitys.Avion;
import com.ftc.flightcontrol.entitys.Clase;
import com.ftc.flightcontrol.entitys.Mensaje;
import com.ftc.flightcontrol.repository.ClaseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClaseService {

    @Autowired
    private ClaseRepository repo;

    public ResponseEntity<Mensaje> saveById(Clase clase) {
        Optional<Clase> busqueda = repo.findById(clase.getId());
        if (busqueda.isPresent()) {
            return new ResponseEntity<>(new Mensaje("Ya existe este rol."), HttpStatus.CONFLICT);
        } else {
            repo.save(clase);
            return new ResponseEntity<>(new Mensaje("Se ha creado el nuevo rol: " + clase.getDescripcion()), HttpStatus.CREATED);
        }
    }

    public ResponseEntity<?> readAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readAll'");
    }

    public ResponseEntity<?> readById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readById'");
    }

    public ResponseEntity<Mensaje> updateById(Clase clase) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateById'");
    }

    public ResponseEntity<Mensaje> deleteById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

}
