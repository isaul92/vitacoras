package com.example.altaTecnologia.controllers.clientes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.altaTecnologia.models.Clientes.clientes;
import com.example.altaTecnologia.models.Clientes.datosfiscales;
import com.example.altaTecnologia.service.OrdenesService.Clientes.IClientes;

@Controller
@RequestMapping("/clientes")
public class ClientesController {
	@Autowired
	private IClientes serviceClientes;

	@Value("${paginacion.numeroDePaginas}")
	private int numeroPaginacion;

	@RequestMapping("/create")
	public String crear(Model model, clientes cliente, BindingResult result) {

		return "clientes/formClientes";

	}

	@RequestMapping("/delete/{id}")
	public String eliminar(@ModelAttribute clientes clientes, BindingResult result, RedirectAttributes attributes,
			@PathVariable("id") int id) {

		serviceClientes.eliminar(id);
		attributes.addFlashAttribute("msg", "Cliente eliminado!");
		return "redirect:/clientes/indexPaginate/0";
	}

	@RequestMapping("/edit/{id}")
	public String editar(@ModelAttribute clientes clientes, BindingResult result, RedirectAttributes attributes,
			@PathVariable("id") int id, Model model) {
		System.out.println("zd");
		Optional<clientes> cliente = serviceClientes.buscarPorId(id);
		if (cliente.isPresent()) {
			model.addAttribute("clientes", cliente.get());
			clientes sc = cliente.get();
			for (datosfiscales d : sc.getDatosfiscales()) {
				System.out.println("assaas"+d.getCiudad());
			}
			return "clientes/formClientes";
		} else {
			return "redirect:/clientes/indexPaginate/0";
		}

	}

	@RequestMapping("/save")
	public String guardar(clientes clientes, Model model, RedirectAttributes attributes, BindingResult result) {
		if (result.hasErrors()) {
			return "clientes/formClientes";
		}
		clientes.setEstatusElim(1);
		serviceClientes.guardar(clientes);

		return "redirect:/clientes/indexPaginate/0";
	}

	@RequestMapping("/indexPaginate/{page}/{nombre}")
	public String indexPaginateNombre(@PathVariable("page") Optional<Integer> pagina, Pageable page, Model model,
			@PathVariable("nombre") String nombre) {
		model.addAttribute("nombre", nombre);
		if (pagina.isPresent() && pagina.get() >= 0) {
			page = PageRequest.of(pagina.get(), numeroPaginacion);
			model.addAttribute("clientes", serviceClientes.indexPaginatePorNombre(page, nombre));
			return "clientes/listClientes";
		} else {
			model.addAttribute("clientes", serviceClientes.indexPaginatePorNombre(page, nombre));
			page = PageRequest.of(0, numeroPaginacion);

			return "clientes/listClientes";
		}

	}

	@RequestMapping("/indexPaginate/{page}")
	public String indexPaginate(@PathVariable("page") Optional<Integer> pagina, Pageable page, Model model) {

		if (pagina.isPresent() && pagina.get() >= 0) {
			page = PageRequest.of(pagina.get(), numeroPaginacion);
			model.addAttribute("clientes", serviceClientes.indexPaginate(page));
			return "clientes/listClientes";
		} else {
			model.addAttribute("clientes", serviceClientes.indexPaginate(page));
			page = PageRequest.of(0, numeroPaginacion);
			model.addAttribute("clientes", serviceClientes.indexPaginate(page));
			return "clientes/listClientes";
		}

	}

}
