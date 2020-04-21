package com.example.altaTecnologia.repository.OrdenesReposity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.altaTecnologia.models.ordenesDeServicio.categoriasProductos;

public interface ServicecategoriasProductos extends JpaRepository<categoriasProductos, Integer> {

	@Query("SELECT c FROM categoriasProductos c where c.estatus=1")
	List<categoriasProductos> buscarTodas();
	@Query("SELECT c FROM categoriasProductos c where c.estatus=1")
	Page<categoriasProductos> buscarTodos(Pageable page);
	
	@Query("SELECT c FROM categoriasProductos c where c.estatus=1 and c.id=:id")
	Optional<categoriasProductos> buscarPorId(@Param ("id") int id);
	
	
}
