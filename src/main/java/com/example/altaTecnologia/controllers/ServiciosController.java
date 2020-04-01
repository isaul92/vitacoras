package com.example.altaTecnologia.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.altaTecnologia.models.categoriasroca;
import com.example.altaTecnologia.models.subcategoriaroca;
import com.example.altaTecnologia.service.IServiceServiciosRocaCategorias;
import com.example.altaTecnologia.service.ISubCategoriasService;

@Controller
@RequestMapping("/servicios")
public class ServiciosController {

	@Autowired
	private ISubCategoriasService serviceSubCat;

	@Autowired
	private IServiceServiciosRocaCategorias serviceCat;

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int id, RedirectAttributes attributes) {

		serviceCat.eliminar(id);
		attributes.addFlashAttribute("msg", "Categoria Eliminada");

		return "redirect:/servicios/indexPaginate";
	}

	@GetMapping("/subCategoria/delete/{id}")
	public String eliminarSub(@PathVariable("id") int id) {
		serviceSubCat.eliminar(id);

		return "redirect:/servicios/subCategoria/indexPaginate";

	}

	@GetMapping("/subCategoria/edit/{id}")
	public String editarSubCat(@PathVariable("id") int id, Model model, subcategoriaroca subcategoriaroca) {
		List<categoriasroca> lista = serviceCat.buscarTodas();

		Optional op = serviceSubCat.buscarPorId(id);
		if (op.isPresent()) {

			model.addAttribute("subcategoriaroca", serviceSubCat.buscarPorId(id).get());
			model.addAttribute("categorias", lista);

			return "servicios/formSubCat";
		}
		return "redirect:/servicios/subCategoria/indexPaginate";

	}

	@GetMapping("/edit/{id}")
	public String editarCategoria(@PathVariable("id") int id, Model model) {
		Optional op = serviceCat.buscarPorId(id);
		if (op.isPresent()) {
			model.addAttribute("categoriasroca", serviceCat.buscarPorId(id).get());
			return "servicios/formCategoria";
		}

		return "redirect:/servicios/indexPaginate";

	}

	@GetMapping("/subCategoria/indexPaginate")
	public String listarSubCategorias(Model model, Pageable page) {

		Page<subcategoriaroca> lista = serviceSubCat.buscarTodas(page);
		model.addAttribute("subCategorias", lista);

		return "servicios/listSubCategoriasServicios";

	}

	@GetMapping("/subCategoria/nuevaSubCategoria")
	public String nuevaSubCat(Model model, subcategoriaroca subcategoriaroca) {
		List<categoriasroca> lista = serviceCat.buscarTodas();
		model.addAttribute("categorias", lista);
		return "servicios/formSubCat";
	}

	@GetMapping("/indexPaginate")
	public String listarCategorias(Model model, Pageable page) {

		Page<categoriasroca> lista = serviceCat.getAll(page);
		model.addAttribute("categorias", lista);

		return "servicios/listCategoriasServicios";

	}

	@PostMapping("/subCat/save")
	public String guardarSubCat(subcategoriaroca subcategoriaroca, BindingResult result,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("msg", "Sucedio un error");
			return "redirect:/servicios/subCategoria/nuevo";

		}
		serviceSubCat.guardar(subcategoriaroca);
		return "redirect:/servicios/subCategoria/indexPaginate";

	}

	@PostMapping("/save")
	public String guardarCategoria(categoriasroca categoriasroca, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {

			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error");
			}

			return "redirect:/servicios/nuevo";
		}
		serviceCat.guardar(categoriasroca);

		subcategoriaroca sub = new subcategoriaroca();
		sub.setNombre("Otro");
		sub.setIdCat(categoriasroca);
		serviceSubCat.guardar(sub);

		return "redirect:/servicios/indexPaginate";
	}

	@GetMapping("/nuevaCategoria")
	public String newCategory(Model model, categoriasroca categoriasroca) {

		return "servicios/formCategoria";
	}

	@GetMapping("/nuevo")
	public String create() {

		return "servicios/formServicio";
	}
}
