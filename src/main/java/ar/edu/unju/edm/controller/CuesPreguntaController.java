package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.CuesPregunta;
import ar.edu.unju.edm.service.ICuesPreguntaService;
import ar.edu.unju.edm.service.ICuestionarioService;
import ar.edu.unju.edm.service.IPreguntaService;
import jakarta.validation.Valid;

@Controller
public class CuesPreguntaController {

	@Autowired
	ICuesPreguntaService cuesPreguntaService;
	@Autowired
	IPreguntaService preguntaService;
	@Autowired
	ICuestionarioService cuestionarioService;
	@Autowired
	CuesPregunta unCuesPregunta;
	
	@GetMapping("/CuestionarioPregunta/{id_Cuestionario}")
	public ModelAndView cargarCuesPregunta(@PathVariable(name="id_Cuestionario") Integer id) {
		
		ModelAndView unCuesP= new ModelAndView("formularioCuesPregunta");
		
		unCuesP.addObject("cuesPregunta", unCuesPregunta);
		unCuesP.addObject("listadoPreguntas", preguntaService.listarPreguntas());
		
		return unCuesP;
	}
	
	@PostMapping("/guardarCuestionarioPregunta")
	public ModelAndView guardarCuesPregunta(@Valid @ModelAttribute("cuesPregunta") CuesPregunta CuestionarioP, BindingResult resultados) {
		
		if(resultados.hasErrors()) {
			ModelAndView cargaCuesPregunta= new ModelAndView();
			cargaCuesPregunta.addObject("otroCuesPreg", CuestionarioP);
			return cargaCuesPregunta;
		}
		
		ModelAndView listadoCuesPregunta=new ModelAndView("mostrarCuesPregunta");
		
		try {
			cuesPreguntaService.cargarCuesPregunta(CuestionarioP);
		}catch(Exception e) {
			listadoCuesPregunta.addObject("cargarCuesPreguntaErrorMessage", e.getMessage());
		}
		
		listadoCuesPregunta.addObject("listadoCuesPreguntas", cuesPreguntaService.mostrarTodosCuesPregunta());
		return listadoCuesPregunta;
	}
}
