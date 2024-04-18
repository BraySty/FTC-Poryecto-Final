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
    private static final String BASE_URL = "/clases";

    /**
     * Metodo endpoint POST que recibe datos y los guarda en la BBDD
     * 
     * @param clase La clase con los datos que se busca guardar.
     * @return Mensaje con el estado de la operacion.
     */
    @PostMapping(BASE_URL)
    ResponseEntity<Mensaje> create(@RequestBody Clase clase) {
        return service.save(clase);
    }

    /**
     * Metodo endpoint GET que lee todos los registro dentro de una tabla
     * y los envia.
     * 
     * @return Se envia una List<> en caso de encontrar registros, sino un
     *         ResponseEntity<> con el estado de la operacion.
     */
    @GetMapping(BASE_URL)
    ResponseEntity<?> readAll() {
        return service.readAll();
    }

    /**
     * Metodo endpoint GET que regresa un registro especifico de una tabla.
     * 
     * @param id String con el ID del registro a buscar.
     * @return ResponseEntity<> con el estado de la operacion.
     */
    @GetMapping(BASE_URL + "/{id}")
    ResponseEntity<?> read(@PathVariable("id") int id) {
        return service.readById(id);
    }

    /**
     * Metodo endpoint PUT que recibe datos y actualiza un registro especifico.
     * 
     * @param clase Los datos del registro a actualizar
     * @return ResponseEntity<> con el estado de la operacion.
     */
    @PutMapping(BASE_URL)
    ResponseEntity<Mensaje> update(@RequestBody Clase clase) {
        return service.updateById(clase);
    }

    /**
     * Metodo endpoint PUT que eliminar un registro especifico.
     * 
     * @param id String con el ID del registro a eliminar.
     * @return ResponseEntity<> con el estado de la operacion.
     */
    @DeleteMapping(BASE_URL + "/{id}")
    ResponseEntity<Mensaje> delete(@PathVariable("id") int id) {
        return service.deleteById(id);
    }

}
