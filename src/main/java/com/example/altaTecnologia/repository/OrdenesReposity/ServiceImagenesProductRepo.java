package com.example.altaTecnologia.repository.OrdenesReposity;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.altaTecnologia.models.ordenesDeServicio.imagenesProductos;

public interface ServiceImagenesProductRepo extends JpaRepository<imagenesProductos, Integer> {
	@Query("SELECT i FROM imagenesProductos i where i.idProducto=:id")
	List<imagenesProductos> buscarPorProducto(@Param("id") int id);

	@Transactional
	@Modifying
	@Query("DELETE  FROM imagenesProductos i where  i.idProducto=:id")
	void eliminarTodas(@Param("id") int id);
}
