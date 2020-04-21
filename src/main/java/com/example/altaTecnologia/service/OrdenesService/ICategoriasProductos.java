package com.example.altaTecnologia.service.OrdenesService;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.altaTecnologia.models.ordenesDeServicio.categoriasProductos;

public interface ICategoriasProductos {

	
	void guardar(categoriasProductos categoriasProductos);
	void eliminar(int id);
	List<categoriasProductos> buscarTodas();
	Page <categoriasProductos> buscarTodas(Pageable page);
	Optional <categoriasProductos> buscarPorId(int id);
}
