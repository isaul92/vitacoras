package com.example.altaTecnologia.service.OrdenesService.Clientes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.altaTecnologia.models.Clientes.abonos;
import com.example.altaTecnologia.models.ordenesDeServicio.ordenservicio;
import com.example.altaTecnologia.repository.OrdenesReposity.ServiceOrdenesServicioRepo;
import com.example.altaTecnologia.repository.OrdenesReposity.Clientes.ServiceAbonosRepo;
import com.example.altaTecnologia.repository.OrdenesReposity.Clientes.ServiceCuentasCobrar;

@Service
public class ServicesAbonos implements IAbonos {
	@Autowired
	private ServiceCuentasCobrar repoCuentas;

	@Autowired
	private ServiceAbonosRepo repoAbonos;
	@Autowired
	private ServiceOrdenesServicioRepo repoOrdenes;

	@Override
	public void guardar(abonos abono) {
		repoAbonos.save(abono);

	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<abonos> buscarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<abonos> buscarTodos(ordenservicio orden) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<abonos> buscarTodos(Pageable page, abonos abono) {
		// TODO Auto-generated method stub
		return null;
	}

}
