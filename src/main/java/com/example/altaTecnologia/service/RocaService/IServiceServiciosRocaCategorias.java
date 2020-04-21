package com.example.altaTecnologia.service.RocaService;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.altaTecnologia.models.vitacorasRoca.categoriasroca;

public interface IServiceServiciosRocaCategorias {

	
	List<categoriasroca> buscarTodas();
	Optional < categoriasroca> buscarPorId(int id);
	void guardar(categoriasroca categoriasroca);
	void eliminar(int id);
	Page<categoriasroca> getAll(Pageable page);
	
	
}
