package com.example.altaTecnologia.service.OrdenesService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.altaTecnologia.models.ordenesDeServicio.categoriasProductos;
import com.example.altaTecnologia.repository.OrdenesReposity.ServicecategoriasProductos;

@Service
public class CategoriaProductosService implements ICategoriasProductos {

	@Autowired
	private ServicecategoriasProductos repoCategorias;

	@Override
	public void guardar(categoriasProductos categoriasProductos) {
		repoCategorias.save(categoriasProductos);
	}

	@Override
	public void eliminar(int id) {
		 Optional<categoriasProductos> cat=repoCategorias.buscarPorId(id);
		 if	(cat.isPresent()) {
			 categoriasProductos categoriasProductos =cat.get();
				categoriasProductos.setEstatus(0);
				repoCategorias.save(categoriasProductos);
		 }
		

	}

	@Override
	public List<categoriasProductos> buscarTodas() {
		// TODO Auto-generated method stub
		return repoCategorias.buscarTodas();
	}

	@Override
	public Page<categoriasProductos> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return repoCategorias.buscarTodos(page);
	}

	@Override
	public Optional<categoriasProductos> buscarPorId(int id) {
		// TODO Auto-generated method stub
		return repoCategorias.buscarPorId(id);
	}

}
