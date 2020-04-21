package com.example.altaTecnologia.service.OrdenesService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.altaTecnologia.models.Clientes.clientes;
import com.example.altaTecnologia.models.ordenesDeServicio.ordenservicio;
import com.example.altaTecnologia.repository.OrdenesReposity.ServiceOrdenesServicioRepo;

@Service
public class OrdenesServices implements IOrdenesServicio {

	@Autowired
	private ServiceOrdenesServicioRepo repoOrdenes;

	@Override
	public void guardar(ordenservicio ordenservicio) {
		repoOrdenes.save(ordenservicio);
	}

	@Override
	public List<ordenservicio> buscarTodas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ordenservicio> buscarPorCliente(clientes cliente) {
		// TODO Auto-generated method stub
		return repoOrdenes.buscarPorCliente(cliente);
	}

	@Override
	public Optional<ordenservicio> buscarPorId(int id) {
		// TODO Auto-generated method stub
		return repoOrdenes.findById(id);
	}

	@Override
	public void eliminiar(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<ordenservicio> indexPaginate(Pageable page) {
		// TODO Auto-generated method stub
		return repoOrdenes.findAll(page);
	}

	@Override
	public Page<ordenservicio> indexPaginates(Pageable page, clientes cliente) {
		// TODO Auto-generated method stub
		return repoOrdenes.buscarPorClientesIndex(page, cliente);
	}

	@Override
	public Page<ordenservicio> indexPaginates(Pageable page, clientes cliente, Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return repoOrdenes.buscarPorClientesIndex(page, cliente, fecha1, fecha2);
	}

	@Override
	public List<ordenservicio> buscarTodasClienteFechas(clientes cliente, Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return repoOrdenes.buscarPorClientesIndex(cliente, fecha1, fecha2);
	}

	@Override
	public Page<ordenservicio> indexPaginate(Pageable page, int folio) {
		// TODO Auto-generated method stub
		return repoOrdenes.buscarPorId(page, folio);
	}

	@Override
	public Page<ordenservicio> indexPaginate(Pageable page, String nombre) {
		// TODO Auto-generated method stub
		return repoOrdenes.buscarPorNombre(page, nombre);
	}

	@Override
	public Page<ordenservicio> indexPaginate(Pageable page, Date vigencia) {
		// TODO Auto-generated method stub
		return repoOrdenes.buscarPorFecha(page, vigencia);
	}

}
