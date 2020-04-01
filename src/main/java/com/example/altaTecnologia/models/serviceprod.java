package com.example.altaTecnologia.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="serviceprod")
public class serviceprod {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String descr;
	private double precio;
	
	@OneToOne()
	@JoinColumn(name = "idUnidad")
	private unidadmedida idUnidad;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public unidadmedida getIdUnidad() {
		return idUnidad;
	}
	public void setIdUnidad(unidadmedida idUnidad) {
		this.idUnidad = idUnidad;
	}
		


	
	
}
