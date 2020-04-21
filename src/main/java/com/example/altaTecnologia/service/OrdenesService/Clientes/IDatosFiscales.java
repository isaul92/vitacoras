package com.example.altaTecnologia.service.OrdenesService.Clientes;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.altaTecnologia.models.Clientes.datosfiscales;

public interface IDatosFiscales {

	
	void guardar(datosfiscales datosfiscales);
	void eliminar(int id);
	Page<datosfiscales> indexPaginate(Pageable page);
	List<datosfiscales> buscarTodos();
	Page<datosfiscales> indexPaginate(Pageable page,String nombre);
	datosfiscales buscarPorId(int id);
	List<datosfiscales> buscarPorIdCliente(int id);
	Optional<datosfiscales> buscarPorIdO(int id);
	
	
}
