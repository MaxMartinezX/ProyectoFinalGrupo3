package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Cuestionario;
import ar.edu.unju.edm.repository.CuestionarioRepository;
import ar.edu.unju.edm.service.ICuestionarioService;

@Service
public class ImpCuestionarioService implements ICuestionarioService {
	
	@Autowired
	CuestionarioRepository cuestionarioRepository;

	@Override
	public void cargarCuestionario(Cuestionario unCuestionario) {
		unCuestionario.setEstado(true);
		cuestionarioRepository.save(unCuestionario);
		
	}

	@Override
	public void eliminarCuestionario(Integer idCuestionario) {
		Optional<Cuestionario> auxiliar = Optional.of(new Cuestionario());
		auxiliar=cuestionarioRepository.findById(idCuestionario);
		auxiliar.get().setEstado(false);
		cuestionarioRepository.save(auxiliar.get());
	}

	@Override
	public Cuestionario mostrarUnCuestionario(Integer idCuestionario) {
		Optional<Cuestionario> auxiliar = Optional.of(new Cuestionario());
		auxiliar= cuestionarioRepository.findById(idCuestionario);
		return auxiliar.get();
	}

	@Override
	public ArrayList<Cuestionario> listarCuestionarios() {
		return (ArrayList<Cuestionario>) cuestionarioRepository.findByEstado(true);
	}

	@Override
	public void eliminarTodosLosCuestionarios() {
		// TODO Auto-generated method stub
		
	}

}
