package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Docente;

@Controller
public class IndexController {
	
	@Autowired
	Docente unDocente;
	
	@GetMapping({"/","/index","/home","login"})
	public ModelAndView cargarPrincipal(){
		ModelAndView principal = new ModelAndView("index");
		principal.addObject("docenteParaLoguear", unDocente);
		
		return principal;
	}

}
