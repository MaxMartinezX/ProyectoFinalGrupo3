package ar.edu.unju.edm.service.imp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.CuesEstudiante;
import ar.edu.unju.edm.repository.CuesEstudianteRepository;
import ar.edu.unju.edm.service.ICuesEstudianteService;

@Service
public class ImpCuesEstudianteService implements ICuesEstudianteService {

	@Autowired
	CuesEstudianteRepository cuesEstudianteRepository;
	
	
	@Override
	public void cargarCuesEstudiante(CuesEstudiante cuesEstudiante) {
		cuesEstudiante.setEstado(true);
		cuesEstudianteRepository.save(cuesEstudiante);
	}

	@Override
	public void eliminarCuesEstudiante(Integer idCuesEstudiante) {
		Optional<CuesEstudiante> aux = Optional.of(new CuesEstudiante());
		aux = cuesEstudianteRepository.findById(idCuesEstudiante);
		aux.get().setEstado(false);
		cuesEstudianteRepository.save(aux.get());
	}

	@Override
	public ArrayList<CuesEstudiante> listarTodosCuestionariosEstudiantes() {
		
		return (ArrayList<CuesEstudiante>) cuesEstudianteRepository.findByEstado(true);
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
	public Integer calcularPuntajeObtenido(List<Integer> opcionesCorrectas, List<String> opcionesElegidas, List<Integer> puntajes) {
		Integer puntajeObtenido=0;
		/*for(String opciones : opcionesElegidas) {
			
			if(opcionesCorrectas.get(i)==opcionesElegidas.get(i)) {
				puntajeObtenido+=puntajes.get(i);
			}
		}
		*/
		return puntajeObtenido;
	}

	@Override
	public String fechaActual() {
		LocalDate fechaActual = LocalDate.now();
		String fechaString = fechaActual.toString();

		return fechaString;
	}

}
