package com.example.altaTecnologia.controllers.ordenesServicio;

import java.io.ByteArrayInputStream;
import java.io.File;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.altaTecnologia.models.ordenesDeServicio.categoriasProductos;
import com.example.altaTecnologia.models.ordenesDeServicio.imagenesProductos;
import com.example.altaTecnologia.models.ordenesDeServicio.serviceprod;
import com.example.altaTecnologia.models.ordenesDeServicio.unidadmedida;
import com.example.altaTecnologia.service.OrdenesService.ICategoriasProductos;
import com.example.altaTecnologia.service.OrdenesService.IMedidasService;
import com.example.altaTecnologia.service.OrdenesService.IProductImagenes;
import com.example.altaTecnologia.service.OrdenesService.IProductServicios;
import com.example.altaTecnologia.utils.ReportsGenerated;
import com.example.altaTecnologia.utils.Util;

@Controller
@RequestMapping("/productosService")
public class ProducServiceController {
	@Value("${altaTecnologia.ruta.imagenes}")
	private String ruta;
	@Value("${paginacion.numeroDePaginas}")
	private int numeroPaginacion;

	@Autowired
	private ICategoriasProductos serviceCat;

	@Autowired
	private IProductServicios serviceProd;
	@Autowired
	private IMedidasService serviceMedidas;

	@Autowired
	private IProductImagenes servicesImagenes;

	@RequestMapping("/save")
	public String guardar(@ModelAttribute serviceprod serviceprod, BindingResult result, RedirectAttributes attributes,
			Model model, HttpServletRequest servletRequest, @RequestParam("images") List<MultipartFile> files,
			@RequestParam("archivoImagen") MultipartFile archivoImagen) {

		List<unidadmedida> lista = serviceMedidas.buscarTodas();
		model.addAttribute("unidadmedidas", lista);
		/*******************************/

		if (!archivoImagen.isEmpty()) {
			String nombreArchivo = Util.guardarArchivo(archivoImagen, ruta);
			if (nombreArchivo != null) { // El archivo () si se subio
				// solicitud.setArchivo(); // Asignamos el nombre de la imagen

				serviceprod.setImgPrincipal(nombreArchivo);

			}
		}
		serviceprod.setEstatusEliminada(1);
		serviceProd.guardar(serviceprod);

		if (null != files && files.size() > 0 && !files.get(0).isEmpty()) {
			for (MultipartFile multipartFile : files) {

				String nombreArchivo = Util.guardarArchivo(multipartFile, ruta);
				if (nombreArchivo != null) { // El archivo (CV) si se subio
					// solicitud.setArchivo(); // Asignamos el nombre de la imagen
					imagenesProductos imagenesProductos = new imagenesProductos();
					imagenesProductos.setIdProducto(serviceprod.getId());
					imagenesProductos.setNombre(nombreArchivo);
					servicesImagenes.guardar(imagenesProductos);
					System.out.println(nombreArchivo);

				}
			}
		}

		/******************************/
		if (result.hasErrors()) {

			return "ordenes/serviciosProductos/formServicios";

		}

		return "redirect:/productosService/indexPaginate/0";

	}

	@RequestMapping("/delete/img/{id}/{idProd}")
	public String eliminarImg(@PathVariable("id") int id, @PathVariable("idProd") int idProd,
			RedirectAttributes attributes) {

		Optional<imagenesProductos> img = servicesImagenes.buscarPorId(id);
		if (img.isPresent()) {
			servicesImagenes.eliminarPorId(id);
		}

		return "redirect:/productosService/edit/" + idProd;
	}

