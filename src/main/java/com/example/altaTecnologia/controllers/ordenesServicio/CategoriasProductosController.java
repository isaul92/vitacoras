package com.example.altaTecnologia.controllers.ordenesServicio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.altaTecnologia.models.ordenesDeServicio.categoriasProductos;

import com.example.altaTecnologia.service.OrdenesService.ICategoriasProductos;

@Controller
@RequestMapping("/categoriasProductos")
public class CategoriasProductosController {
	@Value("${paginacion.numeroDePaginas}")
	private int numeroPaginacion;
	@Autowired
	private ICategoriasProductos serviceCat;

	@RequestMapping("/edit/{id}")
	public String editar(@PathVariable("id") int id, Model model,
			@ModelAttribute categoriasProductos categoriasProductos, BindingResult result,
			RedirectAttributes attributes) {
		Optional<categoriasProductos> cat = serviceCat.buscarPorId(id);

		if (cat.isPresent()) {
			return "ordenes/serviciosProductos/FormCategorias";
		} else {

			attributes.addFlashAttribute("msj", "No existela categoria");

			return "redirect:/categoriasProductos/indexPaginate/0";
		}

	}

	@RequestMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int id, RedirectAttributes attributes) {
		serviceCat.eliminar(id);

		return "redirect:/categoriasProductos/indexPaginate/0";
	}

	@RequestMapping("/save")
	public String guardar(categoriasProductos categoriasProductos, BindingResult result,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {

			return "ordenes/serviciosProductos/formCategorias";

		}
		categoriasProductos.setEstatus(1);
		serviceCat.guardar(categoriasProductos);

		attributes.addFlashAttribute("msj", "Categoria creada con exito");

		return "redirect:/categoriasProductos/indexPaginate/0";
	}

	@RequestMapping("/nuevaCategoria")
	public String crear(Model model, categoriasProductos categoriasProductos) {
		return "ordenes/serviciosProductos/FormCategorias";
	}

	@RequestMapping("/indexPaginate/{page}")
	public String index(Model model, Pageable page, @PathVariable("page") Optional<Integer> pagina) {
		if (pagina.isPresent() && pagina.get() > 0) {
			page = PageRequest.of(pagina.get(), numeroPaginacion);
			Page<categoriasProductos> lista = serviceCat.buscarTodas(page);
			model.addAttribute("categorias", lista);
			return "ordenes/serviciosProductos/listCategorias";
		} else {
			page = PageRequest.of(0, numeroPaginacion);
			Page<categoriasProductos> lista = serviceCat.buscarTodas(page);
			model.addAttribute("categorias", lista);
			return "ordenes/serviciosProductos/listCategorias";
		}

	}

}
