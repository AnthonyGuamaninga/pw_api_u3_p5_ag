package com.example.demo.service;

import java.util.List;

import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.dto.EstudianteLigeroTO;
import com.example.demo.service.to.EstudianteTO;


public interface IEstudianteService {

	public void guardar(EstudianteTO estudiante);
	public void actualizar(EstudianteTO estudiante);
	public void actualizarParcial(String nombre, String apellido, Integer id);
	
	public EstudianteTO buscarTO(Integer id);
	public EstudianteLigeroTO buscarLigeroTO(Integer id);
	public void borrar(Integer id);
	
	public List<EstudianteLigeroTO> buscarTodosLigeroTO();
	
}
