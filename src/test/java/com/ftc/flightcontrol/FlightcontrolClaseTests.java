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

import com.ftc.flightcontrol.entitys.Clase;
import com.ftc.flightcontrol.repository.ClaseRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)

class FlightcontrolClaseTests {

	@Autowired
	private ClaseRepository Repo;

	private int numberTest = 1;

	@Test
	@Order(1)
	@Rollback(false)
	void test1InsertarClase() {
		String descripcion = "Prueba clase #" + numberTest;
		Clase clase = new Clase(descripcion);
		Clase guardado = Repo.save(clase);
		assertNotNull(guardado);
	}

	@Test
	@Order(2)
	@Rollback(true)
	void test2InsertarClaseRepetida() {
		String descripcion = "Prueba clase #" + numberTest;
		Clase clase = new Clase(descripcion);
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
		String descripcion = "Prueba clase #" + numberTest;
		Clase buscar = Repo.findFirstByDescripcion(descripcion).get();
		assertNotNull(buscar);
		assertEquals(descripcion, buscar.getDescripcion());
	}

	@Test
	@Order(4)
	@Rollback(false)
	void test4ActualizarClasePorDescripcion() {
		String descripcion = "Prueba clase #" + numberTest;
		String descripcionOriginal = null;
		String descripcionActualizada = "Prueba clase actualizada #" + numberTest;
		Clase actualizar = Repo.findFirstByDescripcion(descripcion).get();
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
		String descripcion = "Prueba clase actualizada #" + numberTest;
		Clase eliminar = null;
		Repo.deleteFirstByDescripcion(descripcion);
		eliminar = Repo.findFirstByDescripcion(descripcion).orElse(null);
		assertNull(eliminar);
	}

}
