package com.example.altaTecnologia.models.vitacorasRoca;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "vitacora")
public class vitacora {

	public int getId() {
		return id;
	}


	@Override
	public String toString() {
		return "vitacora [id=" + id + ", Fecha=" + Fecha + ", horaInicial=" + horaInicial + ", horaFinal=" + horaFinal
				+ ", cusategoriaActiv=" + cusategoriaActiv + ", proyecto=" + proyecto + ", licencia=" + licencia
				+ ", contacto=" + contacto + ", correoTel=" + correoTel + ", estatus=" + estatus + ", subCat=" + subCat
				+ ", notas=" + notas + "]";
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getFecha() {
		return Fecha;
	}


	public void setFecha(Date fecha) {
		Fecha = fecha;
	}




	public String getHoraInicial() {
		return horaInicial;
	}


	public void setHoraInicial(String horaInicial) {
		this.horaInicial = horaInicial;
	}


	public String getHoraFinal() {
		return horaFinal;
	}


	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}


	public categoriasroca getCusategoriaActiv() {
		return cusategoriaActiv;
	}


	public void setCusategoriaActiv(categoriasroca cusategoriaActiv) {
		this.cusategoriaActiv = cusategoriaActiv;
	}




	public proyectosRoca getProyecto() {
		return proyecto;
	}


	public void setProyecto(proyectosRoca proyecto) {
		this.proyecto = proyecto;
	}


	public String getLicencia() {
		return licencia;
	}


	public void setLicencia(String licencia) {
		this.licencia = licencia;
	}


	public String getContacto() {
		return contacto;
	}


	public void setContacto(String contacto) {
		this.contacto = contacto;
	}


	public String getCorreoTel() {
		return correoTel;
	}


	public void setCorreoTel(String correoTel) {
		this.correoTel = correoTel;
	}


	public String getEstatus() {
		return estatus;
	}


	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}


	public subcategoriaroca getSubCat() {
		return subCat;
	}


	public void setSubCat(subcategoriaroca subCat) {
		this.subCat = subCat;
	}


	public String getNotas() {
		return notas;
	}


	public void setNotas(String notas) {
		this.notas = notas;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date Fecha;
	private String horaInicial;
	private String horaFinal;

	public int getMinDuracion() {
		return minDuracion;
	}


	public void setMinDuracion(int minDuracion) {
		this.minDuracion = minDuracion;
	}


	@OneToOne
	@JoinColumn(name = "categoriaActiv")
	private categoriasroca cusategoriaActiv;

	// one to one
	@OneToOne
	@JoinColumn(name="proyecto")
	private proyectosRoca proyecto;

	private String licencia;
	private String contacto;
	private String correoTel;
	private String estatus;

	@OneToOne
	@JoinColumn(name = "subCat")
	private subcategoriaroca subCat;

private int minDuracion;
	private String notas;

}
