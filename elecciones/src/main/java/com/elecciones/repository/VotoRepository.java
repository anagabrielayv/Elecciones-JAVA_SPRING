package com.elecciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elecciones.entity.Alumno;
import com.elecciones.entity.Voto;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Integer> {
	Voto findByCodigo(int codigo);
	Voto findByAlumno(Alumno alumno);

}
