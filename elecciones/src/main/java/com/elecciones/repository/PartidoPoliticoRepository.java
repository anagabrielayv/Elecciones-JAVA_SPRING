package com.elecciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elecciones.entity.PartidoPolitico;

@Repository
public interface PartidoPoliticoRepository extends JpaRepository<PartidoPolitico, Integer> {
	PartidoPolitico findByCodigo(int codigo);
	List<PartidoPolitico> findAll();
	
}
