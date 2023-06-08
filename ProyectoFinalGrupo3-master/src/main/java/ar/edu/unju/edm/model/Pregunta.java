package ar.edu.unju.edm.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.*;

@Component
@Entity
public class Pregunta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPregunta;
	private String pregunta;
	private String opcion1;
	private String opcion2;
	private String opcion3;
	private String opcion4;
	private Integer opcionCorrecta;
	private Boolean estado;
	
	public Pregunta() {
		
	}

	public Integer getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(Integer idPregunta) {
		this.idPregunta = idPregunta;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getOpcion1() {
		return opcion1;
	}

	public void setOpcion1(String opcion1) {
		this.opcion1 = opcion1;
	}

	public String getOpcion2() {
		return opcion2;
	}

	public void setOpcion2(String opcion2) {
		this.opcion2 = opcion2;
	}

	public String getOpcion3() {
		return opcion3;
	}

	public void setOpcion3(String opcion3) {
		this.opcion3 = opcion3;
	}

	public String getOpcion4() {
		return opcion4;
	}

	public void setOpcion4(String opcion4) {
		this.opcion4 = opcion4;
	}

	public Integer getOpcionCorrecta() {
		return opcionCorrecta;
	}

	public void setOpcionCorrecta(Integer opcionCorrecta) {
		this.opcionCorrecta = opcionCorrecta;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

}
