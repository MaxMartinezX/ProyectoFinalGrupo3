package ar.edu.unju.edm.service;

import java.util.ArrayList;

import ar.edu.unju.edm.model.CuesPregunta;

public interface ICuesPreguntaService {
	public void cargarCuesPregunta(CuesPregunta cuesPregunta);
	public void borrarCuesPregunta(Integer idCuesPregunta);
	public ArrayList<CuesPregunta> mostrarTodosCuesPregunta();
	public CuesPregunta mostrarUnCuesPregunta(Integer idCuesPregunta);
	public CuesPregunta modificarUnCuesPregunta (Integer idCuesPregunta);
}
