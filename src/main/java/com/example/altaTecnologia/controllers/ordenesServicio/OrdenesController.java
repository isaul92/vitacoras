package com.example.altaTecnologia.controllers.ordenesServicio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.altaTecnologia.models.Clientes.clientes;
import com.example.altaTecnologia.models.ordenesDeServicio.ordenesPartidas;
import com.example.altaTecnologia.models.ordenesDeServicio.ordenservicio;
import com.example.altaTecnologia.models.ordenesDeServicio.partidas;
import com.example.altaTecnologia.models.ordenesDeServicio.partidasordenservicios;
import com.example.altaTecnologia.models.ordenesDeServicio.serviceprod;
import com.example.altaTecnologia.service.OrdenesService.IOrdenesServicio;
import com.example.altaTecnologia.service.OrdenesService.IPartidas;
import com.example.altaTecnologia.service.OrdenesService.IProductServicios;
import com.example.altaTecnologia.service.OrdenesService.Clientes.IClientes;
import com.example.altaTecnologia.utils.Util;

@Controller
@RequestMapping("/ordenes")
@SessionAttributes("ordenesPartidas")
public class OrdenesController {

	private int p = 0;

	@Value("${paginacion.numeroDePaginas}")
	private int numeroPaginacio;
	@Autowired
	private IClientes serviceClientes;
	@Autowired
	private IPartidas servicePartidas;
	@Autowired
	private IProductServicios serviceProductos;
	@Autowired
	private IOrdenesServicio serviceOrdenes;

	private double calcularTotal(List<partidas> productos) {
		double total = 0;
		for (partidas p : productos) {
			total += p.getCantidad() * p.getServProdId().getPrecio();
		}
		return total;
	}

	@RequestMapping("/editar/{id}")
	public String editar(@PathVariable("id") int id, RedirectAttributes attribute, Model model,
			@ModelAttribute("ordenesPartidas") ordenesPartidas ordenesPartidas, ordenservicio ordenservicio) {
		Optional<ordenservicio> orden = serviceOrdenes.buscarPorId(id);

		if (orden.isPresent()) {
			model.addAttribute("partidas", orden.get());
			model.addAttribute("editar", "editar");
			model.addAttribute("ordenservicio", orden.get());
			/*
			 * if (ordenesPartidas != null) {
			 * 
			 * List<partidas> p = ordenesPartidas.getPartida(); p.clear();
			 * 
			 * for (partidasordenservicios partidas : orden.get().getPartidas()) { partidas
			 * partida = new partidas(); partida.setCantidad(partidas.getCantidad());
			 * partida.setImporte(partidas.getCantidad() *
			 * partidas.getServProdId().getPrecio());
			 * partida.setServProdId(partidas.getServProdId()); p.add(partida); }
			 * ordenesPartidas.setTotal(100); ordenesPartidas.setPartida(p);
			 * 
			 * model.addAttribute("partidas", ordenesPartidas); } else {
			 * model.addAttribute("partidas", new ordenesPartidas()); }
			 */

		} else {
			attribute.addFlashAttribute("msg", "No existe Orden");
			return "redirect:/ordenes/indexPaginate/0";
		}

		return "ordenes/ordenes/formOrden";
	}

