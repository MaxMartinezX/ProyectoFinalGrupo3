package ar.edu.unju.edm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.Pregunta;
import ar.edu.unju.edm.model.CuesPregunta;
import ar.edu.unju.edm.model.Cuestionario;

@Repository
public interface CuesPreguntaRepository extends CrudRepository<CuesPregunta, Integer>{
	 public List<Pregunta> findAllByCuestionario (Cuestionario cuestionario);
	 public List<CuesPregunta> findAllCPByCuestionario (Cuestionario cuestionario);
}
