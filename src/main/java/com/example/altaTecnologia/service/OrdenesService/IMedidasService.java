package com.example.altaTecnologia.service.OrdenesService;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.altaTecnologia.models.ordenesDeServicio.unidadmedida;

public interface IMedidasService {

	Page<unidadmedida> BuscarTodas(Pageable page);
	void eliminar(int id);
	Optional <unidadmedida>buscarPorId(int id);
	void guardar(unidadmedida unidadmedida);
	List<unidadmedida> buscarTodas();

}
