package ar.edu.unju.edm.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Cuestionario;

@Service
public interface ICuestionarioService {
	public void cargarCuestionario(Cuestionario unCuestionario);
	public void eliminarCuestionario(Integer idCuestionario);
	public Cuestionario mostrarUnCuestionario(Integer idCuestionario);
	public ArrayList<Cuestionario> listarCuestionarios();
	public void eliminarTodosLosCuestionarios();
	public Cuestionario modificarUnCuestionario(Integer idCuestionario);


}
