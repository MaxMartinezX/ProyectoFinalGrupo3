package ar.edu.unju.edm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Cuestionario;
import ar.edu.unju.edm.model.Pregunta;

@Service
public interface ICuestionarioService {
	public void cargarCuestionario(Cuestionario unCuestionario);
	public void eliminarCuestionario(Integer idCuestionario);
	public Cuestionario mostrarUnCuestionario(Integer idCuestionario);
	public ArrayList<Cuestionario> listarCuestionarios();
	public void eliminarTodosLosCuestionarios();
	public void agregarPreguntasAUnCuestionario(Integer idCuestionario, List<Pregunta> preguntas);


}
