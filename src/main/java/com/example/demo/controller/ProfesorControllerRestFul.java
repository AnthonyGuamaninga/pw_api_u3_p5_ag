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
	
	@PostMapping(path = "/ingresar")
	public void ingresar(@RequestBody Profesor profesor) {
		this.profesorService.ingresar(profesor);
	}
	
	@PutMapping(path = "/actualizar")
	public void actualizar(@RequestBody Profesor profesor) {
		this.profesorService.modificar(profesor);
	}
	
	@GetMapping(path = "/buscar/{id}")
	public Profesor buscar(@PathVariable Integer id) {
		return this.profesorService.buscar(id);
	}
	
	@DeleteMapping(path = "/borrar/{id}")
	public void borrar(@PathVariable Integer id) {
		this.profesorService.borrar(id);
	}
	
	@PatchMapping("/modificarParcial")
	public void modificarNacionalidadSueldo(@RequestBody Profesor profesor) {
		this.profesorService.modificarParcial(profesor.getNacionalidad(), profesor.getSueldo(), profesor.getId());
	}
	
	@GetMapping(path = "/obtenerPorApellidos")
	public List<Profesor> obtenerPorApellido(@RequestParam String apellido) {
		return this.profesorService.buscarPorApellido(apellido);
	}
	
	
	
}
