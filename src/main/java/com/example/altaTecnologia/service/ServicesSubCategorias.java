package com.example.altaTecnologia.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.altaTecnologia.models.categoriasroca;
import com.example.altaTecnologia.models.reporte;
import com.example.altaTecnologia.models.subcategoriaroca;
import com.example.altaTecnologia.repository.IServiceServiciosRoca.ServiciosSubCategoria;

@Service
public class ServicesSubCategorias implements ISubCategoriasService {
	@Autowired
	ServiciosSubCategoria repoSub;

	@Override
	public Page<subcategoriaroca> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return repoSub.findAll(page);
	}

	@Override
	public Optional<subcategoriaroca> buscarPorId(int id) {
		// TODO Auto-generated method stub
		return repoSub.findById(id);
	}

	@Override
	public void eliminar(int id) {
		repoSub.deleteById(id);

	}

	@Override
	public void guardar(subcategoriaroca subcategoriaroca) {
		repoSub.save(subcategoriaroca);

	}

	@Override
	public List<subcategoriaroca> buscarTodas() {
		// TODO Auto-generated method stub
		return repoSub.findAll();
	}

	@Override
	public List<subcategoriaroca> buscarPorCategoria(categoriasroca categoriasroca) {
		// TODO Auto-generated method stub
		return repoSub.findByIdCat(categoriasroca);
	}

	@Override
	public List<reporte> reporte(Date fecha1, Date fecha2) {
		
		return repoSub.numeroRoport(fecha1, fecha2);
	}

}
