package com.example.altaTecnologia.models.ordenesDeServicio;

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

import com.example.altaTecnologia.models.Clientes.clientes;
import com.example.altaTecnologia.models.Clientes.cuentasxcobrar;

@Entity
@Table(name = "ordenservicio")
public class ordenservicio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne
	@JoinColumn(name = "clienteId")
	private clientes clienteId;
	private Date fecha;
	private String estatus;
	private double total;
	private Date vigencia;
	private int estatusFactura;
	private String observaciones;
	private int diasVigencia;
	private String tipoOrden;
	
	
	public String getTipoOrden() {
		return tipoOrden;
	}

	public void setTipoOrden(String tipoOrden) {
		this.tipoOrden = tipoOrden;
	}

	public int getDiasVigencia() {
		return diasVigencia;
	}

	public void setDiasVigencia(int diasVigencia) {
		this.diasVigencia = diasVigencia;
	}

	@OneToMany()
	@JoinColumn(name = "ordenId")
	private List<partidasordenservicios> partidas;

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<partidasordenservicios> getPartidas() {
		return partidas;
	}

	public void setPartidas(List<partidasordenservicios> partidas) {
		this.partidas = partidas;
	}

	@Override
	public String toString() {
		return "ordenservicio [id=" + id + ", clienteId=" + clienteId + ", fecha=" + fecha + ", vigencia=" + vigencia
				+ ", estatusFactura=" + estatusFactura + ", observaciones=" + observaciones + ", estadosDeCuenta="
				+ estadosDeCuenta + "]";
	}

	@OneToOne()
	@JoinColumn(name = "id")
	private cuentasxcobrar estadosDeCuenta;

	public cuentasxcobrar getEstadosDeCuenta() {
		return estadosDeCuenta;
	}

	public void setEstadosDeCuenta(cuentasxcobrar estadosDeCuenta) {
		this.estadosDeCuenta = estadosDeCuenta;
	}

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

	public int getEstatusFactura() {
		return estatusFactura;
	}

	public void setEstatusFactura(int estatusFactura) {
		this.estatusFactura = estatusFactura;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
