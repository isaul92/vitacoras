package com.example.altaTecnologia.service.OrdenesService.Clientes;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.altaTecnologia.models.Clientes.clientes;

public interface IClientes {

	void guardar(clientes clientes);
	void eliminar(int id);
	List<clientes> buscarTodos();
	Optional <clientes> buscarPorId(int id);
	Page <clientes> indexPaginate(Pageable page);
	Page <clientes> indexPaginatePorNombre(Pageable page, String nombre);
	List<clientes> buscarPorNombre(String nombre);
	
	
	
	
}
