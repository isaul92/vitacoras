package com.example.altaTecnologia.repository.IServiceServiciosRoca;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.altaTecnologia.models.proyectosRoca;
import com.example.altaTecnologia.models.reporte;

public interface ServicesProyectosRocaRepo  extends JpaRepository<proyectosRoca, Integer> {
	@Query("select  s.nombre, count(v.subCat) as numeroReportes from subcategoriaroca s left join vitacora v on v.subCat =s.id group by s.id")
	List<reporte> getReport();
}
