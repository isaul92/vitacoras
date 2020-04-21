package com.example.altaTecnologia.models.Clientes;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "datosfiscales")
public class datosfiscales {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	
	@OneToMany()
	@JoinColumn(name="idRfc")
	private List<requisitosfiscales> idFiscal;
	private String rfc;

	private String pais;
	public List<requisitosfiscales> getIdFiscal() {
		return idFiscal;
	}

	public void setIdFiscal(List<requisitosfiscales> idFiscal) {
		this.idFiscal = idFiscal;
	}

	private String calle;
	private String numIntExt;
	private String colonia;
	private String cp;
	private String ciudad;
	private String estado;
	private String correo;
	private String telefono;
	private int estatusElim;
	@ManyToOne()
	@JoinColumn(name = "idCliente")
	private clientes idCliente;

	
	
	public int getEstatusElim() {
		return estatusElim;
	}

	public void setEstatusElim(int estatusElim) {
		this.estatusElim = estatusElim;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumIntExt() {
		return numIntExt;
	}

	public void setNumIntExt(String numIntExt) {
		this.numIntExt = numIntExt;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public clientes getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(clientes idCliente) {
		this.idCliente = idCliente;
	}

}
