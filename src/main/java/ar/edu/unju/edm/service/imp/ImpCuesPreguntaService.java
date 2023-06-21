package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.CuesPregunta;
import ar.edu.unju.edm.model.Cuestionario;
import ar.edu.unju.edm.model.Pregunta;
import ar.edu.unju.edm.repository.CuesPreguntaRepository;
import ar.edu.unju.edm.repository.CuestionarioRepository;
import ar.edu.unju.edm.service.ICuesPreguntaService;

@Service
public class ImpCuesPreguntaService implements ICuesPreguntaService{

	
	@Autowired
	CuesPreguntaRepository cuesPreguntaRepository;
	@Autowired
	CuestionarioRepository cuestionarioRepository;
	
	@Override
	public void cargarCuesPregunta(CuesPregunta cuesPregunta) {
		cuesPreguntaRepository.save(cuesPregunta);
		
	}
	
	@Override
	public void cargarPreguntasACuestionario (List<Pregunta> preguntasSeleccionadas,List<Integer> puntajesSeleccionados, Integer id_Cuestionario){
		for(int i=0;i<preguntasSeleccionadas.size();i++) {
			CuesPregunta auxiliar= new CuesPregunta();
			auxiliar.setPregunta(preguntasSeleccionadas.get(i));
			auxiliar.setPuntaje(puntajesSeleccionados.get(i));
			auxiliar.setCuestionario(cuestionarioRepository.findById(id_Cuestionario).get());
			
			cuesPreguntaRepository.save(auxiliar);
		}
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
	
	@Override
	public List<Pregunta> ListarPreguntasDeUnCuestionario(Integer id_Cuestionario){
		Optional<Cuestionario> aux= Optional.of(new Cuestionario());
		aux = cuestionarioRepository.findById(id_Cuestionario);
		return cuesPreguntaRepository.findAllByCuestionario(aux.get());
	}

}
