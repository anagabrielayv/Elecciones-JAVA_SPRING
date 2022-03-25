package com.elecciones.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "T_VOTO")
public class Voto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo")
	private Integer codigo;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="codigoAlumno",referencedColumnName="codigo")	
	private Alumno alumno;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="codigoPartido",referencedColumnName="codigo")	
	private PartidoPolitico partido;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public PartidoPolitico getPartido() {
		return partido;
	}

	public void setPartido(PartidoPolitico partido) {
		this.partido = partido;
	}
	
}
