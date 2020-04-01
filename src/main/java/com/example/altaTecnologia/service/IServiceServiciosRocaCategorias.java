package com.example.altaTecnologia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.altaTecnologia.models.categoriasroca;

public interface IServiceServiciosRocaCategorias {

	
	List<categoriasroca> buscarTodas();
	Optional < categoriasroca> buscarPorId(int id);
	void guardar(categoriasroca categoriasroca);
	void eliminar(int id);
	Page<categoriasroca> getAll(Pageable page);
	
	
}
