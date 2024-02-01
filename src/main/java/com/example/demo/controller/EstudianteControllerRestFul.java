package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.IEstudianteService;
 
@RestController //Servicio
@RequestMapping(path = "/estudiantes")
public class EstudianteControllerRestFul {

	@Autowired
	private IEstudianteService estudianteService;
	
	//Metodos: capacidades
	// GET
	// en la url ->  /buscar/4/Daniel
	// en el path -> /buscar/{id}/{nombre}
	// en el metodo -> @PathVariable: @PathVariable (Integer id, @PathVariable String nombre) 
	@GetMapping(path = "/buscar/{id}")
	public Estudiante buscar(@PathVariable Integer id) {
		return estudianteService.buscar(id);
	}
	//http://localhost:8080/API/v1.0/Matricula/estudiantes/buscar
	
	@PostMapping(path = "/guardar")
	public void guardar(@RequestBody Estudiante estudiante) {
		this.estudianteService.guardar(estudiante);
	}
	
	@PutMapping(path = "/actualizar")
	public void actualizar(@RequestBody Estudiante estudiante) {
		this.estudianteService.actualizar(estudiante);
	}
	
	@PatchMapping(path = "/actualizarParcial")
	public void actualizarParcial(@RequestBody Estudiante estudiante) {
		this.estudianteService.actualizarParcial(estudiante.getNombre(), estudiante.getApellido(), estudiante.getId());
	}
	
	@DeleteMapping(path = "/borrar/{id}")
	public void borrar(@PathVariable Integer id) {
		this.estudianteService.borrar(id);
	}
	
	//capacidad que permita consultar una lista de estudiantes
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/consultarTodos?genero=M
	// Cuando se tienen mas de un requesParam se lo debe separa con una &
	// en la url -> /consultarTodos?genero=M&edad=100
	// en el path -> esta no cambia
	// en el metodo -> (@RequestParam String genero, @RequesParam Integer edad)
	@GetMapping(path = "/consultarTodos")
	public List<Estudiante> consultarTodos(@RequestParam String genero){
		return this.estudianteService.obtenerEstudiantes(genero);
	}
	
}
