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

import com.example.demo.repository.modelo.Estudiante;
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
		/*
		 * Link link2 =linkTo(methodOn(EstudianteControllerRestFul.class).buscar(estu.getId())).withSelfRel();
		 */
				
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

	// capacidad que permita consultar una lista de estudiantes
	// http://localhost:8080/API/v1.0/Matricula/estudiantes
	// Cuando se tienen mas de un requesParam se lo debe separa con una &
	// en la url -> /consultarTodos?genero=M&edad=100
	// en el path -> esta no cambia
	// en el metodo -> (@RequestParam String genero, @RequesParam Integer edad)
	@GetMapping(path = "/tmp",produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<List<Estudiante>> consultarTodos(
			@RequestParam(required = false, defaultValue = "M") String genero) {
		List<Estudiante> lista = this.estudianteService.obtenerEstudiantes(genero);
		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mensaje_242", "Lista consultada de manera satisfactoria");
		cabeceras.add("mensaje_info", "El sistema va estar en mantenimiento el fin de semana");
		return new ResponseEntity<>(lista, cabeceras, 242);
	}

	// http://localhost:8080/API/v1.0/Matricula/estudiantes/11/materias
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EstudianteTO>> consultarTodosHateoas() {
		List<EstudianteTO> lista = this.estudianteService.buscarTodosTO();
		
		for(EstudianteTO est : lista) {
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
	public void guardar(@RequestBody Estudiante estudiante) {
		this.estudianteService.guardar(estudiante);
	}

	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void actualizar(@RequestBody Estudiante estudiante, @PathVariable Integer id) {
		estudiante.setId(id);
		this.estudianteService.actualizar(estudiante);
	}

	@PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void actualizarParcial(@RequestBody Estudiante estudiante, @PathVariable Integer id) {
		this.estudianteService.actualizarParcial(estudiante.getNombre(), estudiante.getApellido(), id);
	}

	@DeleteMapping(path = "/{id}")
	public void borrar(@PathVariable Integer id) {
		this.estudianteService.borrar(id);
	}

}
