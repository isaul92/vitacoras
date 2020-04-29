package com.example.altaTecnologia.controllers.ordenesServicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.altaTecnologia.models.ordenesDeServicio.serviceprod;
import com.example.altaTecnologia.service.OrdenesService.IProductServicios;

@RestController
@RequestMapping("productosApi")
public class ProductosApiController {
	@Autowired
	private IProductServicios serviceProducts;

	@GetMapping("/buscador")
	public List<serviceprod> buscadorProductos(@RequestParam("term") String nombre) {

		List<serviceprod> productos = serviceProducts.buscarPorNombre(nombre);
		return productos;
	}

}
