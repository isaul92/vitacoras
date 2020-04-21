package com.example.altaTecnologia.service.OrdenesService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.altaTecnologia.models.ordenesDeServicio.serviceprod;
import com.example.altaTecnologia.repository.OrdenesReposity.ServiceProdServ;

@Service
public class ProductServiciosService implements IProductServicios {
	@Autowired
	private ServiceProdServ repoProducSer;

	@Override
	public List<serviceprod> buscarTodas() {
		// TODO Auto-generated method stub
		return repoProducSer.mostrarTodas();
	}

	@Override
	public Page<serviceprod> indexTodas(Pageable page) {
		// TODO Auto-generated method stub
		return repoProducSer.indexPaginate(page);
	}

	@Override
	public Optional<serviceprod> buscarPorId(int id) {
		// TODO Auto-generated method stub
		return repoProducSer.buscarPorId(id);
	}

	@Override
	public void eliminar(serviceprod serviceprod) {
		serviceprod.setEstatusEliminada(0);
		repoProducSer.save(serviceprod);

	}

	@Override
	public void guardar(serviceprod serviceprod) {
		repoProducSer.save(serviceprod);

	}

	@Override
	public Page<serviceprod> buscarTodosPoriD(Pageable page, int id) {
		// TODO Auto-generated method stub
		return repoProducSer.indexPaginateId(page, id);
	}

	@Override
	public Page<serviceprod> buscarPorNombre(Pageable page, String nombre) {
		// TODO Auto-generated method stub
		return repoProducSer.indexPaginateNombre(page, nombre);
	}

	@Override
	public Page<serviceprod> buscarPorDescripcion(Pageable page, String descr) {
		// TODO Auto-generated method stub
		return repoProducSer.indexPaginateDescrip(page, descr);
	}

}
