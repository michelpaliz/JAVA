package com.testing.demo.Models.Controlers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.Lib.Control;
import com.github.javafaker.Faker;
import com.testing.demo.App;
import com.testing.demo.Models.Empleado;
import com.testing.demo.Numeric.EProfesion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//Configuracion
import com.testing.demo.Models.Config.Config;
import com.testing.demo.Models.interfaces.Implementacion.EmpleadoServicioImp;

// @Slf4j
@Controller
// @SessionAttributes("empleado")
public class ControladorEmpleado {
	@Autowired
	private EmpleadoServicioImp empleadoServicio;
	private Empleado empleado;
	// private List<Empleado> empleados;

	@ModelAttribute("empleados")
	public String crearDatosRandom() {
		if (empleadoServicio.listarEmpleado().size() < Config.INICIALIZADOR) {
			Faker fk = new Faker();
			Date fechaMaxima = new GregorianCalendar(1999, Calendar.FEBRUARY, 11).getTime();
			Date fechaMinima = new GregorianCalendar(1980, Calendar.FEBRUARY, 11).getTime();
			EProfesion profesion = EProfesion.jardinero;
			for (int i = 0; i < Config.INICIALIZADOR; i++) {
				String formato = fk.number().digits(8);
				String dni = Control.DNIgeneratorChar(formato);
				String nombre = fk.name().firstName();
				String email = fk.name().username() + "@homtail.com";
				Date date = fk.date().between(fechaMinima, fechaMaxima);
				LocalDate fechaNacimiento = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				// Integer edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
				profesion = profesion.getRandom();
				long antiguedad = fk.random().nextInt(0, 10);
				empleado = new Empleado(dni, nombre, email, fechaNacimiento, profesion, antiguedad);
				empleadoServicio.guardarEmpleado(empleado);
			}
			return empleadoServicio.listarEmpleado().toString();
		}
		return empleadoServicio.listarEmpleado().toString();
	}

	/**
	 * mostramos todos los empleados
	 * 
	 * @param model
	 * @return el indice de la api rest
	 */
	@GetMapping("/mostrarListaEmpleado")
	public String mostrarEmpleadosTodos(Model model) {
		model.addAttribute("empleados", empleadoServicio.listarEmpleado());
		return "mostrarEmpleado";
	}

	/**
	 * creamos random empleados
	 * 
	 * @param model
	 * @return el indice de la api rest
	 */
	@GetMapping("/crearRandomEmpleado")
	public String mostrarRandom(Model model) {
		crearDatosRandom();
		model.addAttribute("empleados", empleadoServicio.listarEmpleado());
		return "mostrarEmpleado";
	}

	/**
	 * Esta funcion me crea un nuevo empleado y me lleva al registro.html
	 * 
	 * @param model
	 * @return el registro del nuevo empleado creado si es true
	 */
	@GetMapping("/registrarEmpleado")
	public String mostrarFormularioAnyadir(Model model) {
		model.addAttribute("nuevoEmpleado", new Empleado());
		model.addAttribute("tipoProfesion", EProfesion.values());
		return "registroEmpleado";
	}

	/**
	 * Editamos el empleado que seleccinemos
	 * 
	 * @param id
	 * @param model
	 * @return el formulario para editarlo
	 */

	@GetMapping("/editarEmpleado/{id}")
	public ModelAndView mostrarFormularioEditar(@PathVariable(name = "id") Long id, Model model) {
		// model.addAttribute("empleado", empleadoServicio.obtenerEmpleado(id));
		// return "editarEmpleado";
		ModelAndView modelo = new ModelAndView("editarEmpleado");
		Empleado empleado = empleadoServicio.obtenerEmpleado(id);
		modelo.addObject("empleado", empleado);
		model.addAttribute("tipoProfesion", EProfesion.values());
		return modelo;
	}

	/**
	 * 
	 * @param id
	 * @return la vista del listado de los empleados
	 */

	@GetMapping("/eliminarEmpleado/{id}")
	public String eliminarEmpleado(@PathVariable(name = "id") Long id, Model model) {
		Empleado empleado = empleadoServicio.obtenerEmpleado(id);
		empleadoServicio.eliminarEmpleado(empleado.getId());
		// si el model attribute no esta no recogeria o encontraria el atributo en la
		// vista o formulario.
		model.addAttribute("empleados", empleadoServicio.listarEmpleado());
		return "mostrarEmpleado";
	}

	// *POSTMAPPING */
	/**
	 * Actuzliazar empleado
	 * 
	 * @param id
	 * @param empleado del formulario
	 * @param model
	 * @return
	 */
	@PostMapping("/editarEmpleado")
	public String actualizarEmpleado(@ModelAttribute("empleado") Empleado empleado,
			Model model, Errors error) {
		if (error.hasErrors()) {
			model.addAttribute("tipoProfesion", EProfesion.values());// Cogeme todos los valores de la profesion.
			return "/editarEmpleado";
		}
		if (empleadoServicio.getEmpleadoRepositorio() != empleado) {
			empleado.setEdad(empleado.calcularEdad());
			empleado.setDni(empleado.getDni());
			empleado.setEdad(empleado.getEdad());
			empleado.setEmail(empleado.getEmail());
			empleado.setId(empleado.getId());
			empleado.setFechaNacimiento(empleado.getFechaNacimiento());
			empleado.setNombre(empleado.getNombre());
			empleado.setProfesion(empleado.getProfesion());
			empleadoServicio.guardarEmpleado(empleado);
		} else {
			empleadoServicio.guardarEmpleado(null);
		}
		return "redirect:/mostrarListaEmpleado";
	}

	/**
	 * Este post recibe los parametros establecidos para crear un empleado
	 * 
	 * @param empleado
	 * @param errors
	 * @param model
	 * @return el getMapping donde se muestran los empleados.
	 */
	@PostMapping("/registroEmpleado")
	public String registrarEmpleado(@Valid @ModelAttribute("nuevoEmpleado") Empleado empleado, Errors errors,
			Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("tipoProfesion", EProfesion.values());// Cogeme todos los valores de la profesion.
			return "/registroEmpleado";
		}
		empleado.setEdad(empleado.calcularEdad()); // calculamos la edad llamando el metodo para que lo realize.
		empleadoServicio.guardarEmpleado(empleado);
		System.out.println(empleado);
		// System.out.println(empleado.getFechaNacimiento().getYear());
		// log.info("Peticion enviada " + empleado);
		// log.info("Procesando el registro" + empleado);
		return "redirect:/mostrarListaEmpleado";
	}

	// public void clasificarTipoProfesion() {
	// // for (Empleado empleado : empleados) {
	// // // model.addAttribute("empleados",
	// clasificarTipo(empleado.getProfesion()));
	// // }

	// }

	// @RequestMapping(value= "add", method = RequestMethod.GET)
	// public String tipoProfesion(Model model){
	// model.addAttribute("titulo", "nuevoEmpleado");
	// model.addAttribute("tipoProfesion", EProfesion.values());
	// }

}
