package com.example.altaTecnologia.service.RocaService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.altaTecnologia.models.vitacorasRoca.categoriasroca;
import com.example.altaTecnologia.models.vitacorasRoca.reporte;
import com.example.altaTecnologia.models.vitacorasRoca.subcategoriaroca;

public interface ISubCategoriasService {
	List<subcategoriaroca> buscarTodas() ;
	Page<subcategoriaroca> buscarTodas(Pageable page) ;
	Optional <subcategoriaroca> buscarPorId(int id);
	void eliminar(int id);
	void guardar(subcategoriaroca subcategoriaroca);
	List<subcategoriaroca>  buscarPorCategoria(categoriasroca categoriasroca);
	List<reporte>  reporte(Date fecha1,Date fecha2);
	
}
