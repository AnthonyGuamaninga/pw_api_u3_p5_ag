package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.IEstudianteService;
import com.example.demo.service.IMateriaService;
import com.example.demo.service.dto.EstudianteLigeroTO;
import com.example.demo.service.to.EstudianteTO;
import com.example.demo.service.to.MateriaTO;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController // Servicio
@RequestMapping(path = "/estudiantes")
public class EstudianteControllerRestFul {

	@Autowired
	private IEstudianteService estudianteService;
	
	@Autowired
	private IMateriaService iMateriaService;
	/*
	 *Metodos: capacidades
	 *GET
	 *en la url -> /buscar/4/Daniel
	 *en el path -> /buscar/{id}/{nombre}
	 *en el metodo -> @PathVariable: @PathVariable (Integer id, @PathVariable String nombre) 
	 */
	@GetMapping(path = "/{id}/completo", produces = "application/json")
	public ResponseEntity<EstudianteTO> buscar(@PathVariable Integer id) {
		
		EstudianteTO estu = this.estudianteService.buscarTO(id);
		Link link =linkTo(methodOn(EstudianteControllerRestFul.class).consultarMateriasPorId(estu.getId()))
				.withRel("susMaterias");				
		estu.add(link);
		return ResponseEntity.status(HttpStatus.OK).body(estu);
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<EstudianteLigeroTO> buscarLigero(@PathVariable Integer id){
		EstudianteLigeroTO estuLigero = this.estudianteService.buscarLigeroTO(id);
		Link link = linkTo(methodOn(EstudianteControllerRestFul.class).buscar(id)).withSelfRel();
		estuLigero.add(link);
		return ResponseEntity.status(HttpStatus.OK).body(estuLigero);
	}


	// http://localhost:8080/API/v1.0/Matricula/estudiantes/11/materias
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EstudianteLigeroTO>> consultarTodosHateoas() {
		List<EstudianteLigeroTO> lista = this.estudianteService.buscarTodosLigeroTO();
		
		for(EstudianteLigeroTO est : lista) {
			Link link =linkTo(methodOn(EstudianteControllerRestFul.class).consultarMateriasPorId(est.getId()))
					.withRel("susMaterias");
			est.add(link);
		}
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}
	
	@GetMapping(path = "/{id}/materias", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MateriaTO>> consultarMateriasPorId(@PathVariable Integer id){
		List<MateriaTO> lista = this.iMateriaService.buscarPorIdEstudiante(id); 
		return ResponseEntity.status(HttpStatus.OK).body(lista); 
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> guardar(@RequestBody EstudianteTO estudiante) {
		this.estudianteService.guardar(estudiante);
		return ResponseEntity.status(HttpStatus.OK).body("Estudiante "+estudiante.getApellido()+" insertado con exito!");
	}

	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> actualizar(@RequestBody EstudianteTO estudiante, @PathVariable Integer id) {
		estudiante.setId(id);
		this.estudianteService.actualizar(estudiante);
		return ResponseEntity.status(HttpStatus.OK).body("Estudiante "+id+" actualizado exitosamente");
	}

	@PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> actualizarParcial(@RequestBody EstudianteTO estudiante, @PathVariable Integer id) {
		this.estudianteService.actualizarParcial(estudiante.getNombre(), estudiante.getApellido(), id);
		return ResponseEntity.status(HttpStatus.OK).body("Estudiante "+id+" actualizado parcialmete de forma exitosa");
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> borrar(@PathVariable Integer id) {
		this.estudianteService.borrar(id);
		return ResponseEntity.status(HttpStatus.OK).body("El estudiante "+id+" ha sido eliminado");
	}

}
