package ar.edu.unju.edm.service;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.edm.model.CuesPregunta;
import ar.edu.unju.edm.model.Pregunta;

public interface ICuesPreguntaService {
	public void cargarCuesPregunta(CuesPregunta cuesPregunta);
	public void borrarCuesPregunta(Integer idCuesPregunta);
	public ArrayList<CuesPregunta> mostrarTodosCuesPregunta();
	public CuesPregunta mostrarUnCuesPregunta(Integer idCuesPregunta);
	public CuesPregunta modificarUnCuesPregunta (Integer idCuesPregunta);
	public void cargarPreguntasACuestionario (List<Integer> preguntasSeleccionadas,List<Integer> puntajesSeleccionados, Integer id_Cuestionario);
	public List<Pregunta> ListarPreguntasDeUnCuestionario(Integer id_Cuestionario);
	public List<Integer> ListarRespuestasDePreguntas(Integer id_Cuestionario);
	public List<Integer> ListadoDePuntajes (Integer id_Cuestionario);
	public List<Pregunta> ListarPreguntasNoSeleccionadas(List<Pregunta> seleccionadas, List<Pregunta> todasLasPreguntas);
}
