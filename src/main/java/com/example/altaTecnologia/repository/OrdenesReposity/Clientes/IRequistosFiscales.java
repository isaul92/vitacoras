package com.example.altaTecnologia.repository.OrdenesReposity.Clientes;

import java.util.List;
import java.util.Optional;

import com.example.altaTecnologia.models.Clientes.requisitosfiscales;

public interface IRequistosFiscales {
	void guardar(requisitosfiscales re);

	 void eliminar(int id,requisitosfiscales f);
	List<requisitosfiscales> buscarTodos();

	Optional<requisitosfiscales> buscarPorId(int id);
}
