package ar.edu.unju.edm.controller;

import org.apache.juli.logging.LogFactory;

import java.io.IOException;
import java.util.Base64;

import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Estudiante;
import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.service.IEstudianteService;

@Controller
public class EstudianteController {

	private static final Log Group3 = LogFactory.getLog(EstudianteController.class);
	
	
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
		return cargaEstudiante;
	}
	
	@PostMapping("/guardarEstudiante")
	public ModelAndView guardarEstudiante(@ModelAttribute("nuevoEstudiante") Estudiante unEstudiante ) {
		ModelAndView listadoEstudiantes = new ModelAndView("mostrarEstudiantes");
		
			Group3.warn("Mostrando nuevo Estudiante"+unEstudiante.getNombre());
		
		try {
			unServicio.cargarEstudiante(unEstudiante);
		}catch(Exception e) {
			
		}
		
		listadoEstudiantes.addObject("estudianteListado", unServicio.listarEstudiantes());
		
		return listadoEstudiantes;
	}
	
	@GetMapping("/modificarEstudiante/{id_Estudiante}")
	public ModelAndView modificarEstudiante(@PathVariable(name="id_Estudiante") Integer id_Estudiante ) {
		
		ModelAndView modificaEstudiante = new ModelAndView("formularioEstudiante");
	
		try {
			modificaEstudiante.addObject("estudianteModificar", unServicio.mostrarUnEstudiante(id_Estudiante));
		}catch(Exception e) {
			modificaEstudiante.addObject("Error al modificar producto", e.getMessage());
		}
		modificaEstudiante.addObject("band", true);
		return modificaEstudiante;
	}
	
	@PostMapping("/modificarEstudiante")
	public ModelAndView modificarEstudiante(@ModelAttribute("nuevoEstudiante") Estudiante unEstudiante ) {
		ModelAndView listadoEditado = new ModelAndView("mostrarEstudiantes");
		
			Group3.warn("Mostrando Estudiante modificado"+unEstudiante.getNombre());
		
		try {
			unServicio.cargarEstudiante(unEstudiante);
		}catch(Exception e) {
			
		}
		
		listadoEditado.addObject("estudianteListado", unServicio.listarEstudiantes());
		
		return listadoEditado;
	}
	
	@GetMapping("/eliminarrEstudiante/{id_Estudiante}")
	public ModelAndView eliminarEstudiante(@PathVariable(name="id_Estudiante") Integer id_Estudiante ) {
		ModelAndView cargaEstudiante = new ModelAndView("formularioEstudiante");
		try {
			unServicio.eliminarUnEstudiante(id_Estudiante);
		}catch(Exception e) {
			cargaEstudiante.addObject("error al eliminar un estudainte", e.getMessage());
		}
		try {
			cargaEstudiante.addObject("listado", unServicio.listarEstudiantes() );
		}catch(Exception e) {
			cargaEstudiante.addObject("error de listado", e.getMessage());
		
		return cargaEstudiante;
	}
	
}
