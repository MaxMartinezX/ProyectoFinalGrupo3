package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.CuesPregunta;
import ar.edu.unju.edm.repository.CuesPreguntaRepository;
import ar.edu.unju.edm.service.ICuesPreguntaService;

@Service
public class ImpCuesPreguntaService implements ICuesPreguntaService{

	
	@Autowired
	CuesPreguntaRepository cuesPreguntaRepository;
	
	@Override
	public void cargarCuesPregunta(CuesPregunta cuesPregunta) {
		cuesPreguntaRepository.save(cuesPregunta);
		
	}

	@Override
	public void borrarCuesPregunta(Integer idCuesPregunta) {
		cuesPreguntaRepository.deleteById(idCuesPregunta);
	}

	@Override
	public ArrayList<CuesPregunta> mostrarTodosCuesPregunta() {
		// TODO Auto-generated method stub
		return (ArrayList<CuesPregunta>) cuesPreguntaRepository.findAll();
	}

	@Override
	public CuesPregunta mostrarUnCuesPregunta(Integer idCuesPregunta) {
		Optional<CuesPregunta> aux= Optional.of(new CuesPregunta());
		aux = cuesPreguntaRepository.findById(idCuesPregunta);
		return aux.get();
	}

	@Override
	public CuesPregunta modificarUnCuesPregunta(Integer idCuesPregunta) {
		// TODO Auto-generated method stub
		return null;
	}

}
