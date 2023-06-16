package ar.edu.unju.edm.model;


import org.springframework.stereotype.Component;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Component
public class CuesPregunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_CuesPregunta;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_Pregunta")
	Pregunta pregunta;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_Cuestionario")
	Cuestionario cuestionario;

	public CuesPregunta() {
		
	}

	public Integer getId_CuesPregunta() {
		return id_CuesPregunta;
	}

	public void setId_CuesPregunta(Integer id_CuesPregunta) {
		this.id_CuesPregunta = id_CuesPregunta;
	}

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	public Cuestionario getCuestionario() {
		return cuestionario;
	}

	public void setCuestionario(Cuestionario cuestionario) {
		this.cuestionario = cuestionario;
	}
	
	
}
