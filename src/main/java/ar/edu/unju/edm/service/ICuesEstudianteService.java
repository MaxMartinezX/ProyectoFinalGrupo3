package ar.edu.unju.edm.service;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.CuesEstudiante;


@Service
public interface ICuesEstudianteService {
	public void cargarCuesEstudiante(CuesEstudiante cuesEstudiante);
	public void eliminarCuesEstudiante(Integer idCuesEstudiante);
	public ArrayList<CuesEstudiante> listarTodosCuestionariosEstudiantes();
	public CuesEstudiante listarUnCuestionarioEstudiante(Integer idCuesEstudiante);
	public CuesEstudiante modificarCuestionarioEstudiante(Integer idCuesEstudiante);
	public void eliminarTodosCuesEstudiantes();
	public Integer calcularPuntajeObtenido(List<Integer> opcionesCorrectas, Map<String,String> opcionesElegidas,List<Integer> puntajes);
	public String fechaActual();
}
