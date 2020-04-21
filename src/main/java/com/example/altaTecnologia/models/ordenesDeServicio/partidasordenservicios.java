package com.example.altaTecnologia.models.ordenesDeServicio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "partidasordenservicios")
public class partidasordenservicios {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne()
	@JoinColumn(name = "ordenId")
	private ordenservicio ordenId;

	@OneToOne
	@JoinColumn(name = "servProdId")
	private serviceprod servProdId;
	private int cantidad;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ordenservicio getOrdenId() {
		return ordenId;
	}

	public void setOrdenId(ordenservicio ordenId) {
		this.ordenId = ordenId;
	}

	public serviceprod getServProdId() {
		return servProdId;
	}

	public void setServProdId(serviceprod servProdId) {
		this.servProdId = servProdId;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
