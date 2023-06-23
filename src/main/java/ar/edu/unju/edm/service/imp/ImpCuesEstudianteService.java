package ar.edu.unju.edm.service.imp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.CuesEstudiante;
import ar.edu.unju.edm.repository.CuesEstudianteRepository;
import ar.edu.unju.edm.service.ICuesEstudianteService;
import ar.edu.unju.edm.service.ICuesPreguntaService;

@Service
public class ImpCuesEstudianteService implements ICuesEstudianteService {

	@Autowired
	CuesEstudianteRepository cuesEstudianteRepository;
	@Autowired
	ICuesPreguntaService cuesPreguntaService;
	
	
	@Override
	public void cargarCuesEstudiante(CuesEstudiante cuesEstudiante) {
		cuesEstudianteRepository.save(cuesEstudiante);
	}

	@Override
	public void eliminarCuesEstudiante(Integer idCuesEstudiante) {
		CuesEstudiante aux = cuesEstudianteRepository.findById(idCuesEstudiante).get();
		cuesEstudianteRepository.delete(aux);
	}

	@Override
	public ArrayList<CuesEstudiante> listarTodosCuestionariosEstudiantes() {
		
		return (ArrayList<CuesEstudiante>) cuesEstudianteRepository.findAll();
	}

	@Override
	public CuesEstudiante listarUnCuestionarioEstudiante(Integer idCuesEstudiante) {
		Optional<CuesEstudiante> aux = Optional.of(new CuesEstudiante());
		aux = cuesEstudianteRepository.findById(idCuesEstudiante);
		return aux.get();
	}

	@Override
	public CuesEstudiante modificarCuestionarioEstudiante(Integer idCuesEstudiante) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarTodosCuesEstudiantes() {
		// TODO Auto-generated method stub
		
	}

	@Override

	public String fechaActual() {
		LocalDate fechaActual = LocalDate.now();
		String fechaString = fechaActual.toString();

		return fechaString;
	}

	@Override
	public Integer calcularPuntajeObtenido(Integer idCuestionario, Map<String,String> opcionesElegidas) {
		List<Integer> opcionesCorrectas = cuesPreguntaService.ListarRespuestasDePreguntas(idCuestionario);
		List<Integer> puntajes= cuesPreguntaService.ListadoDePuntajes(idCuestionario);
		Integer puntajeObtenido=0;
		int i=0;
		for(Map.Entry<String, String> opcion: opcionesElegidas.entrySet()) {
			Integer aux=Integer.parseInt(opcion.getValue());
			if(aux==opcionesCorrectas.get(i)) {
				puntajeObtenido+=puntajes.get(i);
			}
			i++;
		}
		return puntajeObtenido;
	}

}
