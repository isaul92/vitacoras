package com.example.altaTecnologia.service.OrdenesService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.altaTecnologia.models.ordenesDeServicio.unidadmedida;
import com.example.altaTecnologia.repository.OrdenesReposity.ServiceMedidasRepo;
@Service
public class MedidasServices implements IMedidasService {

	@Autowired
	private ServiceMedidasRepo repoMedidas;
	
	
	@Override
	public Page<unidadmedida> BuscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return repoMedidas.buscarTodas(page);
	}


	@Override
	public void eliminar(int id) {
		unidadmedida unidadmedida=repoMedidas.findById(id).get();
		unidadmedida.setEstatus(0);
		repoMedidas.save(unidadmedida);
		
		
		
	}


	@Override
	public void guardar(unidadmedida unidadmedida) {
	repoMedidas.save(unidadmedida);
		
	}


	@Override
	public Optional <unidadmedida>buscarPorId(int id) {
		// TODO Auto-generated method stub
		return repoMedidas.buscarPorId(id);
	}


	@Override
	public List<unidadmedida> buscarTodas() {
		// TODO Auto-generated method stub
		return repoMedidas.buscarTodas();
	}

	
	
	
}
