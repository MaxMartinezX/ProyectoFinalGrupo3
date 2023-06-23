package ar.edu.unju.edm.controller;

import java.util.List;
import java.util.Map;

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

import ar.edu.unju.edm.model.CuesEstudiante;
import ar.edu.unju.edm.model.Cuestionario;
import ar.edu.unju.edm.repository.CuestionarioRepository;
import ar.edu.unju.edm.service.ICuesEstudianteService;
import ar.edu.unju.edm.service.ICuesPreguntaService;
import ar.edu.unju.edm.service.ICuestionarioService;
import ar.edu.unju.edm.service.IEstudianteService;


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
	ICuesPreguntaService cuesPreguntasService;
	
	@Autowired
	CuesEstudiante unCuesEstudiante;
	
	@Autowired
	CuestionarioRepository cuestionarioRepository;
	
	
	
	@GetMapping("/elegirCuestionario")
	public ModelAndView cargarCuesEstudiante () {
		ModelAndView cargaCuesEstudiante = new ModelAndView("mostrarCuestionariosAEstudiantes");
		cargaCuesEstudiante.addObject("cuestionarios", cuestionarioService.listarCuestionarios());
		GRUPO3.warn("Elegir Cuestionario");
		return cargaCuesEstudiante;
	}
	
	
	//El Estudiante resuelve el cuestionario
	
	@GetMapping("/resolverCuestionario/{id_Cuestionario}")
	public ModelAndView resolverCuesEstudiante(@PathVariable(name="id_Cuestionario")  Integer idCuesElegido) {
		ModelAndView resolverCuestionario = new ModelAndView("resolverCuestionario");
			GRUPO3.warn("Estudiante resuelve cuestionario");
			resolverCuestionario.addObject("nuevoCuesEstudiante", unCuesEstudiante);
			resolverCuestionario.addObject("listadoEstudiantes", estudianteService.listarEstudiantes());
			
			resolverCuestionario.addObject("cuestionario", cuestionarioService.mostrarUnCuestionario(idCuesElegido));
			resolverCuestionario.addObject("preguntas", cuesPreguntasService.ListarPreguntasDeUnCuestionario(idCuesElegido));
			
			GRUPO3.warn("Nueva resolucion de Cuestionario");
		return resolverCuestionario;
	}
	
	
	
	//Guardar las respuestas del cuestionario
	@PostMapping("/resultadoDeCuestionario/{id_Cuestionario}")
	public ModelAndView guardarCuestionarioERealizado(@ModelAttribute("nuevoCuesEstudiante") CuesEstudiante nuevoCuesEstudiante,
			@RequestParam Map<String,String> respuestasSeleccionadas, @PathVariable(name="id_Cuestionario") Integer idCuestionario ) { 
		
		ModelAndView resultadoCuestionario = new ModelAndView("resultadoCuestionario");
		GRUPO3.warn("Cuestionario realizado");
		try {
		
		nuevoCuesEstudiante.setFechaRealizada(cuesEstudianteService.fechaActual());
		nuevoCuesEstudiante.setPuntajeObtenido(cuesEstudianteService.calcularPuntajeObtenido(idCuestionario, respuestasSeleccionadas));
        nuevoCuesEstudiante.setCuestionario(cuestionarioRepository.findById(idCuestionario).get());
        cuesEstudianteService.cargarCuesEstudiante(nuevoCuesEstudiante);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			GRUPO3.error(e);
		}
		resultadoCuestionario.addObject("nuevoCuesEstudiante", nuevoCuesEstudiante);
		
		return resultadoCuestionario;
	}
	
	
	//mostrando todos los cuesEstudiantes
	@GetMapping("/cuestionariosRealizados")
	public ModelAndView guardarCuesEstudiante () {
		GRUPO3.warn("Listando todos los Cuestionarios");
		ModelAndView listadoCuesEstudiante = new ModelAndView("mostrarCuestionariosResueltos");
		
		listadoCuesEstudiante.addObject("cuesEstudianteListado", cuesEstudianteService.listarTodosCuestionariosEstudiantes() );
		
		return listadoCuesEstudiante;
	}
	
}
