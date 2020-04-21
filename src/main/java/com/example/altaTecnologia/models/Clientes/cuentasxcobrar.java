package com.example.altaTecnologia.models.Clientes;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.altaTecnologia.models.ordenesDeServicio.ordenservicio;

@Entity
@Table(name = "cuentasxcobrar")
public class cuentasxcobrar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne()
	@JoinColumn(name = "idFactura")
	private ordenservicio idFactura;
	private Date fecha;
	@OneToMany()
	@JoinColumn(name = "idCuentaCobrar")
	private List<abonos> idAbonos;
	private double deudaTotal;
	private double dedudaActual;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ordenservicio getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(ordenservicio idFactura) {
		this.idFactura = idFactura;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getDeudaTotal() {
		return deudaTotal;
	}

	public void setDeudaTotal(double deudaTotal) {
		this.deudaTotal = deudaTotal;
	}

	public List<abonos> getIdAbonos() {
		return idAbonos;
	}

	public void setIdAbonos(List<abonos> idAbonos) {
		this.idAbonos = idAbonos;
	}

	public double getDedudaActual() {
		return dedudaActual;
	}

	public void setDedudaActual(double dedudaActual) {
		this.dedudaActual = dedudaActual;
	}

}
