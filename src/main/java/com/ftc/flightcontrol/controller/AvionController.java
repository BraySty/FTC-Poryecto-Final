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

import com.ftc.flightcontrol.entitys.Avion;
import com.ftc.flightcontrol.entitys.Mensaje;
import com.ftc.flightcontrol.service.AvionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:4200" })
public class AvionController {

    private final AvionService service;
    private static final String BASE_URL = "/aviones";

    /**
     * Metodo endpoint POST que recibe datos y los guarda en la BBDD
     * 
     * @param avion La clase con los datos que se busca guardar.
     * @return ResponseEntity<> con el estado de la operacion.
     */
    @PostMapping(BASE_URL)

    ResponseEntity<Mensaje> create(@RequestBody Avion avion) {
        return service.save(avion);
    }

    /**
     * Metodo endpoint GET que lee todos los registro dentro de una tabla
     * y los envia.
     * 
     * @return Se envia una List<> en caso de encontrar registros, sino un
     *         ResponseEntity<> con el estado de la operacion.
     */
    @GetMapping(BASE_URL)
    @Operation(summary = "GET con todos los aviones.", description = "Regresa todos los registros de aviones registrados en la BBDD.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Avion.class)))
            }),
            @ApiResponse(responseCode = "204", description = "No hay ningun avion en la BBDD.", content = {
                    @Content(mediaType = "String", schema = @Schema(implementation = Mensaje.class))
            }) })
    ResponseEntity<?> readAll() {
        return service.readAll();
    }

    /**
     * Metodo endpoint GET que regresa un registro especifico de una tabla.
     * 
     * @param matricula String con el ID del registro a buscar.
     * @return ResponseEntity<> con el estado de la operacion.
     */
    @GetMapping("/aviones/{matricula}")
    @Operation(summary = "GET de un avione.", description = "Regresa un registro de un avion a travez de su matricula.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Avion.class))
        }),
        @ApiResponse(responseCode = "404", description = "No se encontro ningun registro.", content = {
                @Content(mediaType = "String", schema = @Schema(implementation = Mensaje.class))
        }) })
    ResponseEntity<?> read(@PathVariable("matricula") String matricula) {
        return service.readById(matricula);
    }

    /**
     * Metodo endpoint PUT que recibe datos y actualiza un registro especifico.
     * 
     * @param avion Los datos del registro a actualizar
     * @return ResponseEntity<> con el estado de la operacion.
     */
    @PutMapping(BASE_URL)
    ResponseEntity<Mensaje> update(@RequestBody Avion avion) {
        return service.updateById(avion);
    }

    /**
     * Metodo endpoint PUT que eliminar un registro especifico.
     * 
     * @param matricula String con el ID del registro a eliminar.
     * @return ResponseEntity<> con el estado de la operacion.
     */
    @DeleteMapping("/aviones/{matricula}")
    ResponseEntity<Mensaje> delete(@PathVariable("matricula") String matricula) {
        return service.deleteById(matricula);
    }

}
