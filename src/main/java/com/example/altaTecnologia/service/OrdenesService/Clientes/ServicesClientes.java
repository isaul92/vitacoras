package com.example.altaTecnologia.service.OrdenesService.Clientes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.altaTecnologia.models.Clientes.clientes;
import com.example.altaTecnologia.models.Clientes.datosfiscales;
import com.example.altaTecnologia.models.Clientes.requisitosfiscales;
import com.example.altaTecnologia.repository.OrdenesReposity.Clientes.IRequistosFiscales;
import com.example.altaTecnologia.repository.OrdenesReposity.Clientes.ServicesClientesRepo;

@Service
public class ServicesClientes implements IClientes {

	@Autowired
	private ServicesClientesRepo repoClientes;
	@Autowired
	private IRequistosFiscales serviceRepo;
	@Autowired
	private IDatosFiscales serviceDatos;

	@Override
	public void guardar(clientes clientes) {
		repoClientes.save(clientes);

	}

	@Override

	public void eliminar(int id) {
		clientes c = repoClientes.buscarPorId(id).get();
		c.setEstatusElim(0);
		repoClientes.save(c);

		List<datosfiscales> lista = c.getIdDatosFiscales();
		for (datosfiscales d : lista) {
			List<requisitosfiscales> lis = d.getIdFiscal();
			for (requisitosfiscales l : lis) {
				serviceRepo.eliminar(l.getId(), l);
			}
			serviceDatos.eliminar(d.getId());
		}

	}

	@Override
	public List<clientes> buscarTodos() {
		// TODO Auto-generated method stub
		return repoClientes.buscarTodos();
	}

	@Override
	public Optional<clientes> buscarPorId(int id) {
		// TODO Auto-generated method stub
		return repoClientes.buscarPorId(id);
	}

	@Override
	public Page<clientes> indexPaginate(Pageable page) {
		// TODO Auto-generated method stub
		return repoClientes.buscarTodos(page);
	}

	@Override
	public Page<clientes> indexPaginatePorNombre(Pageable page, String nombre) {
		// TODO Auto-generated method stub
		return repoClientes.buscarTodosPorNombre(page, nombre);
	}

	@Override
	public List<clientes> buscarPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return repoClientes.buscarTodosPorNombre(nombre);
	}

	@Override
	public List<clientes> buscarTodosPorNombreApi(String nombre) {
		// TODO Auto-generated method stub
		return repoClientes.buscarTodosPorNombreApi(nombre);
	}

}
