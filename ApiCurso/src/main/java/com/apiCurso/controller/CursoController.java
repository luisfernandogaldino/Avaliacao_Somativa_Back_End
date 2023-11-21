package com.apiCurso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiCurso.entities.Curso;
import com.apiCurso.service.CursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Curso", description = "API REST DE GERENCIAMENTO DE CURSO")
@RestController
@RequestMapping("/curso")
public class CursoController {

	private final CursoService cursoService;

	@Autowired
	public CursoController(CursoService cursoService) {
		this.cursoService = cursoService;
	}

	@GetMapping("/{id}")
	@Operation(summary ="Apresenta todo o curso por ID")
	public ResponseEntity<Curso> buscaCursoControlId(@PathVariable Long id) {
		Curso Curso = cursoService.buscaCursoId(id);
		if (Curso != null) {
			return ResponseEntity.ok(Curso);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping
	@Operation(summary ="Apresenta todo o curso")
	public ResponseEntity<List<Curso>> buscaTodosCursoControl() {
		List<Curso> Curso = cursoService.buscaTodosCurso();
		return ResponseEntity.ok(Curso);
	}

	@PostMapping
	@Operation(summary="Cadastra o curso curso")
	public ResponseEntity<Curso> salvaCursoControl(@RequestBody @Valid Curso curso) {
		Curso salvaCurso = cursoService.salvaCurso(curso);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaCurso);
	}

	@PutMapping("/{id}")
	@Operation(summary="Altera o curso por ID")
	public ResponseEntity<Curso> alterarCursoControl(@PathVariable Long id, @RequestBody @Valid Curso Curso) {
		Curso alterarCurso = cursoService.alterarCurso(id, Curso);
		if (alterarCurso != null) {
			return ResponseEntity.ok(Curso);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary="Deleta o curso")
	public ResponseEntity<Curso> apagaCursoControl(@PathVariable Long id) {
		boolean apagar = cursoService.apagarCurso(id);
		if (apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}

