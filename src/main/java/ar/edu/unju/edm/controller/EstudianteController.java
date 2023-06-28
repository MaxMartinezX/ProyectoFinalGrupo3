package ar.edu.unju.edm.controller;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Estudiante;
import ar.edu.unju.edm.service.IEstudianteService;
import javax.validation.Valid;

@Controller
public class EstudianteController {

	private static final Log GRUPO3 = LogFactory.getLog(EstudianteController.class);
	
	
	@Autowired
	Estudiante unEstudiante;
	
	@Autowired
	@Qualifier("servicioEstudianteEnMySQL")
	IEstudianteService unServicio;
	
	@GetMapping("/estudiante")
	public ModelAndView cargarEstudiante() {
		ModelAndView cargaEstudiante = new ModelAndView("formularioEstudiante");
		cargaEstudiante.addObject("nuevoEstudiante", unEstudiante);
		
		 char[] divisiones = {'A', 'B', 'C', 'D'};
		 cargaEstudiante.addObject("listaDivisiones", divisiones);
		 
		 cargaEstudiante.addObject("band", false);
		 GRUPO3.warn("Cargando nuevo estudiante");
		return cargaEstudiante;
	}
	
	@GetMapping("/listadoEstudiante")
	public ModelAndView mostrarEstudiante(){
		
		ModelAndView listadoEstudiantes = new ModelAndView("mostrarEstudiantes");
		listadoEstudiantes.addObject("estudianteListado", unServicio.listarEstudiantes());
		
		return listadoEstudiantes;
	}
	
	@GetMapping("/listaDeEstudiantes")
	public ModelAndView mostrarEstudiantes(){
		
		ModelAndView listaDeEstudiantes = new ModelAndView("listaDeEstudiantes");
		listaDeEstudiantes.addObject("estudianteListado", unServicio.listarEstudiantes());
		
		return listaDeEstudiantes;
	}
	
	@PostMapping("/guardarEstudiante")
	public ModelAndView guardarEstudiante(@Valid @ModelAttribute("nuevoEstudiante") Estudiante nEstudiante, BindingResult resultado) {
		
		if(resultado.hasErrors()) {
			ModelAndView cargaEstudiante = new ModelAndView("formularioEstudiante");
			cargaEstudiante.addObject("nuevoEstudiante", nEstudiante);
			return cargaEstudiante;
		}
		
		ModelAndView listadoEstudiantes = new ModelAndView("mostrarEstudiantes");
		
			GRUPO3.warn("Mostrando nuevo Estudiante"+nEstudiante.getNombre());
		
		try {
			GRUPO3.warn("Guardando Estudiante");
			unServicio.cargarEstudiante(nEstudiante);
		}catch(Exception e) {
			listadoEstudiantes.addObject("CargadoEstudianteErrorMessage", e.getMessage());
			GRUPO3.error(e);
		}
				
		listadoEstudiantes.addObject("estudianteListado", unServicio.listarEstudiantes());
		
		return listadoEstudiantes;
	}
	
	//MODIFICAR
	
	@GetMapping("/modificarEstudiante/{id_Estudiante}")
	public ModelAndView modificarEstudiante(@PathVariable(name="id_Estudiante") Integer id_Est ) {
		
		ModelAndView modificaEstudiante = new ModelAndView("formularioEstudiante");
	
		try {
			GRUPO3.warn("Modificando Estudiante");
			modificaEstudiante.addObject("nuevoEstudiante", unServicio.mostrarUnEstudiante(id_Est));
		}catch(Exception e) {
			modificaEstudiante.addObject("modificarEstudianteErrorMessage", e.getMessage());
			GRUPO3.error(e);
		}
		
		 char[] divisiones = {'A', 'B', 'C', 'D'};
		 modificaEstudiante.addObject("listaDivisiones", divisiones);
		 
		modificaEstudiante.addObject("band", true);
		return modificaEstudiante;
	}
	
	@PostMapping("/modificarEstudiante")
	public ModelAndView modificarEstudiante(@ModelAttribute("nuevoEstudiante") Estudiante unEstudiante ) {
		ModelAndView listadoEditado = new ModelAndView("mostrarEstudiantes");
		
			GRUPO3.warn("Mostrando Estudiante modificado: "+unEstudiante.getId_Estudiante());
		
		try {
			GRUPO3.warn("Guardando modificaciones de Estudiantes");
			unServicio.cargarEstudiante(unEstudiante);
		}catch(Exception e) {
			listadoEditado.addObject("cargaEstudianteErrorMessage", e.getMessage());
			GRUPO3.error(e);
		}
		
		listadoEditado.addObject("estudianteListado", unServicio.listarEstudiantes());
		
		return listadoEditado;
	}
	
	// ELIMINAR
	@GetMapping("/eliminarEstudiante/{id_Estudiante}")
	public ModelAndView eliminarEstudiante(@PathVariable(name="id_Estudiante") Integer id) {
		ModelAndView eliminarEstudiante = new ModelAndView("mostrarEstudiantes");
		
		try {
			GRUPO3.warn("Eliminando Estudiante");
			unServicio.eliminarUnEstudiante(id);
		}catch(Exception e) {
			eliminarEstudiante.addObject("eliminarEstudianteErrorMessage", e.getMessage());
			GRUPO3.error(e);
		}
		
		try {
			eliminarEstudiante.addObject("estudianteListado", unServicio.listarEstudiantes());
		}catch(Exception e) {
			eliminarEstudiante.addObject("listarEstudianteErrorMessage", e.getMessage());
			GRUPO3.error(e);
		}
		
		return eliminarEstudiante;
	}
	
	
}
