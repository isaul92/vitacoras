package com.example.altaTecnologia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.altaTecnologia.models.proyectosRoca;
import com.example.altaTecnologia.repository.IServiceServiciosRoca.ServicesProyectosRocaRepo;
@Service
public class ServiceProyectosRoca implements IproyectosRoca {

	@Autowired
	private ServicesProyectosRocaRepo repoProyect;

	@Override
	public void eliminar(int id) {
		repoProyect.deleteById(id);

	}

	@Override
	public void guardar(proyectosRoca r) {
		repoProyect.save(r);
	}

	@Override
	public List<proyectosRoca> buscarTodos() {
		// TODO Auto-generated method stub
		return repoProyect.findAll();
	}

	@Override
	public Optional buscarPorId(int id) {
		// TODO Auto-generated method stub
		return repoProyect.findById(id);
	}

	@Override
	public Page<proyectosRoca> getAll(Pageable page) {
		// TODO Auto-generated method stub
		return repoProyect.findAll(page);
	}

}
