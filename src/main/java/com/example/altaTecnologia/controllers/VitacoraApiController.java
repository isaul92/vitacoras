package com.example.altaTecnologia.controllers;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.altaTecnologia.models.categoriasroca;
import com.example.altaTecnologia.models.reporte;
import com.example.altaTecnologia.models.subcategoriaroca;
import com.example.altaTecnologia.service.IServiceServiciosRocaCategorias;
import com.example.altaTecnologia.service.ISubCategoriasService;
import com.example.altaTecnologia.service.IVitacoraService;
import com.example.altaTecnologia.utils.ReportsGenerated;

@RestController
@RequestMapping("/apiVitacora")
public class VitacoraApiController {

	@Autowired
	private ISubCategoriasService serviceSubCat;

	@Autowired
	private IServiceServiciosRocaCategorias serviceCat;

	@Autowired
	private IVitacoraService serviceVita;

	@PostMapping("/reporte/{fecha1}/{fecha2}")
	public List<reporte> reportes(@PathVariable Date fecha1,@PathVariable Date fecha2) {
		System.out.println("JEJE");
		List<reporte> li = serviceSubCat.reporte(fecha1,fecha2);

		return li;

	}

	@PostMapping("/subCategoria/{id}")
	public List<subcategoriaroca> buscarPorCategoria(@PathVariable int id) {

		categoriasroca v = new categoriasroca();

		v = serviceCat.buscarPorId(id).get();
		List<subcategoriaroca> lista = serviceSubCat.buscarPorCategoria(v);

		return lista;

	}
	
	@GetMapping("/reportes/{fecha1}/{fecha2}")
	public ResponseEntity<InputStreamResource> reportesPorFecha(@PathVariable("fecha1") Date fecha1,@PathVariable ("fecha2")Date fecha2) throws Exception {
		ByteArrayInputStream stream = ReportsGenerated.getAll(serviceVita.buscarPorFechaReporte(fecha1, fecha2));
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition","attachment; filename=reporte.xls");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));

	}
	
	

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}
