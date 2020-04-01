package com.example.altaTecnologia.models;

import javax.persistence.Entity;



public class reporte {

	
	

public reporte(String nombre, Long numeroReportes) {
	
		this.nombre = nombre;
		this.numeroReportes = numeroReportes;
	}
private String nombre;
	public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}


	public Long getNumeroReportes() {
	return numeroReportes;
}
public void setNumeroReportes(Long numeroReportes) {
	this.numeroReportes = numeroReportes;
}
	private Long numeroReportes;
	
	
	
}
