package ar.edu.unju.edm.controller;

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
	
	@GetMapping("/cargarPregunta")
	public ModelAndView cargarPregunta() {
		ModelAndView newPregunta= new ModelAndView("fomularioPregunta");
		newPregunta.addObject("nuevaPregunta", unaPregunta);
		return newPregunta;
	}
	
	@PostMapping("/guardarPregunta")
	public ModelAndView guardarPregunta(@ModelAttribute("nuevaPregunta") Pregunta laPregunta) {
		ModelAndView listadoPreguntas = new ModelAndView("mostrarPregunta");
		try {
			unServicio.cargarPregunta(laPregunta);
		}catch(Exception e) {
			
		}
		listadoPreguntas.addObject("listaDePreguntas", unServicio.listarPreguntas());
		return listadoPreguntas;
	}
}