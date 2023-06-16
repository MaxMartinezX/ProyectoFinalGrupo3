package ar.edu.unju.edm.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;


@Component
@Entity
public class Cuestionario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_Cuestionario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_Docente")
	private Docente docente;
	
	//Pendiente por modificar preguntas
	@NotBlank(message= "Pregunta1 is required ")
	private String pregunta1;
	@NotBlank(message= "Pregunta2 is required")
	private String pregunta2;
	@NotBlank(message= "Pregunta3 is required")
	private String pregunta3;
	private String pregunta4;
	private String pregunta5;
	private Boolean estado;
	
	public Cuestionario() {
	}

	public Cuestionario(Integer id_Cuestionario, Docente docente, String pregunta1,
			String pregunta2, String pregunta3, String pregunta4, String pregunta5) {
		super();
		this.id_Cuestionario = id_Cuestionario;
		this.docente = docente;
		this.pregunta1 = pregunta1;
		this.pregunta2 = pregunta2;
		this.pregunta3 = pregunta3;
		this.pregunta4 = pregunta4;
		this.pregunta5 = pregunta5;
	}

	public Integer getId_Cuestionario() {
		return id_Cuestionario;
	}

	public void setId_Cuestionario(Integer id_Cuestionario) {
		this.id_Cuestionario = id_Cuestionario;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public String getPregunta1() {
		return pregunta1;
	}

	public void setPregunta1(String pregunta1) {
		this.pregunta1 = pregunta1;
	}

	public String getPregunta2() {
		return pregunta2;
	}

	public void setPregunta2(String pregunta2) {
		this.pregunta2 = pregunta2;
	}

	public String getPregunta3() {
		return pregunta3;
	}

	public void setPregunta3(String pregunta3) {
		this.pregunta3 = pregunta3;
	}

	public String getPregunta4() {
		return pregunta4;
	}

	public void setPregunta4(String pregunta4) {
		this.pregunta4 = pregunta4;
	}

	public String getPregunta5() {
		return pregunta5;
	}

	public void setPregunta5(String pregunta5) {
		this.pregunta5 = pregunta5;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
}
