package com.example.altaTecnologia.repository.OrdenesReposity.Clientes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.altaTecnologia.models.Clientes.clientes;
import com.example.altaTecnologia.models.Clientes.datosfiscales;


public interface ServiceDatosFiscalesRepo extends JpaRepository<datosfiscales, Integer> {

	
	@Query("SELECT d FROM datosfiscales d WHERE d.idCliente=:id")
	Page<datosfiscales> buscarTodosPorIdCliente(Pageable page,@Param ("id") clientes id);
	
	@Query("SELECT d FROM datosfiscales d WHERE d.rfc=:rfc")
	Page<datosfiscales> buscarTodosPorRFC(Pageable page,@Param("rfc") String rfc);
	
	
}
