package com.example.altaTecnologia.service.OrdenesService.Clientes;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.altaTecnologia.models.Clientes.clientes;
import com.example.altaTecnologia.models.Clientes.cuentasxcobrar;

public interface ICuentasxCobrar {

	
	
	void guardar(cuentasxcobrar c);
	void cancelar(int c);
	List<cuentasxcobrar> buscarTodos();
	Page<cuentasxcobrar> indexPaginate(Pageable page);	
	List<cuentasxcobrar> buscarPorCliente(clientes c);
	
	
}
