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
import ar.edu.unju.edm.repository.CuesEstudianteRepository;
import ar.edu.unju.edm.service.ICuesEstudianteService;
import ar.edu.unju.edm.service.ICuesPreguntaService;
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
	ICuesPreguntaService cuesPreguntasService;
	
	@Autowired
	CuesEstudiante unCuesEstudiante;
	
	
	
	@GetMapping("/elegirCuestionario")
	public ModelAndView cargarCuesEstudiante () {
		ModelAndView cargaCuesEstudiante = new ModelAndView("mostrarCuestionariosAEstudiantes");
		cargaCuesEstudiante.addObject("cuestionarios", cuestionarioService.listarCuestionarios());
		
		return cargaCuesEstudiante;
	}
	
	
	//El Estudiante resuelve el cuestionario
	
	@GetMapping("/resolverCuestionario/{id_Cuestionario}")
	public ModelAndView resolverCuesEstudiante(@PathVariable(name="id_Cuestionario")  Integer idCuesElegido) {
		ModelAndView resolverCuestionario = new ModelAndView("resolverCuestionario");
			
			resolverCuestionario.addObject("nuevoCuesEstud", unCuesEstudiante);
			resolverCuestionario.addObject("listadoEstudiantes", estudianteService.listarEstudiantes());
			
			resolverCuestionario.addObject("cuestionario", cuestionarioService.mostrarUnCuestionario(idCuesElegido));
			resolverCuestionario.addObject("preguntas", cuesPreguntasService.ListarPreguntasDeUnCuestionario(idCuesElegido));
			
				
		return resolverCuestionario;
	}
	
	
	
	//Guardar las respuestas del cuestionario
	@PostMapping("/resultadoDeCuestionario/{id_Cuestionario}")
	public ModelAndView guardarCuestionarioERealizado(@ModelAttribute("cuesEstudiante") CuesEstudiante cuesEstudianteConDatos,
			@RequestParam("respuestasSeleccionadas") List<String> seleccionadas, @PathVariable(name="id_Cuestionario") Integer idCuestionario ) { 
		
		ModelAndView resultadoCuestionario = new ModelAndView("resultadoCuestionario");
		
		try {
			
			cuesEstudianteConDatos.setFechaRealizada(cuesEstudianteService.fechaActual());
			cuesEstudianteConDatos.setPuntajeObtenido(cuesEstudianteService.calcularPuntajeObtenido(cuesPreguntasService.ListarRespuestasDePreguntas(idCuestionario), seleccionadas, cuesPreguntasService.ListadoDePuntajes(idCuestionario)));
		
			cuesEstudianteService.cargarCuesEstudiante(cuesEstudianteConDatos);
			
		}catch(Exception e) {
			resultadoCuestionario.addObject("GuardarCuesEstudianteErrorMessage", e.getMessage());
		}
			resultadoCuestionario.addObject("cuesEstudiante", cuesEstudianteConDatos);
			
		return resultadoCuestionario;
	}
	
	
	//mostrando todos los cuesEstudiantes
	@GetMapping("/cuestionariosRealizados")
	public ModelAndView guardarCuesEstudiante () {
		
		ModelAndView listadoCuesEstudiante = new ModelAndView("mostrarCuesEstudiantes");
		
		listadoCuesEstudiante.addObject("cuesEstudianteListado", cuesEstudianteService.listarTodosCuestionariosEstudiantes() );
		
		return listadoCuesEstudiante;
	}
	
}
