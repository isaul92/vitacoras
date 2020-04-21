package com.example.altaTecnologia.controllers.vitacoras;

import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.altaTecnologia.models.vitacorasRoca.categoriasroca;
import com.example.altaTecnologia.models.vitacorasRoca.subcategoriaroca;
import com.example.altaTecnologia.models.vitacorasRoca.vitacora;
import com.example.altaTecnologia.service.RocaService.IServiceServiciosRocaCategorias;
import com.example.altaTecnologia.service.RocaService.ISubCategoriasService;
import com.example.altaTecnologia.service.RocaService.IVitacoraService;
import com.example.altaTecnologia.service.RocaService.IproyectosRoca;
import com.example.altaTecnologia.utils.ReportsGenerated;

@Controller

@RequestMapping("/vitacora")
public class VitacoraController {
	@Autowired
	private ISubCategoriasService serviceSubCat;

	@Autowired
	private IServiceServiciosRocaCategorias serviceCat;

	@Autowired
	private IVitacoraService serviceVita;

	@Autowired
	private IproyectosRoca serviceProyec;

	@RequestMapping("/ver")
	public String nueva(Model model) {

		categoriasroca v = new categoriasroca();
		v = serviceCat.buscarPorId(5).get();
		List<subcategoriaroca> lista = serviceSubCat.buscarPorCategoria(v);

		System.out.println(lista.size());

		return "as";

	}

	private long calcularHora(vitacora v) {

		long minutes = 0;
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("hh:mm");
		String strFecha = v.getHoraInicial();
		String strFecha1 = v.getHoraFinal();
		Date fecha1 = null;
		Date fecha2 = null;
		try {

			fecha1 = formatoDelTexto.parse(strFecha);
			fecha2 = formatoDelTexto.parse(strFecha1);

		

			long milisegundos = fecha2.getTime() - fecha1.getTime();
			System.out.println("milisegundos" + milisegundos);

			minutes = TimeUnit.MILLISECONDS.toMinutes(milisegundos);

			System.out.println(minutes);

		} catch (ParseException ex) {

			ex.printStackTrace();

		}

		return minutes;
	}

	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int id, vitacora vitacora, BindingResult result,
			RedirectAttributes attributes, Model model) {
		model.addAttribute("vitacora", serviceVita.buscarPorId(id));
		model.addAttribute("proyectos", serviceProyec.buscarTodos());
		List<categoriasroca> lista = serviceCat.buscarTodas();
		model.addAttribute("categorias", lista);
		model.addAttribute("subCategorias", serviceSubCat.buscarTodas());

		return "servicios/vitacora/formVitacora";

	}

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int id, RedirectAttributes attributes) {
		serviceVita.eliminar(id);
		attributes.addFlashAttribute("msg", "Vitacora eliminada");
		return "redirect:/vitacora/indexPaginate/0";
	}

	@PostMapping("/save")
	public String guardar(vitacora vitacora, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return "redirect:/vitacora/create";
		}

		int tiempo = (int) calcularHora(vitacora);
		vitacora.setMinDuracion(tiempo);
		serviceVita.guardar(vitacora);
		return "redirect:/vitacora/indexPaginate/0";
	}

	@RequestMapping("/create")
	public String nueva(vitacora vitacora, Model model, BindingResult result, RedirectAttributes attributes) {
		List<categoriasroca> lista = serviceCat.buscarTodas();
		model.addAttribute("proyectos", serviceProyec.buscarTodos());
		model.addAttribute("categorias", lista);
		model.addAttribute("subCategorias", serviceSubCat.buscarTodas());

		return "servicios/vitacora/formVitacora";
	}

	// indexPaginate/0/fecha/2020-04-02
	@RequestMapping("/indexPaginate/{page}/fecha/{fecha}")
	public String indexBuscarPorFecha(Model model, Pageable page, @PathVariable("page") Optional<Integer> pagina,
			@PathVariable("fecha") Date fecha) {

		if (pagina.isPresent() && pagina.get() >= 0) {

			page = PageRequest.of(pagina.get(), 5, Sort.by(Sort.Direction.ASC, "Fecha"));
			Page<vitacora> lista = serviceVita.buscarTodasFechas(page, fecha);
			model.addAttribute("vitacoras", lista);
			model.addAttribute("licencia", fecha);
			return "servicios/vitacora/listVitacora";

		} else {
			page = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "Fecha"));
			Page<vitacora> lista = serviceVita.buscarTodasFechas(page, fecha);
			model.addAttribute("vitacoras", lista);
			model.addAttribute("fecha", fecha);
			return "servicios/vitacora/listVitacora";
		}

	}

	@RequestMapping("/indexPaginate/{page}/{nombre}")
	public String indexBuscarPorNombre(Model model, Pageable page, @PathVariable("page") Optional<Integer> pagina,
			@PathVariable("nombre") String nombre) {
		if (pagina.isPresent() && pagina.get() >= 0) {

			page = PageRequest.of(pagina.get(), 5, Sort.by(Sort.Direction.ASC, "Fecha"));
			Page<vitacora> lista = serviceVita.buscarTodas(page, nombre);
			model.addAttribute("vitacoras", lista);
			model.addAttribute("licencia", nombre);
			return "servicios/vitacora/listVitacora";

		} else {
			page = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "Fecha"));
			Page<vitacora> lista = serviceVita.buscarTodas(page, nombre);
			model.addAttribute("vitacoras", lista);
			model.addAttribute("licencia", nombre);
			return "servicios/vitacora/listVitacora";
		}

	}


	@RequestMapping("/indexPaginate/{page}/{licencia}/{fecha}")
	public String indexBuscarPorFechaYLicencia(Model model, Pageable page,
			@PathVariable("page") Optional<Integer> pagina, @PathVariable("fecha") Date fecha,
			@PathVariable("licencia") String licencia) {

		if (pagina.isPresent() && pagina.get() >= 0) {

			page = PageRequest.of(pagina.get(), 5, Sort.by(Sort.Direction.ASC, "Fecha"));
			Page<vitacora> lista = serviceVita.buscarTodasFechasYLicencia(page, fecha, licencia);
			model.addAttribute("vitacoras", lista);
			model.addAttribute("fecha", fecha);
			model.addAttribute("licencia", licencia);
			return "servicios/vitacora/listVitacora";

		} else {
			page = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "Fecha"));
			Page<vitacora> lista = serviceVita.buscarTodasFechasYLicencia(page, fecha, licencia);
			model.addAttribute("vitacoras", lista);
			model.addAttribute("fecha", fecha);
			model.addAttribute("licencia", licencia);
			return "servicios/vitacora/listVitacora";
		}
	}

	@RequestMapping("/indexPaginate/{page}")
	public String index(Model model, Pageable page, @PathVariable("page") Optional<Integer> pagina) {
		if (pagina.isPresent() && pagina.get() > 0) {
			page = PageRequest.of(pagina.get(), 5, Sort.by(Sort.Direction.ASC, "Fecha"));
			Page<vitacora> lista = serviceVita.buscarTodas(page);
			model.addAttribute("vitacoras", lista);
			return "servicios/vitacora/listVitacora";

		} else {
			page = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "Fecha"));
			Page<vitacora> lista = serviceVita.buscarTodas(page);
			model.addAttribute("vitacoras", lista);
			return "servicios/vitacora/listVitacora";
		}

	}
@GetMapping("/reportes")
	public ResponseEntity<InputStreamResource> exportAllData() throws Exception {
		ByteArrayInputStream stream = ReportsGenerated.getAll(serviceVita.buscarTodas());
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition","attachment; filename=reporte.xls");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));

	}



	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
