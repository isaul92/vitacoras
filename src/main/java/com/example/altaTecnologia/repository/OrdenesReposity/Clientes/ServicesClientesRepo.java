package com.example.altaTecnologia.repository.OrdenesReposity.Clientes;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.altaTecnologia.models.Clientes.clientes;

public interface ServicesClientesRepo extends JpaRepository<clientes, Integer> {

	@Query("SELECT c FROM clientes c where c.estatusElim=1")
	List<clientes> buscarTodos();

	

	@Query("SELECT c FROM clientes c where c.estatusElim=1")
	Page<clientes> buscarTodos(Pageable page);

	
	
	@Query("SELECT c FROM clientes c where c.estatusElim=1 and c.nombre LIKE %:nombre%")
	Page<clientes> buscarTodosPorNombre(Pageable page,@Param("nombre") String nombre);
	
	
	@Query("SELECT c FROM clientes c where c.estatusElim=1 and c.nombre=:nombre")
	List<clientes> buscarTodosPorNombre(@Param("nombre") String nombre);

	
	@Query("SELECT c FROM clientes c where c.estatusElim=1 and c.id=:id")
	Optional<clientes> buscarPorId(@Param ("id") int id);
	
	
}