	@RequestMapping("/save")
	public String guardar(ordenservicio ordenservicio,
			@ModelAttribute("ordenesPartidas") ordenesPartidas ordenesPartidas, BindingResult result,
			RedirectAttributes attribute, @RequestParam("estatus") String estatus) {

		if (ordenservicio.getId() != 0) {

			if (ordenservicio.getEstatus().equalsIgnoreCase("CANCELADA") && !estatus.equalsIgnoreCase("CANCELADA")) {
				attribute.addFlashAttribute("msgc", "¡NO SE PUEDEN MODIFICAR ORDENES CANCELADAS!");
				return "redirect:/ordenes/editar/" + ordenservicio.getId();
			} else if (ordenservicio.getEstatus().equalsIgnoreCase("CANCELADA")) {
				attribute.addFlashAttribute("msgc", "¡NO SE PUEDEN MODIFICAR ORDENES CANCELADAS!");
				return "redirect:/ordenes/editar/" + ordenservicio.getId();
			}

			serviceOrdenes.guardar(ordenservicio);
			return "redirect:/ordenes/editar/" + ordenservicio.getId();

		} else {

			if (ordenesPartidas.getPartida().size() != 0) {

				if (ordenservicio.getClienteId() == null) {
					attribute.addFlashAttribute("msgc", "No existe cliente");
					return "redirect:/ordenes/crear";
				}

				ordenservicio
						.setDiasVigencia(Util.calcularFecha(ordenservicio.getFecha(), ordenservicio.getVigencia()));
				ordenservicio.setTotal(calcularTotal(ordenesPartidas.getPartida()));
				serviceOrdenes.guardar(ordenservicio);

				List<partidas> partidas = ordenesPartidas.getPartida();
				for (partidas p : partidas) {
					partidasordenservicios partidaOrden = new partidasordenservicios();
					partidaOrden.setServProdId(p.getServProdId());
					partidaOrden.setCantidad(p.getCantidad());
					partidaOrden.setOrdenId(ordenservicio);
					servicePartidas.guardar(partidaOrden);
				}

				List<partidas> p = ordenesPartidas.getPartida();
				p.clear();
				ordenesPartidas.setPartida(p);
				return "redirect:/ordenes/indexPaginate/0";

			} else {
				attribute.addFlashAttribute("msg", "No existen producto y/o servicios en la orden");
				return "redirect:/ordenes/crear";
			}

		}

	}

	@RequestMapping("/eliminarProducto/{id}")
	public String eliminarProduto(@PathVariable("id") int id,
			@ModelAttribute("ordenesPartidas") ordenesPartidas ordenesPartidas, RedirectAttributes attributes) {

		Optional<serviceprod> producto = serviceProductos.buscarPorId(id);
		if (producto.isPresent()) {

			serviceprod product = producto.get();
			if (ordenesPartidas != null) {
				List<partidas> partidas = ordenesPartidas.getPartida();

				partidas p = partidas.stream().filter(x -> product.getId() == (x.getServProdId().getId())).findAny()
						.orElse(null);

				if (p != null) {
					partidas.remove(p);
					ordenesPartidas.setTotal(calcularTotal(partidas));
					return "redirect:/ordenes/crear";
				}
			}
		} else {
			attributes.addFlashAttribute("msg", "No existe producto");
			return "redirect:/ordenes/crear";

		}

		return "redirect:/ordenes/crear";
	}

	@RequestMapping("/add/{id}/{cantidad}")
	public String añadirProducto(@PathVariable("id") int id, RedirectAttributes attributes,
			@ModelAttribute("ordenesPartidas") ordenesPartidas ordenesPartidas,
			@PathVariable("cantidad") int cantidad) {
		double importe = 0;

		Optional<serviceprod> producto = serviceProductos.buscarPorId(id);
		if (producto.isPresent()) {

			serviceprod product = producto.get();
			if (ordenesPartidas != null) {
				List<partidas> partidas = ordenesPartidas.getPartida();

				partidas p = partidas.stream().filter(x -> product.getId() == (x.getServProdId().getId())).findAny()
						.orElse(null);

				if (p != null) {

					cantidad = p.getCantidad() + cantidad;

					if (cantidad >= 0) {
						if (cantidad == 0) {
							partidas.remove(p);
							ordenesPartidas.setTotal(calcularTotal(partidas));
							return "redirect:/ordenes/crear";
						}

						p.setCantidad(cantidad);
						importe = p.getServProdId().getPrecio() * p.getCantidad();
						p.setImporte(importe);
						ordenesPartidas.setTotal(calcularTotal(partidas));
						ordenesPartidas.setPartida(partidas);

						return "redirect:/ordenes/crear";

					} else {
						attributes.addFlashAttribute("msg", "Cantidad Invalida");
						return "redirect:/ordenes/crear";
					}
				} else {

					int contador = ordenesPartidas.getContador();
					partidas partida = new partidas();
					partida.setId(contador);
					contador++;
					ordenesPartidas.setContador(contador);
					partida.setCantidad(1);
					partida.setServProdId(product);
					importe = partida.getServProdId().getPrecio() * partida.getCantidad();
					partida.setImporte(importe);
					partidas.add(partida);
					ordenesPartidas.setTotal(calcularTotal(partidas));
					ordenesPartidas.setPartida(partidas);

					return "redirect:/ordenes/crear";
				}

			} else {

				ordenesPartidas parti = new ordenesPartidas();

				List<partidas> partidas = new LinkedList<>();

				int contador = parti.getContador();
				partidas partida = new partidas();

				partida.setCantidad(cantidad);
				partida.setServProdId(product);
				partida.setId(contador);
				importe = partida.getServProdId().getPrecio() * partida.getCantidad();
				partida.setImporte(importe);

				partidas.add(partida);

				parti.setTotal(calcularTotal(partidas));
				parti.setPartida(partidas);

				contador++;
				parti.setContador(contador);
				return "redirect:/ordenes/crear";

			}
		} else {
			attributes.addFlashAttribute("msg", "No existe el producto/servico");
			return "redirect:/ordenes/crear";
		}

	}

