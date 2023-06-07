package ar.edu.unju.edm.controller;

import org.apache.juli.logging.LogFactory;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Estudiante;
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
		ModelAndView cargaEstudiante = new ModelAndView("formEstudiante");
		cargaEstudiante.addObject("nuevoEstudiante", unEstudiante);
		return cargaEstudiante;
	}
	
	@PostMapping("/estudianteGuardado")
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
}
