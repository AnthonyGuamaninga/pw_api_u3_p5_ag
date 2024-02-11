package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IEstudianteRepository;
import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.dto.EstudianteLigeroTO;
import com.example.demo.service.to.EstudianteTO;


@Service
public class EstudianteServiceImpl implements IEstudianteService{

	@Autowired
	private IEstudianteRepository estudianteRepository;
	
	@Override
	public void guardar(EstudianteTO estudiante) {
		// TODO Auto-generated method stub
		
		this.estudianteRepository.insertar(this.revertir(estudiante));
	}

	@Override
	public void actualizar(EstudianteTO estudiante) {
		// TODO Auto-generated method stub
		this.estudianteRepository.actualizar(this.revertir(estudiante));
	}

	@Override
	public void actualizarParcial(String nombre, String apellido, Integer id) {
		// TODO Auto-generated method stub
		this.estudianteRepository.actualizarParcial(nombre, apellido, id);
	}


	@Override
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		this.estudianteRepository.eliminar(id);
	}


	@Override
	public List<EstudianteLigeroTO> buscarTodosLigeroTO() {
		// TODO Auto-generated method stub
		List<Estudiante> lista = this.estudianteRepository.buscarEstudiantes("M");
		List<EstudianteLigeroTO> listaFinal = new ArrayList<>();
		//listaFinal = lista.forEach(lst -> convertir(lst));
		for(Estudiante e : lista) {
			listaFinal.add(this.convertirLigero(e));
		}
		return listaFinal;
	}
	
	private EstudianteTO convertir(Estudiante estu) {
		EstudianteTO estuTO = new EstudianteTO();
		estuTO.setApellido(estu.getApellido());
		estuTO.setFechaNacimiento(estu.getFechaNacimiento());
		estuTO.setGenero(estu.getGenero());
		estuTO.setId(estu.getId());
		estuTO.setNombre(estu.getNombre());
		estuTO.setCarrera(estu.getCarrera());
		estuTO.setDireccion(estu.getDireccion());
		estuTO.setHobby(estu.getHobby());
		estuTO.setPais(estu.getPais());
		estuTO.setTipoEstudiante(estu.getTipoEstudiante());
		estuTO.setTipoSangre(estu.getTipoSangre());
		return estuTO;
	}
	
	private EstudianteLigeroTO convertirLigero(Estudiante estu) {
		EstudianteLigeroTO estuLigero = new EstudianteLigeroTO();
		estuLigero.setId(estu.getId());
		estuLigero.setApellido(estu.getApellido());
		estuLigero.setNombre(estu.getNombre());
		return estuLigero;
	} 
	
	private Estudiante revertir(EstudianteTO estuTO) {
		Estudiante estudiante = new Estudiante();
		estudiante.setApellido(estuTO.getApellido());
		estudiante.setFechaNacimiento(estuTO.getFechaNacimiento());
		estudiante.setGenero(estuTO.getGenero());
		estudiante.setId(estuTO.getId());
		estudiante.setNombre(estuTO.getNombre());
		estudiante.setCarrera(estuTO.getCarrera());
		estudiante.setDireccion(estuTO.getDireccion());
		estudiante.setHobby(estuTO.getHobby());
		estudiante.setPais(estuTO.getPais());
		estudiante.setTipoEstudiante(estuTO.getTipoEstudiante());
		estudiante.setTipoSangre(estuTO.getTipoSangre());
		return estudiante;
	}

	@Override
	public EstudianteTO buscarTO(Integer id) {
		// TODO Auto-generated method stub
		return this.convertir(this.estudianteRepository.seleccionar(id));
	}

	@Override
	public EstudianteLigeroTO buscarLigeroTO(Integer id) {
		// TODO Auto-generated method stub
		return this.convertirLigero(this.estudianteRepository.seleccionar(id));
	}

}
