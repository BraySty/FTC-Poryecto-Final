package com.ftc.flightcontrol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.ftc.flightcontrol.entitys.Vuelo;
import com.ftc.flightcontrol.repository.VueloRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)

class FlightcontrolVueloTests {

	@Autowired
	private VueloRepository repo;

	@Test
	@Order(1)
	@Rollback(false)
	void test1InsertarClase() {
		String id = "XXY12";
		Vuelo clase = new Vuelo(id, "Columbia", new Date(), "Madrid", new Date(), null, null, null);
		Vuelo guardado = repo.save(clase);
		assertNotNull(guardado);
	}

	@Test
	@Order(2)
	@Rollback(false)
	void test2BuscarClasePorDescripcion() {
		String id = "XXY12";
		Vuelo buscar = repo.findById(id).get();
		assertNotNull(buscar);
		assertEquals(id, buscar.getId());
	}

	@Test
	@Order(3)
	@Rollback(false)
	void test3ActualizarClasePorDescripcion() {
		String id = "XXY12";
		Vuelo actualizar  = repo.findById(id).get();
		String salida = actualizar.getSalida();
		String llegada = actualizar.getLlegada();
		actualizar.setSalida("Tokio");
		actualizar.setLlegada("Paris");
		repo.save(actualizar );
		actualizar  = repo.findById(id).get();
		assertNotEquals(salida, actualizar .getSalida());
		assertNotEquals(llegada, actualizar.getLlegada());
	}

	@Test
	@Order(4)
	@Rollback(false)
	void test4EliminarClasePorDescripcion() {
		String id = "XXY12";
		Vuelo eliminar = null;
		repo.deleteById(id);
		eliminar = repo.findById(id).orElse(null);
		assertNull(eliminar);
	}

}
