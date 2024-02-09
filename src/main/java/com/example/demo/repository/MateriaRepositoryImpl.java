package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Materia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class MateriaRepositoryImpl implements IMateriaRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Materia> seleccionarPorIdEstudiante(Integer id) {
		// TODO Auto-generated method stub
		TypedQuery<Materia> query = this.entityManager.createQuery("SELECT m FROM Materia m WHERE m.estudiante.id = :id", Materia.class);
		query.setParameter("id", id);
		
		return query.getResultList();
	}

}
