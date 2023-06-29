package ar.edu.unju.edm.controller;

import java.util.List;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.CuesPregunta;
import ar.edu.unju.edm.repository.CuestionarioRepository;
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
	@Autowired
	CuestionarioRepository cuestionarioRepository;
	
	private static final Log GRUPO3 = LogFactory.getLog(CuesEstudianteController.class);
	
	@GetMapping("/cuestionarioPregunta/{id_Cuestionario}")
	public ModelAndView cargarCuesPregunta(@PathVariable(name="id_Cuestionario") Integer id) {
		
		ModelAndView unCuesP= new ModelAndView("formularioCuesPregunta");
		
		unCuesP.addObject("listadoSeleccionadas", cuesPreguntaService.ListarPreguntasDeUnCuestionario(id));
		unCuesP.addObject("listadoDeNoSeleccionadas", cuesPreguntaService.ListarPreguntasNoSeleccionadas( cuesPreguntaService.ListarPreguntasDeUnCuestionario(id), preguntaService.listarPreguntas()));
		unCuesP.addObject("listadoPreguntas", preguntaService.listarPreguntas());
		
		GRUPO3.warn("Cargando preguntas al cuestionario");
		return unCuesP;
	}
	
	@PostMapping("/guardarCuestionarioPregunta/{id_Cuestionario}")
	public String guardarCuesPregunta(@ModelAttribute("cuesPregunta") CuesPregunta CuestionarioP, @RequestParam("preguntasSeleccionada") List<Integer> preguntasSeleccionadas, @RequestParam("puntajesSeleccionados") List<Integer> puntajesSeleccionados, @PathVariable(name="id_Cuestionario") Integer id) {
		
		GRUPO3.warn("PUNTAJESSSSSSSSSSSSSSSSSS");
		GRUPO3.warn(puntajesSeleccionados);
		GRUPO3.warn(preguntasSeleccionadas);
		
	  cuesPreguntaService.cargarPreguntasACuestionario(preguntasSeleccionadas, cuesPreguntaService.depurarPuntajesNoSeleccionados(puntajesSeleccionados), id);
		
		return "redirect:/listadoCuestionarios";
	}
	
	
}
