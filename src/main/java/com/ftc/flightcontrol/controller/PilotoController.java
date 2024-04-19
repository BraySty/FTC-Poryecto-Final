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

import com.ftc.flightcontrol.entitys.usuarios.Piloto;
import com.ftc.flightcontrol.service.PilotoService;
import com.ftc.flightcontrol.utils.Mensaje;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:4200" })
public class PilotoController {

	private final PilotoService service;
	private static final String BASE_URL = "/pilotos";

	/**
	 * Metodo endpoint POST que recibe datos y los guarda en la BBDD
	 * 
	 * @param piloto La clase con los datos que se busca guardar.
	 * @return Mensaje con el estado de la operacion.
	 */
	@PostMapping(BASE_URL)
	ResponseEntity<Mensaje> create(@RequestBody Piloto piloto) {
		return service.save(piloto);
	}

	/**
	 * Metodo endpoint GET que lee todos los registro dentro de una tabla
	 * y los envia.
	 * 
	 * @return Se envia una List<> en caso de encontrar registros, sino un
	 *         ResponseEntity<> con el estado de la operacion.
	 */
	//@PreAuthorize("hasAuthority('Cliente')")
	@GetMapping(BASE_URL)
	ResponseEntity<?> read() {
		return service.read();
	}

	/**
	 * Metodo endpoint GET que regresa un registro especifico de una tabla.
	 * 
	 * @param dni String con el ID del registro a buscar.
	 * @return ResponseEntity<> con el estado de la operacion.
	 */
	@GetMapping(BASE_URL + "/{dni}")
	ResponseEntity<?> read(@PathVariable("dni") String dni) {
		return service.read();
	}

	/**
	 * Metodo endpoint PUT que recibe datos y actualiza un registro especifico.
	 * 
	 * @param piloto Los datos del registro a actualizar
	 * @return ResponseEntity<> con el estado de la operacion.
	 */
	@PutMapping(BASE_URL)
	ResponseEntity<Mensaje> update(@RequestBody Piloto piloto) {
		return service.update(piloto);
	}

	@PutMapping(BASE_URL + "/{dni}/{rol}")
	ResponseEntity<Mensaje> setRole(@PathVariable("dni") String dni, @PathVariable("rol") String rol) {
		return service.updateRole(dni, rol);
	}

	/**
	 * Metodo endpoint PUT que eliminar un registro especifico.
	 * 
	 * @param dni String con el ID del registro a eliminar.
	 * @return ResponseEntity<> con el estado de la operacion.
	 */
	@DeleteMapping(BASE_URL + "/{dni}")
	ResponseEntity<Mensaje> delete(@PathVariable("dni") String dni) {
		return service.delete(dni);
	}

}
