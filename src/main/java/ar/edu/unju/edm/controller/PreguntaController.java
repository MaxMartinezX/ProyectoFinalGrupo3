package ar.edu.unju.edm.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Pregunta;
import ar.edu.unju.edm.service.IPreguntaService;

@Controller
public class PreguntaController {
	@Autowired
	Pregunta unaPregunta;
	
	@Autowired
	@Qualifier("servicioEnMySQL")
	IPreguntaService unServicio;
	
	private static final Log GRUPO3 = LogFactory.getLog(PreguntaController.class);
	
	@GetMapping("/pregunta")
	public ModelAndView cargarPregunta() {
		ModelAndView newPregunta= new ModelAndView("formularioPregunta");
		newPregunta.addObject("nuevaPregunta", unaPregunta);
		GRUPO3.warn("Cargando nueva pregunta");
		return newPregunta;
	}
	
	@PostMapping("/guardarPregunta")
	public ModelAndView guardarPregunta(@ModelAttribute("nuevaPregunta") Pregunta laPregunta) {
		ModelAndView listadoPreguntas = new ModelAndView("mostrarPregunta");
		try {
			GRUPO3.warn("Guardando nueva pregunta");
			unServicio.cargarPregunta(laPregunta);
		}catch(Exception e) {
			GRUPO3.error(e);	
		}
		GRUPO3.warn("Listado de Preguntas Realizadas");
		listadoPreguntas.addObject("preguntaListado", unServicio.listarPreguntas());
		return listadoPreguntas;
	}
}