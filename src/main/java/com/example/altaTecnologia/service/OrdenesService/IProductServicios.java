package com.example.altaTecnologia.service.OrdenesService;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.altaTecnologia.models.ordenesDeServicio.serviceprod;

public interface IProductServicios {

	List<serviceprod> buscarTodas();

	Page<serviceprod> indexTodas(Pageable page);

	Optional<serviceprod> buscarPorId(int id);

	void eliminar(serviceprod serviceprod);

	void guardar(serviceprod serviceprod);

	Page<serviceprod> buscarTodosPoriD(Pageable page,int id);

	Page<serviceprod> buscarPorNombre(Pageable page, String nombre);

	Page<serviceprod> buscarPorDescripcion(Pageable page,String desc);
}
