package com.ftc.flightcontrol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.ftc.flightcontrol.entitys.usuarios.Usuario;
import com.ftc.flightcontrol.repository.UsuarioRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)

class FlightcontrolUsuarioTests {

	@Autowired
	private UsuarioRepository Repo;

	private int numberTest = 1;

	@Test
	@Order(1)
	@Rollback(false)
	void test1InsertarUsuario() {
		String dni = "Prueba clase #" + numberTest;
		Usuario clase = new Usuario(dni,"Nombre","Apellido","Correo","Password", null, null);
		Usuario guardado = Repo.save(clase);
		assertNotNull(guardado);
	}

	@Test
	@Order(2)
	@Rollback(false)
	void test2BuscarUsuarioPorDescripcion() {
		String dni = "Prueba clase #" + numberTest;
		Usuario buscar = Repo.findById(dni).get();
		assertNotNull(buscar);
		assertEquals(dni, buscar.getDni());
	}

	@Test
	@Order(3)
	@Rollback(false)
	void test3ActualizarUsuarioPorDescripcion() {
		String dni = "Prueba clase #" + numberTest;
		String dniOriginal = null;
		String dniActualizada = "Prueba clase actualizada #" + numberTest;
		Usuario actualizar = Repo.findById(dni).get();
		dniOriginal = actualizar.getDni();
		actualizar.setDni(dniActualizada);
		Repo.save(actualizar);
		actualizar = Repo.findById(dniActualizada).get();
		assertNotEquals(dniOriginal, actualizar.getDni());
	}

	@Test
	@Order(4)
	@Rollback(false)
	void test4EliminarUsuarioPorDescripcion() {
		String dni = "Prueba clase actualizada #" + numberTest;
		Usuario eliminar = null;
		Repo.deleteById(dni);
		eliminar = Repo.findById(dni).orElse(null);
		assertNull(eliminar);
	}

}
