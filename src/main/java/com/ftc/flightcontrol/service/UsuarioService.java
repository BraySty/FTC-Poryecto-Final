package com.ftc.flightcontrol.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private UsuarioRepository repo;

	private String userEmailMsg = "El usuario con correo: ";

	public ResponseEntity<Mensaje> save(Usuario usuario) {
		Optional<Usuario> busqueda = repo.findByCorreo(usuario.getCorreo());
		String correo = usuario.getCorreo();
		if (busqueda.isPresent()) {
			return new ResponseEntity<>(new Mensaje(userEmailMsg + correo + " ya existe"),
					HttpStatus.CONFLICT);
		} else {
			repo.save(usuario);
			return new ResponseEntity<>(new Mensaje(userEmailMsg + correo + " ha sido creado"), HttpStatus.CREATED);
		}
	}

	public ResponseEntity<?> read() {
		List<Usuario> lista = repo.findAll();
		if (!lista.isEmpty()) {
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Mensaje("No hay ningun usuario."), HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<?> read(String correoUsuario) {
		Optional<Usuario> busqueda = repo.findByCorreo(correoUsuario);
		if (busqueda.isPresent()) {
			return new ResponseEntity<>(busqueda.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Mensaje(userEmailMsg + correoUsuario + " no existe"), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * TODO encriptar contraseña
	 * 
	 * @param correoUsuario
	 * @param passwordUsuario
	 * @param usuario
	 * @return
	 */
	public ResponseEntity<Mensaje> update(String correoUsuario, String passwordUsuario, Usuario usuario) {
		Optional<Usuario> update = repo.findByCorreo(correoUsuario);
		if (update.isPresent() && update.get().getPassword().equals(passwordUsuario)) {
			update.get().setNombre(usuario.getNombre());
			update.get().setApellido(usuario.getApellido());
			update.get().setCorreo(usuario.getCorreo());
			update.get().setPassword(usuario.getPassword());
			update.get().setRole(usuario.getRole());
			repo.save(usuario);
			return new ResponseEntity<>(new Mensaje("El usuario fue actualizado."), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Mensaje(userEmailMsg + correoUsuario + " no existe."),
					HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Mensaje> delete(String correoUsuario) {
		Optional<Usuario> delete = repo.findByCorreo(correoUsuario);
		if (delete.isPresent()) {
			repo.delete(delete.get());
			return new ResponseEntity<>(new Mensaje("Se ha borrado el usuario con id: " + correoUsuario),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Mensaje(userEmailMsg + correoUsuario + " no existe"), HttpStatus.NOT_FOUND);
		}
	}

}
