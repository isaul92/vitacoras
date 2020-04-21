package com.example.altaTecnologia.controllers.ordenesServicio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.altaTecnologia.models.ordenesDeServicio.ordenservicio;
import com.example.altaTecnologia.service.OrdenesService.IOrdenesServicio;

@Controller
@RequestMapping("/ordenes")
public class OrdenesController {

	@Value("${paginacion.numeroDePaginas}")
	private int numeroPaginacio;

	@Autowired
	private IOrdenesServicio serviceOrdenes;

	@RequestMapping("/crear")
	public String crear(Model model,ordenservicio ordenservicio, BindingResult result) {
		return "ordenes/ordenes/formOrden";
	}
	
	
	
	@RequestMapping("/indexPaginate/{page}/fecha/{fecha}")
	public String indexPaginateFecha(Model model, @PathVariable("page") Optional<Integer> pagina, Pageable page,
			@PathVariable("fecha") Date fecha) {
		model.addAttribute("fecha", fecha);
		if (pagina.isPresent() && pagina.get() >= 0) {

			page = PageRequest.of(pagina.get(), numeroPaginacio, Sort.by(Sort.Direction.DESC, "Fecha"));
			model.addAttribute("ordenes", serviceOrdenes.indexPaginate(page, fecha));

		} else {
			page = PageRequest.of(0, numeroPaginacio);
			model.addAttribute("ordenes", serviceOrdenes.indexPaginate(page, fecha));

		}

		return "/ordenes/ordenes/listOrdenes";

	}

	@RequestMapping("/indexPaginate/{page}/nombre/{nombre}")
	public String indexPaginateNombre(Model model, @PathVariable("page") Optional<Integer> pagina, Pageable page,
			@PathVariable("nombre") String nombre) {
		model.addAttribute("nombre", nombre);
		if (pagina.isPresent() && pagina.get() >= 0) {

			page = PageRequest.of(pagina.get(), numeroPaginacio);
			model.addAttribute("ordenes", serviceOrdenes.indexPaginate(page, nombre));

		} else {
			page = PageRequest.of(0, numeroPaginacio);
			model.addAttribute("ordenes", serviceOrdenes.indexPaginate(page, nombre));

		}

		return "/ordenes/ordenes/listOrdenes";

	}

	@RequestMapping("/indexPaginate/{page}/folio/{id}")
	public String indexPaginateFolio(Model model, @PathVariable("page") Optional<Integer> pagina, Pageable page,
			@PathVariable("id") int id) {
		model.addAttribute("folio", id);
		if (pagina.isPresent() && pagina.get() >= 0) {
			page = PageRequest.of(pagina.get(), numeroPaginacio);
			model.addAttribute("ordenes", serviceOrdenes.indexPaginate(page, id));
		} else {
			page = PageRequest.of(0, numeroPaginacio);
			model.addAttribute("ordenes", serviceOrdenes.indexPaginate(page, id));
		}
		return "/ordenes/ordenes/listOrdenes";
	}
	
	
	

	@RequestMapping("/indexPaginate/{page}")
	public String indexPaginate(Model model, @PathVariable("page") Optional<Integer> pagina, Pageable page) {

		if (pagina.isPresent() && pagina.get() >= 0) {
			page = PageRequest.of(pagina.get(), numeroPaginacio);
			model.addAttribute("ordenes", serviceOrdenes.indexPaginate(page));

		} else {
			page = PageRequest.of(0, numeroPaginacio);
			model.addAttribute("ordenes", serviceOrdenes.indexPaginate(page));
		}

		return "/ordenes/ordenes/listOrdenes";
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}
