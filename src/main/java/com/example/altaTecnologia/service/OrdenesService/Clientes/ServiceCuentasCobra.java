package com.example.altaTecnologia.service.OrdenesService.Clientes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.altaTecnologia.models.Clientes.clientes;
import com.example.altaTecnologia.models.Clientes.cuentasxcobrar;
import com.example.altaTecnologia.repository.OrdenesReposity.Clientes.ServiceCuentasCobrar;
@Service
public class ServiceCuentasCobra implements ICuentasxCobrar {
@Autowired
private ServiceCuentasCobrar repoCuentas;

@Autowired
private ServicesAbonos repoAbonos;

@Override
public void guardar(cuentasxcobrar c) {
	// TODO Auto-generated method stub
	
}

@Override
public void cancelar(int c) {
	// TODO Auto-generated method stub
	
}

@Override
public List<cuentasxcobrar> buscarTodos() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Page<cuentasxcobrar> indexPaginate(Pageable page) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<cuentasxcobrar> buscarPorCliente(clientes c) {
	// TODO Auto-generated method stub
	return null;
}

}
