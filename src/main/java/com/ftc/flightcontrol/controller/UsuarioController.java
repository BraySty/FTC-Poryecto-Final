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

import com.ftc.flightcontrol.entitys.usuarios.Usuario;
import com.ftc.flightcontrol.service.UsuarioService;
import com.ftc.flightcontrol.utils.Mensaje;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:4200" })
public class UsuarioController {

	private final UsuarioService service;
	private static final String BASE_URL = "/users";

	/**
	 * Metodo endpoint POST que recibe datos y los guarda en la BBDD
	 * 
	 * @param usuario La clase con los datos que se busca guardar.
	 * @return Mensaje con el estado de la operacion.
	 */
	@PostMapping(BASE_URL)
	ResponseEntity<Mensaje> create(@RequestBody Usuario usuario) {
		return service.save(usuario);
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
	 * @param correoUsuario String con el ID del registro a buscar.
	 * @return ResponseEntity<> con el estado de la operacion.
	 */
	@GetMapping(BASE_URL + "/{correo}")
	ResponseEntity<?> read(@PathVariable("correo") String correoUsuario) {
		return service.read(correoUsuario);
	}

	/**
	 * Metodo endpoint PUT que recibe datos y actualiza un registro especifico.
	 * 
	 * @param usuario Los datos del registro a actualizar
	 * @return ResponseEntity<> con el estado de la operacion.
	 */
	@PutMapping(BASE_URL)
	ResponseEntity<Mensaje> update(@RequestBody Usuario usuario) {
		return service.update(usuario);
	}

	/**
	 * Metodo endpoint PUT que eliminar un registro especifico.
	 * 
	 * @param correoUsuario String con el ID del registro a eliminar.
	 * @return ResponseEntity<> con el estado de la operacion.
	 */
	@DeleteMapping(BASE_URL + "/{correo}")
	ResponseEntity<Mensaje> delete(@PathVariable("correo") String correoUsuario) {
		return service.delete(correoUsuario);
	}

}
