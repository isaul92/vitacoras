package com.example.altaTecnologia.models.Clientes;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class clientes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String apellidos;

	private String telefono;
	private String correo;
	private int estatusElim;
	@OneToMany()
	@JoinColumn(name = "idCliente")
	private List<datosfiscales> idDatosFiscales;

	public List<datosfiscales> getIdDatosFiscales() {
		return idDatosFiscales;
	}

	@Override
	public String toString() {
		return "clientes [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", idDatosFiscales="
				+ idDatosFiscales + ", telefono=" + telefono + ", correo=" + correo + ", estatusElim=" + estatusElim
				+ "]";
	}

	public void setIdDatosFiscales(List<datosfiscales> idDatosFiscales) {
		this.idDatosFiscales = idDatosFiscales;
	}

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public List<datosfiscales> getDatosfiscales() {
		return idDatosFiscales;
	}

	public void setDatosfiscales(List<datosfiscales> idDatosFiscales) {
		this.idDatosFiscales = idDatosFiscales;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

}
