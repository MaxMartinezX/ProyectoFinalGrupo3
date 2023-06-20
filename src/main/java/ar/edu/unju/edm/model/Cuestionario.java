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
	
	@NotBlank(message= "titulo is required")
	private String titulo;
	
	private String descripcion;

	private Boolean estado;
	
	public Cuestionario() {
	}

	public Cuestionario(Integer id_Cuestionario, Docente docente,
			@NotBlank(message = "titulo is required") String titulo, String descripcion, Boolean estado) {
		super();
		this.id_Cuestionario = id_Cuestionario;
		this.docente = docente;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.estado = estado;
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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
}
