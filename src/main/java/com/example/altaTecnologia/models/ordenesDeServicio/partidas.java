package com.example.altaTecnologia.models.ordenesDeServicio;

public class partidas {
	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	private double importe;
	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}
	private serviceprod servProdId;
	private int cantidad;

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
