package com.apiCurso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiCurso.entities.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}
