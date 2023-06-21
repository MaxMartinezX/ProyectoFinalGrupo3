package ar.edu.unju.edm.controller;

import java.util.List;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.CuesEstudiante;
import ar.edu.unju.edm.service.ICuesEstudianteService;
import ar.edu.unju.edm.service.ICuestionarioService;
import ar.edu.unju.edm.service.IEstudianteService;
import jakarta.validation.Valid;

@Controller
public class CuesEstudianteController {
	
	private static final Log GRUPO3 = LogFactory.getLog(CuesEstudianteController.class);
	
	@Autowired
	ICuesEstudianteService cuesEstudianteService;
	
	@Autowired
	IEstudianteService estudianteService;

	@Autowired
	ICuestionarioService cuestionarioService;
	
	@Autowired
	CuesEstudiante unCuesEstudiante;
	
	/*
	@GetMapping("/elegirCuestionarioE")
	public ModelAndView cargarCuesEstudiante () {
		ModelAndView cargaCuesEstudiante = new ModelAndView("formularioCuesEstudiante");
		cargaCuesEstudiante.addObject("nuevoCuesEstud", unCuesEstudiante);
		cargaCuesEstudiante.addObject("estudiante", estudianteService.listarEstudiantes());
		cargaCuesEstudiante.addObject("cuestionario", cuestionarioService.listarCuestionarios());
		
		return cargaCuesEstudiante;
	}
	*/
	/*
	@PostMapping("/guardarCuestionarioEARealizar")
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
	*/
	
	//El Estudiante resuelve el cuestionario
	@GetMapping("/resolverCuestionarioE/{id_Cuestionario}")
	public ModelAndView resolverCuesEstudiante(@PathVariable(name="id_Cuestionario")  Integer idCuesElegido) {
		ModelAndView resolverCuestionario = new ModelAndView("resolverCuestionarios");
		try {
			resolverCuestionario.addObject("nuevoCuestionario", cuestionarioService.mostrarUnCuestionario(idCuesElegido));
		}catch(Exception e) {
			resolverCuestionario.addObject("CargandoCuestionarioErrorMessaje", e.getMessage());
		}
				
		return resolverCuestionario;
	}
	
	//Guardar las respuestas del cuestionario
	@PostMapping("/cuestionarioResuelto/{id_cuestionario}")
	public String guardarCuestionarioERealizado(@ModelAttribute("cuesEstudiante") CuesEstudiante cuestionarioE,
			@RequestParam("respuestasSeleccionadas") List<String> seleccionadas,
			@PathVariable ) 
		
		// descomponer el array y calcular puntaje
		
		//guardar el puntaje calculado en el campo puntajeObtenido
		
		// hacer lago para que el usuario no pueda volver a realizar de nuevo el cuestionario --> id_cuestionario
		
		return "redirect:";
	}
	
}
