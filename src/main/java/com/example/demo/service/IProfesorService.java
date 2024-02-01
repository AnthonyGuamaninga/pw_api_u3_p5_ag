package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.repository.modelo.Profesor;

public interface IProfesorService {
	
	public void ingresar(Profesor profesor);
	public void modificar(Profesor profesor);
	public Profesor buscar(Integer id);
	public void borrar(Integer id);
	
	public void modificarParcial(String nacionalidad, BigDecimal sueldo, Integer id);
	public List<Profesor> buscarPorApellido(String apellido);
}
