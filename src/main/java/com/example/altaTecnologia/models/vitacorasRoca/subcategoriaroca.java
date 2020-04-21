package com.example.altaTecnologia.models.vitacorasRoca;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subcategoriaroca")
public class subcategoriaroca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public categoriasroca getIdCat() {

		return idCat;
	}

	public void setIdCat(categoriasroca idCat) {
		this.idCat = idCat;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@OneToOne()
	@JoinColumn(name = "idCat")
	private categoriasroca idCat;

	private String nombre;

}
