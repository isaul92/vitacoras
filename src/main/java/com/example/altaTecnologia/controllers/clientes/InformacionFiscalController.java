package com.example.altaTecnologia.controllers.clientes;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.altaTecnologia.models.Clientes.datosfiscales;
import com.example.altaTecnologia.models.Clientes.requisitosfiscales;
import com.example.altaTecnologia.repository.OrdenesReposity.Clientes.IRequistosFiscales;
//import com.example.altaTecnologia.service.OrdenesService.Clientes.IClientes;
import com.example.altaTecnologia.service.OrdenesService.Clientes.IDatosFiscales;
import com.example.altaTecnologia.utils.Util;

@Controller
@RequestMapping("/informacionFiscal")
public class InformacionFiscalController {
	@Autowired
	private IRequistosFiscales serviceFiscal;

//	@Autowired
//	private IClientes serviceClientes;

	@Autowired
	private IDatosFiscales serviceDatos;

	@Value("${altaTecnologia.ruta.informacionFiscal}")
	private String rutaInformacion;

	@RequestMapping(value = "/delete/{id}")
	public String eliminar(@PathVariable("id") int id) {
		Optional<requisitosfiscales> r = serviceFiscal.buscarPorId(id);
		if (r.isPresent()) {

			serviceFiscal.eliminar(id, r.get());
			return "redirect:/datosFiscales/edit/" + r.get().getDatosfiscales().getId();

		} else {
			return "redirect:/datosFiscales/indexPaginate/0";
		}

	}

	@RequestMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String guardar(@ModelAttribute requisitosfiscales requisitosfiscales, @RequestPart MultipartFile[] files,
			@RequestParam("idDatosFiscales") int idDatosFiscales, @RequestParam("idCliente") int idCliente,
			BindingResult result, Model model) throws IOException {
		boolean key = false;
		boolean cer = false;
		int b = 0;
		System.out.println(idDatosFiscales + "sdsd");
		/* VALIDANDO ARCHIVOS */
		Optional<datosfiscales> d = serviceDatos.buscarPorIdO(idDatosFiscales);
		datosfiscales datosfiscales = d.get();
		System.out.println(datosfiscales.getIdFiscal().size());
		model.addAttribute("datosfiscales", datosfiscales);
		model.addAttribute("id", idDatosFiscales);
		model.addAttribute("cliente", datosfiscales.getIdCliente());

		for (MultipartFile file : files) {
			// String fileName = file.getOriginalFilename();// NOMBRE DEL ARCHIVO

			String contentType = file.getContentType();// TIPO DE CONTENIDO EXTENCION

			if (b == 0 && contentType.equals("application/octet-stream") && !file.isEmpty()) {
				key = true;
			} else if (b == 0 && !file.isEmpty()) {

				model.addAttribute("key", "No es un archivo valido");
				return "/clientes/datosFiscales/formDatosFiscales";
			}

			if (b == 1 && contentType.equals("application/x-x509-ca-cert") && !file.isEmpty()) {
				cer = true;
				System.out.println("ES CER");
			} else if (b == 1 && !file.isEmpty()) {

				model.addAttribute("cer", "No es un archivo valido");
				return "/clientes/datosFiscales/formDatosFiscales";
			}
			b++;

		}
		if (cer && !files[0].isEmpty()) {
			String nombreArchivo = Util.guardarArchivo(files[0], rutaInformacion);
			requisitosfiscales.setKeyFiscal(nombreArchivo);

		}

		if (key && !files[1].isEmpty()) {
			String nombreArchivo = Util.guardarArchivo(files[1], rutaInformacion);
			requisitosfiscales.setCer(nombreArchivo);

		}
		requisitosfiscales.setDuracion(
				Util.calcularFecha(requisitosfiscales.getVigenciaIni(), requisitosfiscales.getVigencialFin()));

		requisitosfiscales.setDatosfiscales(datosfiscales);
		serviceFiscal.guardar(requisitosfiscales);

		return "redirect:/datosFiscales/edit/" + idDatosFiscales + "/" + requisitosfiscales.getId();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
