package com.testing.demo;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import com.testing.demo.Models.interfaces.Repository.EmpleadoRepositorio;
import com.testing.demo.Models.interfaces.Repository.PersonaRepositorio;
import com.testing.demo.Models.interfaces.Repository.UsuarioRepositorio;

@SpringBootApplication
// @EnableJpaRepositories(" com.testing.demo.Models.interfaces.Implementacion")
// @RestController // esto hace que nuestra clase reciba restpoints
public class App {
	private static EmpleadoRepositorio empleadoRepositorio;
	private static ConfigurableApplicationContext configuracion;
	// private static Controlador;

	public static void main(String[] args) {
		// SpringApplication.run(App.class, args);

		configuracion = SpringApplication.run(App.class,
				args);
		empleadoRepositorio = configuracion.getBean(EmpleadoRepositorio.class);

	}

	public EmpleadoRepositorio getEmpleadoRepositorio() {
		return this.empleadoRepositorio;
	}

}