	@RequestMapping("/crear")
	public String crear(Model model, ordenservicio ordenservicio, BindingResult result,
			@ModelAttribute("ordenesPartidas") ordenesPartidas ordenesPartidas) {

		if (ordenesPartidas != null) {
			model.addAttribute("partidas", ordenesPartidas);
		} else {
			model.addAttribute("partidas", new ordenesPartidas());
		}

		return "ordenes/ordenes/formOrden";
	}

	@RequestMapping("/indexPaginate/{page}/fecha/{fecha}")
	public String indexPaginateFecha(Model model, @PathVariable("page") Optional<Integer> pagina, Pageable page,
			@PathVariable("fecha") Date fecha) {
		model.addAttribute("fecha", fecha);
		if (pagina.isPresent() && pagina.get() >= 0) {

			page = PageRequest.of(pagina.get(), numeroPaginacio, Sort.by(Sort.Direction.DESC, "fecha"));
			model.addAttribute("ordenes", serviceOrdenes.indexPaginate(page, fecha));

		} else {
			page = PageRequest.of(0, numeroPaginacio);
			model.addAttribute("ordenes", serviceOrdenes.indexPaginate(page, fecha));

		}

		return "/ordenes/ordenes/listOrdenes";

	}

	@RequestMapping("/prueba")
	public String pureba() {
		this.p++;
		System.out.println(p);
		return "plantillaVacia";

	}

	@RequestMapping("/indexPaginate/{page}/nombre/{id}")
	public String indexPaginateNombre(Model model, @PathVariable("page") Optional<Integer> pagina, Pageable page,
			@PathVariable("id") int id, RedirectAttributes attributes) {
		calcularVigencias();
		if (serviceClientes.buscarPorId(id).isPresent()) {

			clientes nombre = serviceClientes.buscarPorId(id).get();
			model.addAttribute("nombre", id);
			if (pagina.isPresent() && pagina.get() >= 0) {

				page = PageRequest.of(pagina.get(), numeroPaginacio);
				model.addAttribute("ordenes", serviceOrdenes.indexPaginates(page, nombre));

			} else {
				page = PageRequest.of(0, numeroPaginacio);
				model.addAttribute("ordenes", serviceOrdenes.indexPaginates(page, nombre));

			}
		} else {
			attributes.addFlashAttribute("msg", "No existe cliente");
			return "redirect:/ordenes/indexPaginate/0/";
		}
		return "/ordenes/ordenes/listOrdenes";

	}

	@RequestMapping("/indexPaginate/{page}/folio/{id}")
	public String indexPaginateFolio(Model model, @PathVariable("page") Optional<Integer> pagina, Pageable page,
			@PathVariable("id") int id) {
		calcularVigencias();
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
		calcularVigencias();
		if (pagina.isPresent() && pagina.get() >= 0) {
			page = PageRequest.of(pagina.get(), numeroPaginacio);
			model.addAttribute("ordenes", serviceOrdenes.indexPaginate(page));

		} else {
			page = PageRequest.of(0, numeroPaginacio);
			model.addAttribute("ordenes", serviceOrdenes.indexPaginate(page));
		}

		return "/ordenes/ordenes/listOrdenes";
	}

	@ModelAttribute("ordenesPartidas")
	public ordenesPartidas ordenesPartidas() {
		return new ordenesPartidas();
	}

	private void calcularVigencias() {
		List<ordenservicio> ordenes = serviceOrdenes.buscarPorVigencia();
		for (ordenservicio o : ordenes) {
			o.setDiasVigencia(Util.calcularFechaVigencia(o.getVigencia()));
			serviceOrdenes.guardar(o);
		}
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}
