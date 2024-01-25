package com.example.demo.repository;

import com.example.demo.repository.modelo.Estudiante;


public interface IEstudianteRepository {
	
	//CRUD -> Create, Read, Update, Delete 
	
	public void insertar(Estudiante estudiante);
	public void actualizar(Estudiante estudiante);
	public void actualizarParcial(String nombre, String apellido, Integer id);
	public Estudiante seleccionar(Integer id);
	public void eliminar(Integer id);
	
}
