package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IProfesorRepository;
import com.example.demo.repository.modelo.Profesor;
@Service
public class ServiceProfesorImpl implements IProfesorService{

	@Autowired
	private IProfesorRepository iProfesorRepository;
	
	@Override
	public void ingresar(Profesor profesor) {
		// TODO Auto-generated method stub
		this.iProfesorRepository.insertar(profesor);
	}

	@Override
	public void modificar(Profesor profesor) {
		// TODO Auto-generated method stub
		this.iProfesorRepository.actualizar(profesor);
	}

	@Override
	public Profesor buscar(Integer id) {
		// TODO Auto-generated method stub
		return this.iProfesorRepository.buscar(id);
	}

	@Override
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		this.iProfesorRepository.eliminar(id);
	}

	@Override
	public void modificarParcial(String nacionalidad, BigDecimal sueldo, Integer id) {
		// TODO Auto-generated method stub
		this.iProfesorRepository.actualizarParcial(nacionalidad, sueldo, id);
	}

	@Override
	public List<Profesor> buscarPorApellido(String apellido) {
		// TODO Auto-generated method stub
		return this.iProfesorRepository.buscarPorApellido(apellido);
	}

}
