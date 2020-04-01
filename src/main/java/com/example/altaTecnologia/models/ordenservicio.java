package com.example.altaTecnologia.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ordenservicio")
public class ordenservicio {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToOne
	@JoinColumn(name="clienteId")
	private clientes clienteId;
	private Date fecha;
	private Date vigencia;
	private int estatusFecha;
	private String observaciones;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public clientes getClienteId() {
		return clienteId;
	}
	public void setClienteId(clientes clienteId) {
		this.clienteId = clienteId;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Date getVigencia() {
		return vigencia;
	}
	public void setVigencia(Date vigencia) {
		this.vigencia = vigencia;
	}
	public int getEstatusFecha() {
		return estatusFecha;
	}
	public void setEstatusFecha(int estatusFecha) {
		this.estatusFecha = estatusFecha;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	

}
