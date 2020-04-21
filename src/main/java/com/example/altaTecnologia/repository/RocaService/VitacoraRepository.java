package com.example.altaTecnologia.repository.RocaService;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.altaTecnologia.models.vitacorasRoca.vitacora;

public interface VitacoraRepository extends JpaRepository<vitacora, Integer> {

	@Query("SELECT v FROM vitacora v  ORDER BY v.Fecha")
	Page<vitacora> buscarTodosOrdernadoFecha(Pageable page);

	@Query("SELECT v FROM vitacora v WHERE v.licencia = :licencia")
	Page<vitacora> buscarTodosOrdernadoFecha(Pageable page, @Param("licencia") String licencia);

	@Query("SELECT v FROM vitacora v WHERE v.Fecha = :fecha")
	Page<vitacora> buscarTodosFechaOrdernadoFecha(Pageable page, @Param("fecha") Date fecha);
	
	@Query("SELECT v FROM vitacora v WHERE v.Fecha BETWEEN :fecha1 AND :fecha2 ORDER BY v.Fecha")
	List<vitacora> buscarPorFecha(@Param("fecha1") Date fecha1,@Param("fecha2")Date fecha2);
	
	@Query("SELECT v FROM vitacora v WHERE v.licencia =:licencia and v.Fecha = :fecha")
	Page<vitacora> buscarTodosFechaYLicenciaOrdernadoFecha(Pageable page, @Param("fecha") Date fecha,@Param("licencia") String licencia);
	
	
	
	
}
