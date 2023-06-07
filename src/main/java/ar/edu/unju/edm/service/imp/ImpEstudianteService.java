package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Estudiante;
import ar.edu.unju.edm.repository.EstudianteRepository;
import ar.edu.unju.edm.service.IEstudianteService;

@Service
@Qualifier("servicioEstudianteEnMySQL")
public class ImpEstudianteService implements IEstudianteService{
	
	@Autowired
	EstudianteRepository estudianteRepository;

	@Override
	public void cargarEstudiante(Estudiante unEstudiante) {
		// TODO Auto-generated method stub
		unEstudiante.setEstado(true);
		estudianteRepository.save(unEstudiante);
	}

	@Override
	public void eliminarUnEstudiante(Integer idEstudiante) {
		// TODO Auto-generated method stub
		Optional<Estudiante> aux = Optional.of(new Estudiante());
		aux =estudianteRepository.findById(idEstudiante);
		aux.get().setEstado(false);
		estudianteRepository.save(aux.get());
	}

	@Override
	public Estudiante mostrarUnEstudiante(Integer idEstudiante) {
		// TODO Auto-generated method stub
		Optional<Estudiante> aux = Optional.of(new Estudiante());
		aux = estudianteRepository.findById(idEstudiante);
		
		return aux.get();
	}

	@Override
	public ArrayList<Estudiante> listarEstudiantes() {
		// TODO Auto-generated method stub
		return (ArrayList<Estudiante>) estudianteRepository.findByEstado(true);
	}

	@Override
	public void eliminarTodosLosEstudiantes() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Estudiante modificarUnEstudiante(Integer idEstudiante) {
		// TODO Auto-generated method stub
		return null;
	}

}
