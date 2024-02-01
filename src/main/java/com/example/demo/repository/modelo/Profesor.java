package com.example.demo.repository.modelo;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "profesor")
public class Profesor {

	@Id
	@GeneratedValue(generator = "seq_prof", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_prof", sequenceName = "seq_prof", allocationSize = 1)
	@Column(name = "prof_id")
	private Integer id;
	
	@Column(name = "prof_nombre")
	private String nombre;
	
	@Column(name = "prof_apellido")
	private String apellido;
	
	@Column(name = "prof_fecha_nacimiento")
	private String fechaNacimiento;
	
	@Column(name = "prof_direccion")
	private String direccion;
	
	@Column(name = "prof_genero")
	private String genero;
	
	@Column(name = "prof_nacionalidad")
	private String nacionalidad;
	
	@Column(name = "prof_sueldo")
	private BigDecimal sueldo;

	
	// GET y SET
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public BigDecimal getSueldo() {
		return sueldo;
	}

	public void setSueldo(BigDecimal sueldo) {
		this.sueldo = sueldo;
	}
	
	
	
}
