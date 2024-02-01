package com.example.demo.repository;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.repository.modelo.Profesor;

public interface IProfesorRepository {

	public void insertar(Profesor profesor);
	public void actualizar(Profesor profesor);
	public Profesor buscar(Integer id);
	public void eliminar(Integer id);
	
	public void actualizarParcial(String nacionalidad, BigDecimal sueldo, Integer id);
	public List<Profesor> buscarPorApellido(String apellido);
	
	
	
}
