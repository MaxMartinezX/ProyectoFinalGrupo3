package ar.edu.unju.edm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.Estudiante;

@Repository

public interface EstudianteRepository extends CrudRepository <Estudiante,Integer>{
	public List<Estudiante> findByEstado (Boolean estado);
}
