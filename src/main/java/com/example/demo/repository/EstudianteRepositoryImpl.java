package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Estudiante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class EstudianteRepositoryImpl implements IEstudianteRepository{

	
	private EntityManager entityManager;
	@Override
	public void insertar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.entityManager.persist(estudiante);
	}

	@Override
	public void actualizar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.entityManager.merge(estudiante);
	}

	@Override
	public void actualizarParcial(String nombre, String apellido, Integer id) {
		// TODO Auto-generated method stub
		// SQL: UPDATE estudiante e SET e.estu_nombre=: valor, e.estu_apellido=: valor2 WHERE e.estu_id := valor3
		Query query = this.entityManager.createQuery("UPDATE estudiante e SET e.nombre=:valor1, e.apellido=:valor2 WHERE e.id=:valor3");
		
		query.setParameter("valor1", nombre);
		query.setParameter("valor2", apellido);
		query.setParameter("valor3", id);
		
		query.executeUpdate();
	}

	@Override
	public Estudiante seleccionar(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Estudiante.class, id);
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		this.entityManager.remove(this.seleccionar(id));
	}
	
	//CRUD -> Create, Read, Update, Delete 
	
	
	
}
