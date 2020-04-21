package com.example.altaTecnologia.controllers.clientes;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.altaTecnologia.models.Clientes.abonos;
import com.example.altaTecnologia.models.Clientes.clientes;
import com.example.altaTecnologia.models.Clientes.cuentasxcobrar;
import com.example.altaTecnologia.models.ordenesDeServicio.ordenservicio;
import com.example.altaTecnologia.models.ordenesDeServicio.partidasordenservicios;
import com.example.altaTecnologia.service.OrdenesService.IOrdenesServicio;
import com.example.altaTecnologia.service.OrdenesService.Clientes.IAbonos;
import com.example.altaTecnologia.service.OrdenesService.Clientes.IClientes;
import com.example.altaTecnologia.service.OrdenesService.Clientes.ICuentasxCobrar;
import com.example.altaTecnologia.utils.ReportsGenerated;

@Controller
@RequestMapping("/estado")
public class EstadoCuentaController {
	@Value("${paginacion.numeroDePaginas}")
	private int numeroPaginacio;
	@Autowired
	private IClientes serviceClientes;
	@Autowired
	private ICuentasxCobrar serviceCuentas;
	@Autowired
	private IOrdenesServicio serviceOrdenes;
	@Autowired
	private IAbonos serviceAbonos;

	@PostMapping("/abonar")
	public String abonar(@RequestParam("cantidad") double cantidad, @RequestParam("id") int id,
			RedirectAttributes attributes) {
		double deudaActual = 0;
		abonos abono = new abonos();

		Optional<ordenservicio> orden = serviceOrdenes.buscarPorId(id);
		if (orden.isPresent()) {
			ordenservicio ordenSer = orden.get();
			Date nuevaFecha = new Date(); // Sistema actual La fecha y la hora se asignan a objDate
			abono.setAbono(cantidad);
			abono.setFecha(nuevaFecha);
			abono.setIdCuentaCobrar(ordenSer.getEstadosDeCuenta());
			serviceAbonos.guardar(abono);
			deudaActual = ordenSer.getEstadosDeCuenta().getDedudaActual();
			deudaActual -= cantidad;
			cuentasxcobrar estado = ordenSer.getEstadosDeCuenta();
			estado.setDedudaActual(deudaActual);
			serviceCuentas.guardar(estado);
			serviceOrdenes.guardar(ordenSer);
			return "redirect:/estado/detalle/" + id;
		} else {
			attributes.addFlashAttribute("msg", "No existe informacion");
			return "redirect:/clientes/indexPaginate/0";
		}

	}

	@RequestMapping("/detalle/{id}")
	public String detalle(@PathVariable("id") int idOrden, Model model) {
		Optional<ordenservicio> orden = serviceOrdenes.buscarPorId(idOrden);
		if (orden.isPresent()) {
			double deudaTodal = 0;
			List<ordenservicio> ordenesLista = serviceOrdenes.buscarPorCliente(orden.get().getClienteId());
			for (ordenservicio or : ordenesLista) {
				deudaTodal += or.getEstadosDeCuenta().getDedudaActual();
			}
			model.addAttribute("deudaTotal", deudaTodal);
			double total = 0;
			for (partidasordenservicios p : orden.get().getPartidas()) {

				// total=total+orden.cantidad * orden.servProdId.precio
				total += p.getCantidad() * p.getServProdId().getPrecio();
			}
			model.addAttribute("total", total);
			model.addAttribute("cliente", orden.get().getClienteId());
			model.addAttribute("ordenes", orden.get());
			// model.addAttribute("servicios",orden.get().get)

		}
		return "/clientes/credito/detalleEstado";
	}

	@RequestMapping("/{id}/{page}/{fecha1}/{fecha2}")
	public String indexPaginateFechas(@PathVariable("fecha1") Date fecha1, @PathVariable("fecha2") Date fecha2,
			@PathVariable("id") int idCliente, @PathVariable("page") Optional<Integer> pagina, Model model,
			Pageable page) {
		model.addAttribute("fecha1", fecha1);
		model.addAttribute("fecha2", fecha2);
		Optional<clientes> clien = serviceClientes.buscarPorId(idCliente);
		clientes cliente;
		double deudaTodal = 0;
		if (clien.isPresent()) {
			cliente = clien.get();
			List<cuentasxcobrar> estados = serviceCuentas.buscarPorCliente(cliente);
			model.addAttribute("estados", estados);
			List<ordenservicio> ordenesLista = serviceOrdenes.buscarPorCliente(cliente);
			for (ordenservicio or : ordenesLista) {
				deudaTodal += or.getEstadosDeCuenta().getDedudaActual();
			}
			model.addAttribute("deudaTotal", deudaTodal);
			if (pagina.isPresent() && pagina.get() >= 0) {
				page = PageRequest.of(pagina.get(), numeroPaginacio);
				Page<ordenservicio> ordenes = serviceOrdenes.indexPaginates(page, cliente, fecha1, fecha2);
				model.addAttribute("ordenes", ordenes);
				model.addAttribute("cliente", clien.get());
				return "clientes/credito/listEstadoCuenta";
			} else {
				page = PageRequest.of(0, numeroPaginacio);
				model.addAttribute("ordenes", serviceOrdenes.indexPaginates(page, cliente, fecha1, fecha2));
				model.addAttribute("cliente", clien.get());
				return "clientes/credito/listEstadoCuenta";
			}
		} else {
			return "redirect:/clientes/indexPaginate/0";
		}

	}

	@GetMapping("/reportes/{id}/{fecha1}/{fecha2}")
	public ResponseEntity<InputStreamResource> reporte(@PathVariable("fecha1") Date fecha1,
			@PathVariable("fecha2") Date fecha2, @PathVariable("id") int idCliente) throws Exception {

		Optional<clientes> clien = serviceClientes.buscarPorId(idCliente);
		clientes cliente;

		if (clien.isPresent()) {
			cliente = clien.get();

			ByteArrayInputStream stream = ReportsGenerated
					.getReporteCreditos(serviceOrdenes.buscarTodasClienteFechas(cliente, fecha1, fecha2));
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "attachment; filename=productos.xls");
			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
		}
		return null;
	}

	@RequestMapping("/{id}/{page}")
	public String index(@PathVariable("id") int idCliente, @PathVariable("page") Optional<Integer> pagina, Model model,
			Pageable page) {

		Optional<clientes> clien = serviceClientes.buscarPorId(idCliente);
		clientes cliente;
		double deudaTodal = 0;
		if (clien.isPresent()) {
			cliente = clien.get();
			List<cuentasxcobrar> estados = serviceCuentas.buscarPorCliente(cliente);
			model.addAttribute("estados", estados);
			List<ordenservicio> ordenesLista = serviceOrdenes.buscarPorCliente(cliente);
			for (ordenservicio or : ordenesLista) {
				deudaTodal += or.getEstadosDeCuenta().getDedudaActual();
			}
			model.addAttribute("deudaTotal", deudaTodal);
			if (pagina.isPresent() && pagina.get() >= 0) {

				page = PageRequest.of(pagina.get(), numeroPaginacio);

				model.addAttribute("ordenes", serviceOrdenes.indexPaginates(page, cliente));
				model.addAttribute("cliente", clien.get());
				return "clientes/credito/listEstadoCuenta";
			} else {

				page = PageRequest.of(0, numeroPaginacio);

				model.addAttribute("ordenes", serviceOrdenes.indexPaginates(page, cliente));
				model.addAttribute("cliente", clien.get());

				return "clientes/credito/listEstadoCuenta";
			}
		} else {
			return "redirect:/clientes/indexPaginate/0";
		}

	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}
