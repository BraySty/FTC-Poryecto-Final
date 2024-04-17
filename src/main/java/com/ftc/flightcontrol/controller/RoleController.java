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
import com.ftc.flightcontrol.entitys.Role;
import com.ftc.flightcontrol.service.RoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class RoleController {

    private final RoleService service;

    //@PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/roles") 
	ResponseEntity<Mensaje> create(@RequestBody Role role) {
		return service.saveById(role);
	}
	
	//@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/roles") 
	ResponseEntity<?> readAll() {
		return service.readAll();
	}

    //@PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/roles/{id}") 
	ResponseEntity<?> read(@PathVariable("id") int idRol) {
		return service.readById(idRol);
	}

    //@PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/roles") 
	ResponseEntity<Mensaje> update(@RequestBody Role role) {
		return service.updateById(role);
	}

    //@PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/roles/{id}") 
	ResponseEntity<Mensaje> delete(@PathVariable("id") int idRol) {
        return service.deleteById(idRol);
	}

}
