package com.example.altaTecnologia.service.RocaService;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.altaTecnologia.models.vitacorasRoca.categoriasroca;
import com.example.altaTecnologia.repository.RocaService.ServiciosRocaCategoriasRepository;
@Service
public class ServicesCategoriasRocaImp implements IServiceServiciosRocaCategorias {

	
	
	
	
	@Autowired
	private ServiciosRocaCategoriasRepository repoCategorias;
	
	
	@Override
	public void guardar(categoriasroca categoriasroca) {
		repoCategorias.save(categoriasroca);
		
	}


	@Override
	public Page<categoriasroca> getAll(Pageable page) {
		// TODO Auto-generated method stub
		return repoCategorias.findAll(page);

	}


	@Override
	public void eliminar(int id) {
		repoCategorias.deleteById(id);
		
	}


	@Override
	public Optional < categoriasroca>  buscarPorId(int id) {
		// TODO Auto-generated method stub
		return repoCategorias.findById(id);
	}


	@Override
	public List<categoriasroca> buscarTodas() {
		// TODO Auto-generated method stub
		return repoCategorias.findAll();
	}

}
