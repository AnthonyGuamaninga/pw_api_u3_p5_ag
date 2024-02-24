package com.example.demo.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Profesor;

@Repository
@Transactional
public class ProfesorRepositoryImpl implements IProfesorRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void insertar(Profesor profesor) {
		// TODO Auto-generated method stub
		this.entityManager.persist(profesor);
	}

	@Override
	public void actualizar(Profesor profesor) {
		// TODO Auto-generated method stub
		this.entityManager.merge(profesor);
	}

	@Override
	public Profesor buscar(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Profesor.class, id);
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		this.entityManager.remove(this.buscar(id));
	}

	@Override
	public void actualizarParcial(String nacionalidad, BigDecimal sueldo, Integer id) {
		// TODO Auto-generated method stub
		Query query = this.entityManager.createQuery("UPDATE Profesor p SET p.nacionalidad= :val1, p.sueldo= :val2 WHERE p.id= :val3  ");
		query.setParameter("val1", nacionalidad);
		query.setParameter("val2", sueldo);
		query.setParameter("val3", id);
		query.executeUpdate();
	}

	@Override
	public List<Profesor> buscarPorApellido(String apellido) {
		// TODO Auto-generated method stub
		TypedQuery<Profesor> query = this.entityManager.createQuery("SELECT p FROM Profesor p WHERE p.apellido= :val1", Profesor.class);
		query.setParameter("val1", apellido);
		return query.getResultList();
	}

	
}
