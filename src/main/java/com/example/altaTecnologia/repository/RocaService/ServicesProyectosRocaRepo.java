package com.example.altaTecnologia.repository.RocaService;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.altaTecnologia.models.vitacorasRoca.proyectosRoca;
import com.example.altaTecnologia.models.vitacorasRoca.reporte;

public interface ServicesProyectosRocaRepo  extends JpaRepository<proyectosRoca, Integer> {
	@Query("select  s.nombre, count(v.subCat) as numeroReportes from subcategoriaroca s left join vitacora v on v.subCat =s.id group by s.id")
	List<reporte> getReport();
}
