package com.example.altaTecnologia.repository.OrdenesReposity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.altaTecnologia.models.ordenesDeServicio.serviceprod;

public interface ServiceProdServ extends JpaRepository<serviceprod, Integer> {

	@Query("SELECT s FROM serviceprod s where s.id=:id and s.estatusEliminada=1")
	Optional<serviceprod> buscarPorId(@Param("id") int id);

	@Query("SELECT s FROM serviceprod s where s.estatusEliminada=1")
	List<serviceprod> mostrarTodas();

	@Query("SELECT s FROM serviceprod s where s.estatusEliminada=1")
	Page<serviceprod> indexPaginate(Pageable page);

	@Query("SELECT s FROM serviceprod s where s.estatusEliminada=1 and s.id=:id")
	Page<serviceprod> indexPaginateId(Pageable page, @Param("id") int id);

	@Query("SELECT s FROM serviceprod s where s.estatusEliminada=1 and s.nombre LIKE %:nombre%")
	Page<serviceprod> indexPaginateNombre(Pageable page, @Param("nombre") String nombre);

	@Query("SELECT s FROM serviceprod s where s.estatusEliminada=1 and s.descr LIKE %:descr%")
	Page<serviceprod> indexPaginateDescrip(Pageable page, @Param("descr") String descr);

}
