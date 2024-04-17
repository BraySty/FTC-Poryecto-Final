package com.ftc.flightcontrol.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftc.flightcontrol.entitys.Clase;
import com.ftc.flightcontrol.entitys.Mensaje;
import com.ftc.flightcontrol.service.ClaseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:4200" })
public class ClaseController {

    private final ClaseService service;

    @PostMapping("/clases")
    ResponseEntity<Mensaje> create(@RequestBody Clase clase) {
        return service.saveById(clase);
    }

    @GetMapping("/clases")
    ResponseEntity<?> readAll() {
        return service.readAll();
    }

    @GetMapping("/clases/{id}")
    ResponseEntity<?> read(@PathVariable("id") int id) {
        return service.readById(id);
    }

    @PutMapping("/clases")
    ResponseEntity<Mensaje> update(@RequestBody Clase clase) {
        return service.updateById(clase);
    }

    @DeleteMapping("/clases/{id}")
    ResponseEntity<Mensaje> delete(@PathVariable("id") int id) {
        return service.deleteById(id);
    }

}
