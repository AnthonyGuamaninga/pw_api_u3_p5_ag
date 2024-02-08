package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	
	private String msg;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> ingresar(@RequestBody Profesor profesor) {
		this.profesorService.ingresar(profesor);
		this.msg = "El profesor "+profesor.getApellido()+" ha sido registrado con exito";
		return ResponseEntity.status(HttpStatus.OK).body(msg);
	}
	
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<String> actualizar(@RequestBody Profesor profesor, @PathVariable Integer id) {
		profesor.setId(id);
		msg = "El profesor con id ("+profesor.getId()+") ha sido actualizado con exito";
		this.profesorService.modificar(profesor);
		return ResponseEntity.status(HttpStatus.OK).body(msg);
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Profesor> buscar(@PathVariable Integer id) {
		Profesor prof = this.profesorService.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(prof);
	}
	
	@DeleteMapping(path = "/{id}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<String> borrar(@PathVariable Integer id) {
		msg = "El profesor con id("+id+") ha sido eliminado con exito";
		this.profesorService.borrar(id);
		return ResponseEntity.status(HttpStatus.OK).body(msg);
	}
	
	@PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> modificarNacionalidadSueldo(@RequestBody Profesor profesor, @PathVariable Integer id) {
		profesor.setId(id);
		msg = "El profesor con id("+id+") ha sido actualizado parcialmente con exito";
		this.profesorService.modificarParcial(profesor.getNacionalidad(), profesor.getSueldo(), id);
		return ResponseEntity.status(HttpStatus.OK).body(msg);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<List<Profesor>> obtenerTodosOPorApellido(@RequestParam(required = false, defaultValue = "Castro") String apellido) {
		List<Profesor> listP = this.profesorService.buscarPorApellido(apellido); 
		return ResponseEntity.status(HttpStatus.OK).body(listP);
	}
	
	
	
}
