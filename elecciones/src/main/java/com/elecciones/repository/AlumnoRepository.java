package com.elecciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elecciones.entity.Alumno;
import com.elecciones.entity.Usuario;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
	Alumno findByCodigo(int codigo);
	Alumno findByUsuario(Usuario usuario);
}
