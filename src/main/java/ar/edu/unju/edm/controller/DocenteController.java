package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import ar.edu.unju.edm.model.Docente;
import ar.edu.unju.edm.service.IDocenteService;

@Controller
public class DocenteController {
	
	private static final Log GRUPO3 = LogFactory.getLog(DocenteController.class);
	
	@Autowired
	Docente unDocente;
	
	@Autowired
	@Qualifier("servicioEnMySQL")
	IDocenteService unServicio;
	
	@GetMapping("/docente")
	public ModelAndView cargarDocente() {
		ModelAndView cargaDocente = new ModelAndView("formularioDocente");
		cargaDocente.addObject("nuevoDocente", unDocente);
		return cargaDocente;
	}
	
	@PostMapping("/guardarDocente")
	public ModelAndView guardarDocente(@ModelAttribute("nuevoDocente") Docente docenteNuevo){
		ModelAndView listadoDocentes = new ModelAndView("mostrarDocentes");
		
		GRUPO3.warn("Mostrando el nuevo producto " + docenteNuevo.getNombre());
		
		try {
			unServicio.cargarDocente(docenteNuevo);
		}catch(Exception e) {
		}
		
		listadoDocentes.addObject("docenteListado",unServicio.listarDocentes());
		
		return listadoDocentes;
	}

}
