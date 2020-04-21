package com.example.altaTecnologia.models.Clientes;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "abonos")
public class abonos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne()
	@JoinColumn(name = "idCuentaCobrar")
	private cuentasxcobrar idCuentaCobrar;

	private double abono;

	private Date fecha;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public cuentasxcobrar getIdCuentaCobrar() {
		return idCuentaCobrar;
	}

	public void setIdCuentaCobrar(cuentasxcobrar idCuentaCobrar) {
		this.idCuentaCobrar = idCuentaCobrar;
	}

	public double getAbono() {
		return abono;
	}

	public void setAbono(double abono) {
		this.abono = abono;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
