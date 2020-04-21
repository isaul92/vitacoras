package com.example.altaTecnologia.repository.RocaService;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.altaTecnologia.models.vitacorasRoca.categoriasroca;
import com.example.altaTecnologia.models.vitacorasRoca.reporte;
import com.example.altaTecnologia.models.vitacorasRoca.subcategoriaroca;

public interface ServiciosSubCategoria extends JpaRepository<subcategoriaroca, Integer> {

	List<subcategoriaroca> findByIdCat(categoriasroca categoriasroca);

	
	@Query("SELECT new com.example.altaTecnologia.models.vitacorasRoca.reporte(s.nombre,count(v.subCat)) FROM subcategoriaroca s LEFT JOIN vitacora v ON v.subCat=s.id WHERE v.Fecha BETWEEN ?1 and ?2 GROUP BY s.nombre ORDER BY v.Fecha")
	 List<reporte> numeroRoport(Date fecha1, Date fecha2 );
	
	
	/*
	@Query("select s,count(v.subCat) from subcategoriaroca s left join vitacora v on v.subCat=s.id where v.Fecha between ?1 and ?2 group by s.nombre")
	List<reporte> numeroRoport(Date fecha1, Date fecha2);


*/}