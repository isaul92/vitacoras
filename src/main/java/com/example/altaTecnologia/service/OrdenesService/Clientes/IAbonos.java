package com.example.altaTecnologia.service.OrdenesService.Clientes;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.altaTecnologia.models.Clientes.abonos;
import com.example.altaTecnologia.models.ordenesDeServicio.ordenservicio;

public interface IAbonos {

	
	void guardar(abonos abono);
	void eliminar (int id);
	Optional <abonos> buscarPorId(int id);
	List<abonos> buscarTodos(ordenservicio orden);
	Page<abonos> buscarTodos(Pageable page,abonos abono);
}
