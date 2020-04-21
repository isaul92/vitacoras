package com.example.altaTecnologia.controllers.ordenesServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.altaTecnologia.models.ordenesDeServicio.unidadmedida;
import com.example.altaTecnologia.service.OrdenesService.IMedidasService;

@Controller
@RequestMapping("/medidas")
public class MedidasController {
	@Autowired
	private IMedidasService serviceMedidas;

	@RequestMapping("/create")
	public String crear(Model model, unidadmedida unidadmedida) {

		return "ordenes/listMedidas/formMedidas";
	}

	@RequestMapping("/indexPaginate")
	public String index(Model model, Pageable page) {
		model.addAttribute("medidas", serviceMedidas.BuscarTodas(page));

		return "ordenes/listMedidas/listMedidas";
	}
	
	@RequestMapping("/edit/{id}")
	public String editat(@PathVariable("id") int id,unidadmedida unidadmedida, BindingResult result,Model model,
			RedirectAttributes attributes) {
		
		model.addAttribute("unidadmedida",serviceMedidas.buscarPorId(id).get());
		return  "ordenes/listMedidas/formMedidas";
	}
	
	@RequestMapping("/delete/{id}")
	public String eliminar(@PathVariable ("id") int id, RedirectAttributes attributes) {
		serviceMedidas.eliminar(id);
		return "redirect:/medidas/indexPaginate";
	}

	@RequestMapping("/save")
	public String guardar(unidadmedida unidadmedida, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return "redirect:/medidas/create";
		}
		unidadmedida.setEstatus(1);
		serviceMedidas.guardar(unidadmedida);
		return "redirect:/medidas/indexPaginate";
	}

}
