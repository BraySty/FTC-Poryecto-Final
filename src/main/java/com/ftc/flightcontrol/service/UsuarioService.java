package com.ftc.flightcontrol.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ftc.flightcontrol.entitys.Mensaje;
import com.ftc.flightcontrol.entitys.Usuario;
import com.ftc.flightcontrol.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

	private UsuarioRepository repo;

	private String userEmailMsg = "El usuario con correo: ";

	public ResponseEntity<Mensaje> save(Usuario usuario) {
		Optional<Usuario> posibleUsuario = repo.findByCorreo(usuario.getCorreo());
		if (posibleUsuario.isPresent()) {
			return new ResponseEntity<>(new Mensaje(userEmailMsg + usuario.getCorreo() + " ya existe"),
					HttpStatus.CONFLICT);
		} else { // Que pasa si no existe
			repo.save(usuario);
			return new ResponseEntity<>(new Mensaje(userEmailMsg), HttpStatus.OK);
		}
	}

	public ResponseEntity<?> read(String correoUsuario) {
		Optional<Usuario> posibleUsuario = repo.findByCorreo(correoUsuario);
		if (posibleUsuario.isPresent()) {
			return new ResponseEntity<>(posibleUsuario.get(), HttpStatus.OK);
		} else { // Que pasa si no existe
			return new ResponseEntity<>(new Mensaje(userEmailMsg + correoUsuario + " no existe"), HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<?> read() {
		List<Usuario> posibleUsuario = repo.findAll();
		if (!posibleUsuario.isEmpty()) {
			return new ResponseEntity<>(posibleUsuario, HttpStatus.OK);
		} else { // Que pasa si no existe
			return new ResponseEntity<>(new Mensaje("No hay ningun usuario."), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 *  TODO encriptar contraseña
	 * @param correoUsuario
	 * @param passwordUsuario
	 * @param usuario
	 * @return
	 */
	public ResponseEntity<Mensaje> update(String correoUsuario, String passwordUsuario, Usuario usuario) {
		Optional<Usuario> posibleUsuario = repo.findByCorreo(correoUsuario);
		if (posibleUsuario.isPresent() && posibleUsuario.get().getPassword().equals(passwordUsuario)) {
			repo.delete(posibleUsuario.get());
			repo.save(usuario);
			return new ResponseEntity<>(new Mensaje("El usuario fue actualizado."), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Mensaje(userEmailMsg + correoUsuario + " no existe."),
					HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Mensaje> delete(String correoUsuario) {
		Optional<Usuario> posibleUsuario = repo.findByCorreo(correoUsuario);
		if (posibleUsuario.isPresent()) {
			repo.delete(posibleUsuario.get());
			return new ResponseEntity<>(new Mensaje("Se ha borrado el usuario con id: " + correoUsuario),
					HttpStatus.OK);
		} else { // Que pasa si no existe
			return new ResponseEntity<>(new Mensaje(userEmailMsg + correoUsuario + " no existe"), HttpStatus.NOT_FOUND);
		}
	}

}
