package com.ftc.flightcontrol.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftc.flightcontrol.entitys.Mensaje;
import com.ftc.flightcontrol.entitys.Usuario;
import com.ftc.flightcontrol.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping("/users") 
	ResponseEntity<Mensaje> create(@RequestBody Usuario usuario) {
		return service.save(usuario);
	}
	
	@PreAuthorize("hasAuthority('Cliente')")
	@GetMapping("/users") 
	ResponseEntity<?> read() {
		return service.read();
	}

    @GetMapping("/users/{correo}") 
	ResponseEntity<?> read(@PathVariable("correo") String correoUsuario) {
		return service.read(correoUsuario);
	}

    @PutMapping("/users/{correo}/{password}") 
	ResponseEntity<Mensaje> update(@PathVariable("correo") String correoUsuario, @PathVariable("password") String passwordUsuario, @RequestBody Usuario usuario) {
		return service.update(correoUsuario, passwordUsuario, usuario);
	}

    @DeleteMapping("/users/{correo}") 
	ResponseEntity<Mensaje> delete(@PathVariable("correo") String correoUsuario) {
        return service.delete(correoUsuario);
	}

}
