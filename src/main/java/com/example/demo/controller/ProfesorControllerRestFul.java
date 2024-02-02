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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.modelo.Profesor;
import com.example.demo.service.IProfesorService;

@RestController
@RequestMapping(path = "/profesores")
public class ProfesorControllerRestFul {

	@Autowired
	private IProfesorService profesorService;
	
	@PostMapping
	public void ingresar(@RequestBody Profesor profesor) {
		this.profesorService.ingresar(profesor);
	}
	
	@PutMapping(path = "/{id}")
	public void actualizar(@RequestBody Profesor profesor, @PathVariable Integer id) {
		profesor.setId(id);
		this.profesorService.modificar(profesor);
	}
	
	@GetMapping(path = "/{id}")
	public Profesor buscar(@PathVariable Integer id) {
		return this.profesorService.buscar(id);
	}
	
	@DeleteMapping(path = "/{id}")
	public void borrar(@PathVariable Integer id) {
		this.profesorService.borrar(id);
	}
	
	@PatchMapping(path = "/{id}")
	public void modificarNacionalidadSueldo(@RequestBody Profesor profesor, @PathVariable Integer id) {
		profesor.setId(id);
		this.profesorService.modificarParcial(profesor.getNacionalidad(), profesor.getSueldo(), id);
	}
	
	@GetMapping
	public List<Profesor> obtenerTodosOPorApellido(@RequestParam(required = false, defaultValue = "Castro") String apellido) {
		return this.profesorService.buscarPorApellido(apellido);
	}
	
	
	
}
