package com.example.altaTecnologia.service.OrdenesService;

import java.util.List;

import com.example.altaTecnologia.models.ordenesDeServicio.ordenservicio;
import com.example.altaTecnologia.models.ordenesDeServicio.partidasordenservicios;

public interface IPartidas {

	void guardar(partidasordenservicios partidasordenservicios);

	void eliminar(int id);

	List<partidasordenservicios> buscarPorOrden(ordenservicio ordenservicio);
}
