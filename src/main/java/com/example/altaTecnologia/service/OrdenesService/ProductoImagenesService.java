package com.example.altaTecnologia.service.OrdenesService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.altaTecnologia.models.ordenesDeServicio.imagenesProductos;
import com.example.altaTecnologia.repository.OrdenesReposity.ServiceImagenesProductRepo;
@Service
public class ProductoImagenesService implements IProductImagenes {
@Autowired
private ServiceImagenesProductRepo repoImagenes;

@Override
public void guardar(imagenesProductos imagenesProductos) {
	repoImagenes.save(imagenesProductos);
	
}

@Override
public void eliminar(int id) {
repoImagenes.eliminarTodas(id);
	
}

@Override
public List<imagenesProductos> buscarTodas() {
	// TODO Auto-generated method stub
	return repoImagenes.findAll();
}

@Override
public List<imagenesProductos> buscarPorProducto(int id) {
	// TODO Auto-generated method stub
	return repoImagenes.buscarPorProducto(id);
}

@Override
public void eliminarPorId(int id) {
repoImagenes.deleteById(id);
	
}

@Override
public Optional<imagenesProductos> buscarPorId(int id) {
	// TODO Auto-generated method stub
	return repoImagenes.findById(id);
}

	
}