	@RequestMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int id, RedirectAttributes attributes) {

		Optional<serviceprod> product = serviceProd.buscarPorId(id);
		if (product.isPresent()) {
			serviceprod serviceprod = product.get();
			serviceprod.setEstatusEliminada(0);
			serviceProd.guardar(serviceprod);
			File fichero = new File(ruta + serviceprod.getImgPrincipal());
			if (fichero.delete())
				System.out.println("El fichero ha sido borrado satisfactoriamente");
			else
				System.out.println("El fichero no puede ser borrado");
			List<imagenesProductos> lista = servicesImagenes.buscarPorProducto(id);
			// servicesImagenes.eliminar(id);
			for (imagenesProductos l : lista) {
				fichero = new File(ruta + l.getNombre());
				if (fichero.delete())
					System.out.println("El fichero ha sido borrado satisfactoriamente");
				else
					System.out.println("El fichero no puede ser borrado");
			}
			attributes.addFlashAttribute("msj", "Servicio eliminado");

		}
		servicesImagenes.eliminar(id);
		return "redirect:/productosService/indexPaginate/0";

	}

	@RequestMapping("edit/{id}")
	public String editar(Model model, @ModelAttribute serviceprod serviceprod, BindingResult result,
			@PathVariable("id") int id) {
		System.out.println("jej");
		List<imagenesProductos> imagenes = servicesImagenes.buscarPorProducto(id);
		for (imagenesProductos i : imagenes) {
			System.out.println(i.getNombre());
		}
		Optional<serviceprod> product = serviceProd.buscarPorId(id);

		List<categoriasProductos> lista1 = serviceCat.buscarTodas();
		List<unidadmedida> lista = serviceMedidas.buscarTodas();
		model.addAttribute("imagenes", imagenes);
		model.addAttribute("unidadmedidas", lista);
		model.addAttribute("categorias", lista1);
		if (product.isPresent()) {
			model.addAttribute("serviceprod", product.get());

			model.addAttribute("unidadmedidas", lista);

			return "ordenes/serviciosProductos/formServicios";
		} else {
			return "redirect:/productosService/indexPaginate/0";
		}

	}

	@RequestMapping("/create")
	public String crear(Model model, @ModelAttribute serviceprod serviceprod, BindingResult result) {
		List<categoriasProductos> lista1 = serviceCat.buscarTodas();
		List<unidadmedida> lista = serviceMedidas.buscarTodas();
		model.addAttribute("unidadmedidas", lista);
		model.addAttribute("categorias", lista1);
		return "ordenes/serviciosProductos/formServicios";
	}

	@RequestMapping("/indexPaginate/{page}/nombre/{nombre}")
	public String indexnombre(Model model, Pageable page, @PathVariable("page") Optional<Integer> pagina,
			@PathVariable("nombre") String nombre) {
		model.addAttribute("nombre", nombre);
		if (pagina.isPresent() && pagina.get() > 0) {
			page = PageRequest.of(pagina.get(), numeroPaginacion);
			Page<serviceprod> lista = serviceProd.buscarPorNombre(page, nombre);
			model.addAttribute("servicios", lista);
			return "ordenes/serviciosProductos/listServicios";
		} else {
			page = PageRequest.of(0, numeroPaginacion);
			Page<serviceprod> lista = serviceProd.buscarPorNombre(page, nombre);
			model.addAttribute("servicios", lista);

			return "ordenes/serviciosProductos/listServicios";
		}

	}

	@GetMapping("/reportes")
	public ResponseEntity<InputStreamResource> reporteProductos() throws Exception {
		ByteArrayInputStream stream = ReportsGenerated.getAllProductos(serviceProd.buscarTodas());
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=productos.xls");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));

	}

	@RequestMapping("/indexPaginate/{page}/desc/{desc}")
	public String indexdesc(Model model, Pageable page, @PathVariable("page") Optional<Integer> pagina,
			@PathVariable("desc") String desc) {
		model.addAttribute("desc", desc);
		if (pagina.isPresent() && pagina.get() > 0) {
			page = PageRequest.of(pagina.get(), numeroPaginacion);
			Page<serviceprod> lista = serviceProd.buscarPorDescripcion(page, desc);
			model.addAttribute("servicios", lista);
			model.addAttribute("desc", desc);
			return "ordenes/serviciosProductos/listServicios";
		} else {

			page = PageRequest.of(0, numeroPaginacion);
			Page<serviceprod> lista = serviceProd.buscarPorDescripcion(page, desc);
			model.addAttribute("servicios", lista);
			return "ordenes/serviciosProductos/listServicios";
		}

	}

	@RequestMapping("/indexPaginate/{page}/id/{id}")
	public String indexId(Model model, Pageable page, @PathVariable("page") Optional<Integer> pagina,
			@PathVariable("id") int id) {
		model.addAttribute("id", id);
		if (pagina.isPresent() && pagina.get() > 0) {
			page = PageRequest.of(pagina.get(), numeroPaginacion);
			Page<serviceprod> lista = serviceProd.buscarTodosPoriD(page, id);
			model.addAttribute("servicios", lista);
			return "ordenes/serviciosProductos/listServicios";
		} else {
			page = PageRequest.of(0, numeroPaginacion);
			Page<serviceprod> lista = serviceProd.buscarTodosPoriD(page, id);
			model.addAttribute("servicios", lista);
			return "ordenes/serviciosProductos/listServicios";
		}

	}

	@RequestMapping("/indexPaginate/{page}")
	public String index(Model model, Pageable page, @PathVariable("page") Optional<Integer> pagina) {

		if (pagina.isPresent() && pagina.get() > 0) {
			page = PageRequest.of(pagina.get(), numeroPaginacion);
			Page<serviceprod> lista = serviceProd.indexTodas(page);
			model.addAttribute("servicios", lista);
			return "ordenes/serviciosProductos/listServicios";
		} else {
			page = PageRequest.of(0, numeroPaginacion);
			Page<serviceprod> lista = serviceProd.indexTodas(page);
			model.addAttribute("servicios", lista);
			return "ordenes/serviciosProductos/listServicios";
		}

	}
}
