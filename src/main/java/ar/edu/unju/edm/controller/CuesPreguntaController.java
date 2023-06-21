package ar.edu.unju.edm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.CuesPregunta;
import ar.edu.unju.edm.model.Pregunta;
import ar.edu.unju.edm.service.ICuesPreguntaService;
import ar.edu.unju.edm.service.ICuestionarioService;
import ar.edu.unju.edm.service.IPreguntaService;

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
	
	@GetMapping("/cuestionarioPregunta/{id_Cuestionario}")
	public ModelAndView cargarCuesPregunta(@PathVariable(name="id_Cuestionario") Integer id) {
		
		ModelAndView unCuesP= new ModelAndView("formularioCuesPregunta");
		
		unCuesP.addObject("listadoPreguntas", preguntaService.listarPreguntas());
		
		return unCuesP;
	}
	
	@PostMapping("/guardarCuestionarioPregunta/{id_Cuestionario}")
	public String guardarCuesPregunta(@ModelAttribute("cuesPregunta") CuesPregunta CuestionarioP, @RequestParam("preguntasSeleccionada") List<Pregunta> preguntasSeleccionadas, @RequestParam("puntajesSeleccionados") List<Integer> puntajesSeleccionados, @PathVariable(name="id_Cuestionario") Integer id) {
		
		
	  cuesPreguntaService.cargarPreguntasACuestionario(preguntasSeleccionadas, puntajesSeleccionados, id);
		
		return "redirect:/cuestionarioConPreguntas/{id_Cuestionario}";
	}
}
