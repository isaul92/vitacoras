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
@Table(name = "requisitosfiscales")
public class requisitosfiscales {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne()
	@JoinColumn(name="idRfc")
	private datosfiscales datosfiscales;
	private String tipoCet;
	private String cer;
	private String keyFiscal;
	private Date vigenciaIni;
	private Date vigencialFin;
	private double duracion;
	
	
	public double getDuracion() {
		return duracion;
	}


	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}


	public int getId() {
		return id;
	}
	
	
	@Override
	public String toString() {
		return "requisitosfiscales [id=" + id + ", datosfiscales=" + datosfiscales + ", tipoCet=" + tipoCet + ", cer="
				+ cer + ", keyFiscal=" + keyFiscal + ", vigenciaIni=" + vigenciaIni + ", vigencialFin=" + vigencialFin
				+ "]";
	}


	public void setId(int id) {
		this.id = id;
	}
	public datosfiscales getDatosfiscales() {
		return datosfiscales;
	}
	public void setDatosfiscales(datosfiscales datosfiscales) {
		this.datosfiscales = datosfiscales;
	}
	public String getTipoCet() {
		return tipoCet;
	}
	public void setTipoCet(String tipoCet) {
		this.tipoCet = tipoCet;
	}
	public String getCer() {
		return cer;
	}
	public void setCer(String cer) {
		this.cer = cer;
	}
	public String getKeyFiscal() {
		return keyFiscal;
	}
	public void setKeyFiscal(String keyFiscal) {
		this.keyFiscal = keyFiscal;
	}
	public Date getVigenciaIni() {
		return vigenciaIni;
	}
	public void setVigenciaIni(Date vigenciaIni) {
		this.vigenciaIni = vigenciaIni;
	}
	public Date getVigencialFin() {
		return vigencialFin;
	}
	public void setVigencialFin(Date vigencialFin) {
		this.vigencialFin = vigencialFin;
	}
	
}
