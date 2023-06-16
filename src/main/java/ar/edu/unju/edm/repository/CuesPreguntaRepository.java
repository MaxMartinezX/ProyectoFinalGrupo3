package ar.edu.unju.edm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.CuesPregunta;

@Repository
public interface CuesPreguntaRepository extends CrudRepository<CuesPregunta, Integer>{
	
}
