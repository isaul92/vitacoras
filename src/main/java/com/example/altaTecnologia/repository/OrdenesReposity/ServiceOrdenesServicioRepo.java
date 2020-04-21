package com.example.altaTecnologia.repository.OrdenesReposity;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.altaTecnologia.models.Clientes.clientes;
import com.example.altaTecnologia.models.ordenesDeServicio.ordenservicio;

public interface ServiceOrdenesServicioRepo extends JpaRepository<ordenservicio, Integer> {

	@Query("SELECT o,c FROM  ordenservicio o INNER JOIN  clientes c on c.id=o.clienteId WHERE o.clienteId=:id")
	List<ordenservicio> buscarPorCliente(@Param("id") clientes id);

	@Query("SELECT o FROM  ordenservicio o  WHERE o.clienteId.nombre LIKE %:nombre%")
	Page<ordenservicio> buscarPorNombre(Pageable page, @Param("nombre") String nombre);

	@Query("SELECT o FROM  ordenservicio o  WHERE o.vigencia=:vigencia")
	Page<ordenservicio> buscarPorFecha(Pageable page, @Param("vigencia") Date vigencia);

	@Query("SELECT o FROM  ordenservicio o WHERE o.id=:id")
	Page<ordenservicio> buscarPorId(Pageable page, @Param("id") int id);

	@Query("SELECT o FROM  ordenservicio o INNER JOIN  clientes c on c.id=o.clienteId WHERE o.clienteId=:id")
	Page<ordenservicio> buscarPorClientesIndex(Pageable page, @Param("id") clientes id);

	@Query("SELECT o FROM  ordenservicio o INNER JOIN  clientes c on c.id=o.clienteId WHERE o.clienteId=:id AND o.fecha BETWEEN :fecha1 AND :fecha2")
	Page<ordenservicio> buscarPorClientesIndex(Pageable page, @Param("id") clientes id, @Param("fecha1") Date fecha1,
			@Param("fecha2") Date fecha2);

	@Query("SELECT o FROM  ordenservicio o INNER JOIN  clientes c on c.id=o.clienteId WHERE o.clienteId=:id AND o.fecha BETWEEN :fecha1 AND :fecha2")
	List<ordenservicio> buscarPorClientesIndex(@Param("id") clientes id, @Param("fecha1") Date fecha1,
			@Param("fecha2") Date fecha2);
}
