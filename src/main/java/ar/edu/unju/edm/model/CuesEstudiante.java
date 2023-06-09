package ar.edu.unju.edm.model;


import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Component
@Entity
public class CuesEstudiante {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id_CuesEstudiante;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_Estudiante")
	Estudiante estudiante;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_Cuestionario")
	Cuestionario cuestionario;
	@NotNull(message="Puntaje Obtenido is required")
	private int puntajeObtenido;
	@NotNull(message="FechaRealizada is required")
	private String fechaRealizada;
	
	public CuesEstudiante() {
		
	}


	public Integer getId_CuesEstudiante() {
		return id_CuesEstudiante;
	}


	public void setId_CuesEstudiante(Integer id_CuesEstudiante) {
		this.id_CuesEstudiante = id_CuesEstudiante;
	}


	public Estudiante getEstudiante() {
		return estudiante;
	}


	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}


	public Cuestionario getCuestionario() {
		return cuestionario;
	}


	public void setCuestionario(Cuestionario cuestionario) {
		this.cuestionario = cuestionario;
	}


	public int getPuntajeObtenido() {
		return puntajeObtenido;
	}


	public void setPuntajeObtenido(int puntajeObtenido) {
		this.puntajeObtenido = puntajeObtenido;
	}


	public String getFechaRealizada() {
		return fechaRealizada;
	}


	public void setFechaRealizada(String fechaRealizada) {
		this.fechaRealizada = fechaRealizada;
	}
	
}
