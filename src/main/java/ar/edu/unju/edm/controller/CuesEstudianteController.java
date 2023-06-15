package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.CuesEstudiante;
import ar.edu.unju.edm.service.ICuesEstudianteService;
import ar.edu.unju.edm.service.ICuestionarioService;
import ar.edu.unju.edm.service.IEstudianteService;
import jakarta.validation.Valid;

@Controller
public class CuesEstudianteController {
	
	@Autowired
	ICuesEstudianteService cuesEstudianteService;
	
	@Autowired
	IEstudianteService estudianteService;

	@Autowired
	ICuestionarioService cuestionarioService;
	
	@Autowired
	CuesEstudiante unCuesEstudiante;
	
	
	@GetMapping("/realizarCuestionarioE")
	public ModelAndView cargarCuesEstudiante () {
		ModelAndView cargaCuesEstudiante = new ModelAndView("formularioCuesEstudiante");
		cargaCuesEstudiante.addObject("nuevoCuesEstud", unCuesEstudiante);
		cargaCuesEstudiante.addObject("estudiante", estudianteService.listarEstudiantes());
		cargaCuesEstudiante.addObject("cuestionario", cuestionarioService.listarCuestionarios());
		
		return cargaCuesEstudiante;
	}
	
	@PostMapping("/guardarCuestionarioERealizado")
	public ModelAndView guardarCuesEstudiante (@Valid @ModelAttribute("cuesEstudiante") CuesEstudiante cuestionarioHecho, BindingResult resultado) {
		
		if(resultado.hasErrors()) {
			ModelAndView cargaCuesEstudiante = new ModelAndView("formularioCuesEstudiante");
			cargaCuesEstudiante.addObject("nuevoCuesEstud", cuestionarioHecho);
			
			return cargaCuesEstudiante;
		}
		
		ModelAndView listadoCuesEstudiante = new ModelAndView("mostrarCuesEstudiante");
		
		try {
			cuesEstudianteService.cargarCuesEstudiante(cuestionarioHecho);
		}catch(Exception e) {
			listadoCuesEstudiante.addObject("cargarCuestionarioEstudianteErrorMessage", e.getMessage());
		}
		
		listadoCuesEstudiante.addObject("cuesEstudianteListado", cuesEstudianteService.listarTodosCuestionariosEstudiantes() );
		
		return listadoCuesEstudiante;
	}
	
}
