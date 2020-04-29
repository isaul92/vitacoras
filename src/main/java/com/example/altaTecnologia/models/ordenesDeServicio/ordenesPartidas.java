package com.example.altaTecnologia.models.ordenesDeServicio;

import java.util.LinkedList;
import java.util.List;

public class ordenesPartidas {
	private List<partidas> partida;
	private double total;
	private int contador = 0;

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public ordenesPartidas() {
		partida = new LinkedList<partidas>();
	}

	public List<partidas> getPartida() {
		return partida;
	}

	public void setPartida(List<partidas> partida) {
		this.partida = partida;
	}

}
