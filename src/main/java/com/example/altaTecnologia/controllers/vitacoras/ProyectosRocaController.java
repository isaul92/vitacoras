package com.example.altaTecnologia.controllers.vitacoras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.altaTecnologia.models.vitacorasRoca.proyectosRoca;
import com.example.altaTecnologia.service.RocaService.IproyectosRoca;

@Controller
@RequestMapping("/proyectoRoca")
public class ProyectosRocaController {

	@Autowired
	private IproyectosRoca servicePRoca;

	@GetMapping("/indexPaginate")
	public String index(Model model, Pageable page) {

		model.addAttribute("proyectos", servicePRoca.getAll(page));
		return "servicios/proyectosRoca/listProyectos";

	}

	
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable  ("id") int id ,Model model) {
		servicePRoca.eliminar(id);
		return "redirect:/proyectoRoca/indexPaginate";
		
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable  ("id") int id ,Model model) {
		model.addAttribute("proyectosRoca",servicePRoca.buscarPorId(id));
		return "servicios/proyectosRoca/formProyectos";
		
	}
	
	@PostMapping("/save")
	public String guardar(proyectosRoca proyectosRoca, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {

			return "redirect:/servicios/nuevo";
		}

		servicePRoca.guardar(proyectosRoca);
		return "redirect:/proyectoRoca/indexPaginate";
	}

	@GetMapping("/nuevoProyecto")
	public String crear(Model model, proyectosRoca proyectosRoca) {

		return "servicios/proyectosRoca/formProyectos";
	}

}
