package com.ftc.flightcontrol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.ftc.flightcontrol.entitys.Role;
import com.ftc.flightcontrol.repository.RoleRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)

class FlightcontrolRoleTests {

	@Autowired
	private RoleRepository Repo;

	private int numberTest = 1;

	@Test
	@Order(1)
	@Rollback(false)
	void test1InsertarClase() {
		String descripcion = "Prueba role #" + numberTest;
		Role clase = new Role(descripcion);
		Role guardado = Repo.save(clase);
		assertNotNull(guardado);
	}

	@Test
	@Order(2)
	@Rollback(true)
	void test2InsertarClaseRepetida() {
		String descripcion = "Prueba role #" + numberTest;
		Role clase = new Role(descripcion);
		Exception exception = assertThrows(org.springframework.dao.DataIntegrityViolationException.class, () -> {
			Repo.save(clase);
		});
		String expectedMessage = "Duplicate entry";
    	String actualMessage = exception.getMessage();
    	assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	@Order(3)
	@Rollback(false)
	void test3BuscarClasePorDescripcion() {
		String descripcion = "Prueba role #" + numberTest;
		Role buscar = Repo.findFirstByDescripcion(descripcion).get();
		assertNotNull(buscar);
		assertEquals(descripcion, buscar.getDescripcion());
	}

	@Test
	@Order(4)
	@Rollback(false)
	void test4ActualizarClasePorDescripcion() {
		String descripcion = "Prueba role #" + numberTest;
		String descripcionOriginal = null;
		String descripcionActualizada = "Prueba role actualizada #" + numberTest;
		Role actualizar = Repo.findFirstByDescripcion(descripcion).get();
		descripcionOriginal = actualizar.getDescripcion();
		actualizar.setDescripcion(descripcionActualizada);
		Repo.save(actualizar);
		actualizar = Repo.findFirstByDescripcion(descripcionActualizada).get();
		assertNotEquals(descripcionOriginal, actualizar.getDescripcion());
	}

	@Test
	@Order(5)
	@Rollback(false)
	void test5EliminarClasePorDescripcion() {
		String descripcion = "Prueba role actualizada #" + numberTest;
		Role eliminar = null;
		Repo.deleteFirstByDescripcion(descripcion);
		eliminar = Repo.findFirstByDescripcion(descripcion).orElse(null);
		assertNull(eliminar);
	}

}
