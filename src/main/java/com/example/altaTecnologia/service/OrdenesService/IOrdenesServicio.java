package com.example.altaTecnologia.service.OrdenesService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.altaTecnologia.models.Clientes.clientes;
import com.example.altaTecnologia.models.ordenesDeServicio.ordenservicio;

public interface IOrdenesServicio {
	void guardar(ordenservicio ordenservicio);

	List<ordenservicio> buscarTodas();

	List<ordenservicio> buscarPorCliente(clientes cliente);

	Optional<ordenservicio> buscarPorId(int id);

	void eliminiar(int id);

	Page<ordenservicio> indexPaginate(Pageable page);

	Page<ordenservicio> indexPaginate(Pageable page, Date nombre);

	Page<ordenservicio> indexPaginate(Pageable page, String nombre);

	Page<ordenservicio> indexPaginate(Pageable page, int folio);

	Page<ordenservicio> indexPaginates(Pageable page, clientes cliente);

	List<ordenservicio> buscarTodasClienteFechas(clientes cliente, Date fecha1, Date fecha2);

	Page<ordenservicio> indexPaginates(Pageable page, clientes cliente, Date fecha1, Date fecha2);
}
