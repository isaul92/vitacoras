package com.example.altaTecnologia.controllers.clientes;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.altaTecnologia.models.Clientes.clientes;
import com.example.altaTecnologia.models.Clientes.datosfiscales;
import com.example.altaTecnologia.models.Clientes.requisitosfiscales;
import com.example.altaTecnologia.repository.OrdenesReposity.Clientes.IRequistosFiscales;
import com.example.altaTecnologia.service.OrdenesService.Clientes.IClientes;
import com.example.altaTecnologia.service.OrdenesService.Clientes.IDatosFiscales;

@Controller
@RequestMapping("/datosFiscales")
public class DatosFiscalesController {
	@Autowired
	private IClientes serviceClientes;
	@Autowired
	private IDatosFiscales serviceDatos;
	@Autowired
	private IRequistosFiscales serviceRequ;

	@Value("${paginacion.numeroDePaginas}")
	private int numeroPaginacion;

	@RequestMapping("/save")
	public String guardar(datosfiscales datosfiscales, RedirectAttributes attributes, BindingResult result,
			@RequestParam("idCliente") int idCliente, Model model) {
		if (result.hasErrors()) {
			return "/clientes/datosFiscales/formDatosFiscales";
		}
		clientes c = serviceClientes.buscarPorId(idCliente).get();
		datosfiscales.setIdCliente(c);
		serviceDatos.guardar(datosfiscales);
		attributes.addFlashAttribute("msg", "Informacion fiscal creada!");
		return "redirect:/datosFiscales/indexPaginate/0";
	}

	@RequestMapping("/edit/{id}/{idFiscal}")
	public String editarDatoFiscal(@PathVariable("idFiscal") int idFiscal, @PathVariable("id") int id,
			datosfiscales datosFiscal, BindingResult result, RedirectAttributes attributes, Model model,
			requisitosfiscales requisitosfiscales) {

		Optional<datosfiscales> d = serviceDatos.buscarPorIdO(id);
		Optional<requisitosfiscales> r = serviceRequ.buscarPorId(idFiscal);
		if (d.isPresent() && r.isPresent()) {
			model.addAttribute("requisitosfiscales", r.get());
			model.addAttribute("datosfiscales", d.get());
			model.addAttribute("cliente", d.get().getIdCliente());

			return "/clientes/datosFiscales/formDatosFiscales";

		}
		return "redirect:/datosFiscales/indexPaginate/0";

	}

	@RequestMapping("/edit/{id}")
	public String editar(@PathVariable("id") int id, @ModelAttribute datosfiscales datosFiscal, BindingResult result,
			RedirectAttributes attributes, Model model, @ModelAttribute requisitosfiscales requisitosfiscales) {

		Optional<datosfiscales> d = serviceDatos.buscarPorIdO(id);
		if (d.isPresent()) {

			model.addAttribute("datosfiscales", d.get());
			model.addAttribute("cliente", d.get().getIdCliente());

			return "/clientes/datosFiscales/formDatosFiscales";

		}
		return "redirect:/datosFiscales/indexPaginate/0";

	}

	@RequestMapping("/create/{idCliente}")
	public String crear(Model model, @ModelAttribute datosfiscales datosFiscal, BindingResult result,
			@PathVariable("idCliente") int id, RedirectAttributes attributes) {
		Optional<clientes> cliente = serviceClientes.buscarPorId(id);

		if (cliente.isPresent()) {

			model.addAttribute("cliente", cliente.get());
			datosFiscal.setIdCliente(cliente.get());
			model.addAttribute("datosFiscal", datosFiscal);

		} else {
			attributes.addFlashAttribute("msg", "No existe Cliente");
			return "redirect:/datosFiscales/indexPaginate/0";
		}
		return "/clientes/datosFiscales/formDatosFiscales";

	}

	@RequestMapping("/delete/{id}")
	public String eliminar(RedirectAttributes attributes, @PathVariable("id") int id) {

		serviceDatos.eliminar(id);
		attributes.addFlashAttribute("msg", "RFC Eliminado");
		return "redirect:/datosFiscales/indexPaginate/0";

	}

	@RequestMapping("/indexPaginate/{page}/{nombre}")
	public String indexPaginateRFC(Model model, Pageable page, @PathVariable("page") Optional<Integer> pagina,
			@PathVariable("nombre") String nombre) {
		model.addAttribute("nombre", nombre);
		if (pagina.isPresent() && pagina.get() >= 0) {
			page = PageRequest.of(pagina.get(), numeroPaginacion, Sort.by(Sort.Direction.ASC, "id"));
			model.addAttribute("datosFiscales", serviceDatos.indexPaginate(page, nombre));
		} else {
			page = PageRequest.of(0, numeroPaginacion, Sort.by(Sort.Direction.ASC, "id"));
			model.addAttribute("datosFiscales", serviceDatos.indexPaginate(page, nombre));
		}
		return "/clientes/datosFiscales/listDatosFiscales";
	}

	@RequestMapping("/indexPaginate/{page}")
	public String indexPaginate(Model model, Pageable page, @PathVariable("page") Optional<Integer> pagina) {
		if (pagina.isPresent() && pagina.get() >= 0) {
			page = PageRequest.of(pagina.get(), numeroPaginacion, Sort.by(Sort.Direction.ASC, "id"));
			model.addAttribute("datosFiscales", serviceDatos.indexPaginate(page));
		} else {
			page = PageRequest.of(0, numeroPaginacion, Sort.by(Sort.Direction.ASC, "id"));
			model.addAttribute("datosFiscales", serviceDatos.indexPaginate(page));
		}
		return "/clientes/datosFiscales/listDatosFiscales";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
