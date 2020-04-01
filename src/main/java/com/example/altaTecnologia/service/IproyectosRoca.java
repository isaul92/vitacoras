package com.example.altaTecnologia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.altaTecnologia.models.proyectosRoca;

public interface IproyectosRoca {

	void eliminar(int id);

	void guardar(proyectosRoca r);

	List<proyectosRoca> buscarTodos();


	Page<proyectosRoca> getAll(Pageable page);

	Optional <proyectosRoca>buscarPorId(int id);

}
