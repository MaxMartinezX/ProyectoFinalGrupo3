package ar.edu.unju.edm.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Component
@Entity
public class Estudiante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id_Estudiante;
	private String apellido;
	private String nombre;
	private Integer dni;
	private String telefono;
	private String correo;
	private short curso;
	private char division;
	private String direccion;
	private Boolean estado;
	
	
	
	public Estudiante() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Estudiante(Integer id_Estudiante, String apellido, String nombre, Integer dni, String telefono,
			String correo, short curso, char division, String direccion, Boolean estado) {
		super();
		this.id_Estudiante = id_Estudiante;
		this.apellido = apellido;
		this.nombre = nombre;
		this.dni = dni;
		this.telefono = telefono;
		this.correo = correo;
		this.curso = curso;
		this.division = division;
		this.direccion = direccion;
		this.estado = estado;
	}


	public Integer getId_Estudiante() {
		return id_Estudiante;
	}


	public void setId_Estudiante(Integer id_Estudiante) {
		this.id_Estudiante = id_Estudiante;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Integer getDni() {
		return dni;
	}


	public void setDni(Integer dni) {
		this.dni = dni;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public short getCurso() {
		return curso;
	}


	public void setCurso(short curso) {
		this.curso = curso;
	}


	public char getDivision() {
		return division;
	}


	public void setDivision(char division) {
		this.division = division;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public Boolean getEstado() {
		return estado;
	}


	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	
	
	
}