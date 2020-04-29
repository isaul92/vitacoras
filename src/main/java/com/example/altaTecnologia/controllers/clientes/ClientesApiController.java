package com.example.altaTecnologia.controllers.clientes;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.altaTecnologia.models.Clientes.clientes;
import com.example.altaTecnologia.models.Clientes.clientesApi;
import com.example.altaTecnologia.service.OrdenesService.Clientes.IClientes;

@RestController()
@RequestMapping("clientesApi")

public class ClientesApiController {
	@Autowired
	private IClientes serviceClientes;

	@GetMapping("/buscador")
	public List<clientesApi> buscarTodosPorNombreApi(@RequestParam("term") String nombre) {
		List<clientesApi> clientes = new LinkedList();
		List<clientes> c = serviceClientes.buscarTodosPorNombreApi(nombre);

		for (clientes cliente : c) {
			clientesApi clienteApi = new clientesApi();
			clienteApi.setId(cliente.getId());
			clienteApi.setNombre(cliente.getNombre());
			clienteApi.setApellidos(cliente.getApellidos());
			clienteApi.setCorreo(cliente.getCorreo());
			clienteApi.setTelefono(cliente.getTelefono());
			clientes.add(clienteApi);
		}

		return clientes;

	}
}
