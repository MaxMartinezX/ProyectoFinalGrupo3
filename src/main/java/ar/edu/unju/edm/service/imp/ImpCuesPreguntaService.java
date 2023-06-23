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
import ar.edu.unju.edm.repository.PreguntaRepository;
import ar.edu.unju.edm.service.ICuesPreguntaService;

@Service
public class ImpCuesPreguntaService implements ICuesPreguntaService{

	
	@Autowired
	CuesPreguntaRepository cuesPreguntaRepository;
	@Autowired
	CuestionarioRepository cuestionarioRepository;
	@Autowired
	PreguntaRepository preguntaRepository;
	
	@Override
	public void cargarCuesPregunta(CuesPregunta cuesPregunta) {
		cuesPreguntaRepository.save(cuesPregunta);
		
	}
	
	@Override
	public void cargarPreguntasACuestionario (List<Integer> preguntasSeleccionadas,List<Integer> puntajesSeleccionados, Integer id_Cuestionario){
		
		//cuesPreguntaRepository.deleteAll(cuesPreguntaRepository.findAllByCuestionario(cuestionarioRepository.findById(id_Cuestionario).get()));
			
		for(int i=0;i<preguntasSeleccionadas.size();i++) {
			CuesPregunta auxiliar= new CuesPregunta();
			auxiliar.setPregunta(preguntaRepository.findById(preguntasSeleccionadas.get(i)).get());
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
	public List<Pregunta> ListarPreguntasDeUnCuestionario(Integer id_Cuestionario) {
		Cuestionario aux = cuestionarioRepository.findById(id_Cuestionario).get();
        List<CuesPregunta> cuesPreguntas = cuesPreguntaRepository.findAllByCuestionario(aux);
        List<Pregunta> preguntas = new ArrayList<>();
        for(int i=0;i<cuesPreguntas.size();i++) {
			preguntas.add(cuesPreguntas.get(i).getPregunta());
		}
        return preguntas;
    }
	
	@Override
	public List<Integer> ListarRespuestasDePreguntas(Integer id_Cuestionario){
		List<Pregunta> preguntas = ListarPreguntasDeUnCuestionario(id_Cuestionario);
		List<Integer> respuestas = new ArrayList<>();
		for(int i=0;i<preguntas.size();i++) {
			respuestas.add(preguntas.get(i).getOpcionCorrecta());
		}
		return respuestas;
	}
	

	@Override
	public List<Integer> ListadoDePuntajes(Integer id_Cuestionario) {
		Cuestionario aux = cuestionarioRepository.findById(id_Cuestionario).get();
        List<CuesPregunta> cuesPreguntas = cuesPreguntaRepository.findAllByCuestionario(aux);
		List<Integer> puntajes = new ArrayList<Integer>();
		for(int i=0;i<cuesPreguntas.size();i++) {
			puntajes.add(cuesPreguntas.get(i).getPuntaje());
		}
		return puntajes;
	}

	@Override
	public List<Pregunta> ListarPreguntasNoSeleccionadas(List<Pregunta> seleccionadas,List<Pregunta> todasLasPreguntas) {
		List<Pregunta> noSeleccionadas = new ArrayList<Pregunta>(todasLasPreguntas);
		
		noSeleccionadas.removeAll(seleccionadas);
		
		return noSeleccionadas;
		
	}

	@Override
	public void obtenerPuntajeTotalDeUnCuestionario(Integer id_cuestionario) {
		Cuestionario aux= cuestionarioRepository.findById(id_cuestionario).get();
		Integer puntajeTotal=0;
		List<Integer> puntajes=ListadoDePuntajes(id_cuestionario);
		for(int i=0; i <puntajes.size();i++) {
			puntajeTotal+=puntajes.get(i);
		}
		
		aux.setPuntajeTotal(puntajeTotal);
		cuestionarioRepository.save(aux);
	}

	
}
