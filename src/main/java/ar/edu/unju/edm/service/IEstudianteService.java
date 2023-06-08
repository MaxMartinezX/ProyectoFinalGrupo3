package ar.edu.unju.edm.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Estudiante;

@Service
public interface IEstudianteService {
	public void cargarEstudiante(Estudiante unEstudiante);
	public void eliminarUnEstudiante(Integer idEstudiante);
	public Estudiante modificarUnEstudiante(Integer idEstudiante);
	public Estudiante mostrarUnEstudiante(Integer idEstudiante);
	public ArrayList<Estudiante> listarEstudiantes();
	public void eliminarTodosLosEstudiantes();
}
