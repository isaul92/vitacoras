package com.example.altaTecnologia.models.ordenesDeServicio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "serviceprod")
public class serviceprod {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int id;
	private String imgPrincipal;

	public String getImgPrincipal() {
		return imgPrincipal;
	}

	public void setImgPrincipal(String imgPrincipal) {
		this.imgPrincipal = imgPrincipal;
	}

	public categoriasProductos getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(categoriasProductos categoriaId) {
		this.categoriaId = categoriaId;
	}

	private String nombre;
	private String descr;
	private double precio;

	private int estatusEliminada;

	public int getEstatusEliminada() {
		return estatusEliminada;
	}

	public void setEstatusEliminada(int estatusEliminada) {
		this.estatusEliminada = estatusEliminada;
	}

	private String estatus;
	@OneToOne
	@JoinColumn(name = "categoriaId")
	private categoriasProductos categoriaId;

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

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
