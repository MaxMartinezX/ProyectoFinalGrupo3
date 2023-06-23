package ar.edu.unju.edm.controller;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Cuestionario;
import ar.edu.unju.edm.service.ICuesPreguntaService;
import ar.edu.unju.edm.service.ICuestionarioService;
import ar.edu.unju.edm.service.IDocenteService;
import javax.validation.Valid;

@Controller
public class CuestionarioController {
	
 private static final Log GRUPO3 = LogFactory.getLog(CuestionarioController.class);
	
	
	@Autowired
	Cuestionario unCuestionario;
	
	@Autowired
	ICuestionarioService cuestionarioService;
	@Autowired
	IDocenteService docenteService;
	@Autowired
	ICuesPreguntaService cuesPreguntaService;
	
	@GetMapping("/cuestionario")
	public ModelAndView cargarCuestionario() {
		ModelAndView cargaCuestionario = new ModelAndView("formularioCuestionario");
		cargaCuestionario.addObject("nuevoCuestionario", unCuestionario);
		cargaCuestionario.addObject("listadoDocentes", docenteService.listarDocentes());
	    GRUPO3.warn("Cargando nuevo cuestionario");
		return cargaCuestionario;
	}
	
	@PostMapping("/guardarCuestionario")
	public ModelAndView guardarCuestionario(@Valid @ModelAttribute("nuevoCuestionario") Cuestionario unCuestionario, BindingResult resultado ) {
		if(resultado.hasErrors()) {
			ModelAndView cargaCuestionario = new ModelAndView("formularioCuestionario");
			cargaCuestionario.addObject("nuevoCuestionario", unCuestionario);
			return cargaCuestionario;
		}
		
		ModelAndView listadoCuestionarios = new ModelAndView("mostrarCuestionarios");
		
			GRUPO3.warn("Mostrando nuevo Cuestionario"+unCuestionario.getId_Cuestionario());
		
		try {
			cuestionarioService.cargarCuestionario(unCuestionario);
		}catch(Exception e) {
			listadoCuestionarios.addObject("cargarCuestionarioErrorMessage", e.getMessage());
			GRUPO3.error(e);
		}
		
		listadoCuestionarios.addObject("cuestionarioListado", cuestionarioService.listarCuestionarios());
		
		return listadoCuestionarios;
	}
	
	@GetMapping("/listadoCuestionarios")
	public ModelAndView mostrarCuestionarios(){
		GRUPO3.warn("Lista de Cuestionarios");
		ModelAndView listadoCuestionarios = new ModelAndView("mostrarCuestionarios");
		listadoCuestionarios.addObject("cuestionarioListado", cuestionarioService.listarCuestionarios());
		
		return listadoCuestionarios;
	}
	
	//CUESTIONARIO CON PREGUNTAS
	
	@PostMapping("/cuestionarioConPreguntas/{id_Cuestionario}")
	public ModelAndView guardarCuestionarioConPreguntas(@PathVariable(name="id_Cuestionario") Integer id) {
		
		ModelAndView listadoCuestionarios = new ModelAndView("mostrarCuestionarios");
		
		GRUPO3.warn("Cuestionario con preguntas: " + unCuestionario.getTitulo());
		try {
			listadoCuestionarios.addObject("preguntas", cuesPreguntaService.ListarPreguntasDeUnCuestionario(id));
		}catch(Exception e) {
			listadoCuestionarios.addObject("cargaCuestionarioConPreguntasErrorMessage", e.getMessage());
			GRUPO3.error(e);
		}
		
		listadoCuestionarios.addObject("cuestionarioListado", cuestionarioService.listarCuestionarios());
		
		return listadoCuestionarios;
	}
	

}
