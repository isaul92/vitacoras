package com.example.altaTecnologia.service.OrdenesService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.altaTecnologia.models.ordenesDeServicio.ordenservicio;
import com.example.altaTecnologia.models.ordenesDeServicio.partidasordenservicios;
import com.example.altaTecnologia.repository.OrdenesReposity.ServiceOrdenesServicioRepo;
import com.example.altaTecnologia.repository.OrdenesReposity.ServicePartidas;

@Service
public class PartidasService implements IPartidas {
	@Autowired
	private ServicePartidas repoPartidas;

	@Autowired
	private ServiceOrdenesServicioRepo repoOrdenes;

	@Override
	public void guardar(partidasordenservicios partidasordenservicios) {
		repoPartidas.save(partidasordenservicios);
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<partidasordenservicios> buscarPorOrden(ordenservicio ordenservicio) {
		// TODO Auto-generated method stub
		return null;
	}

}
