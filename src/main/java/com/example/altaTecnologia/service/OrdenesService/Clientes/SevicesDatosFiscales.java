package com.example.altaTecnologia.service.OrdenesService.Clientes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.altaTecnologia.models.Clientes.datosfiscales;
import com.example.altaTecnologia.repository.OrdenesReposity.Clientes.ServiceDatosFiscalesRepo;

@Service
public class SevicesDatosFiscales implements IDatosFiscales {

	@Autowired
	private ServiceDatosFiscalesRepo repoDatos;

	@Override
	public void guardar(datosfiscales datosfiscales) {
		repoDatos.save(datosfiscales);

	}

	@Override
	public void eliminar(int id) {
		repoDatos.deleteById(id);
	}

	@Override
	public Page<datosfiscales> indexPaginate(Pageable page) {
		// TODO Auto-generated method stub
		return repoDatos.findAll(page);
	}

	@Override
	public List<datosfiscales> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<datosfiscales> indexPaginate(Pageable page, String nombre) {
		// TODO Auto-generated method stub
		return repoDatos.buscarTodosPorRFC(page, nombre);
	}

	@Override
	public datosfiscales buscarPorId(int id) {
		// TODO Auto-generated method stub
		return repoDatos.findById(id).get();
	}

	@Override
	public List<datosfiscales> buscarPorIdCliente(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<datosfiscales> buscarPorIdO(int id) {
		// TODO Auto-generated method stub
		return repoDatos.findById(id);
	}

}
