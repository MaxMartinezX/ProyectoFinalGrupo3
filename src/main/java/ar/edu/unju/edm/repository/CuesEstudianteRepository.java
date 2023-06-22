package ar.edu.unju.edm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.CuesEstudiante;

@Repository
public interface CuesEstudianteRepository extends CrudRepository<CuesEstudiante, Integer> {
}
