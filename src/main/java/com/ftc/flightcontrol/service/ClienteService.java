package com.ftc.flightcontrol.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ftc.flightcontrol.entitys.Cliente;
import com.ftc.flightcontrol.entitys.Mensaje;
import com.ftc.flightcontrol.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	private String userEmailMsg = "El cliente con correo: ";

	/**
	 * Metodo para guardar un Cliente dentro de la BBDD.
	 * 
	 * @param cliente El Cliente a guardar.
	 * @return ResponseEntity<> con el estado de la operacion.
	 */
	public ResponseEntity<Mensaje> save(Cliente cliente) {
		Optional<Cliente> busqueda = repo.findByCorreo(cliente.getCorreo());
		String correo = cliente.getCorreo();
		if (busqueda.isPresent()) {
			return new ResponseEntity<>(new Mensaje(userEmailMsg + correo + " ya existe"),
					HttpStatus.CONFLICT);
		} else {
			repo.save(cliente);
			return new ResponseEntity<>(new Mensaje(userEmailMsg + correo + " ha sido creado"), HttpStatus.CREATED);
		}
	}

	/**
	 * Metodo para buscar registros dentro de la BBDD.
	 * 
	 * @return ResponseEntity<> con List<Cliente> con todos los registros o
	 *         ResponseEntity<> con un Mensaje en caso de estar vacia.
	 */
	public ResponseEntity<?> read() {
		List<Cliente> lista = repo.findAll();
		if (!lista.isEmpty()) {
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Mensaje("No hay ningun cliente."), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Metodo para buscar un registro dentro de la BBDD a travez de su ID.
	 * 
	 * @param correoUsuario String con el ID a buscar.
	 * @return ResponseEntity<> con el registro en forma de Cliente o
	 *         ResponseEntity<> con un Mensaje en caso de no existir.
	 */
	public ResponseEntity<?> read(String correoUsuario) {
		Optional<Cliente> busqueda = repo.findByCorreo(correoUsuario);
		if (busqueda.isPresent()) {
			return new ResponseEntity<>(busqueda.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Mensaje(userEmailMsg + correoUsuario + " no existe"), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Metodo para actualizar un registro dentro de la BBDD a travez de su ID.
	 * TODO encriptar contraseña
	 * 
	 * @param correoUsuario
	 * @param passwordUsuario
	 * @param cliente         Cliente con los datos a actualizar.
	 * @return ResponseEntity<> con el estado de la operacion.
	 */
	public ResponseEntity<Mensaje> update(String correoUsuario, String passwordUsuario, Cliente cliente) {
		Optional<Cliente> update = repo.findByCorreo(correoUsuario);
		if (update.isPresent() && update.get().getPassword().equals(passwordUsuario)) {
			update.get().setNombre(cliente.getNombre());
			update.get().setApellido(cliente.getApellido());
			update.get().setCorreo(cliente.getCorreo());
			update.get().setPassword(cliente.getPassword());
			update.get().setRole(cliente.getRole());
			repo.save(cliente);
			return new ResponseEntity<>(new Mensaje("El cliente fue actualizado."), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Mensaje(userEmailMsg + correoUsuario + " no existe."),
					HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Metodo para eliminar un registro dentro de la BBDD a travez de su ID.
	 * 
	 * @param correoUsuario String con el ID a eliminar.
	 * @return ResponseEntity<> con el estado de la operacion.
	 */
	public ResponseEntity<Mensaje> delete(String correoUsuario) {
		Optional<Cliente> delete = repo.findByCorreo(correoUsuario);
		if (delete.isPresent()) {
			repo.delete(delete.get());
			return new ResponseEntity<>(new Mensaje("Se ha borrado el cliente con id: " + correoUsuario),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Mensaje(userEmailMsg + correoUsuario + " no existe"), HttpStatus.NOT_FOUND);
		}
	}

}
