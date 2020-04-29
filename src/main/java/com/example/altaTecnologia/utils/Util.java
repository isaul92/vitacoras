package com.example.altaTecnologia.utils;

import java.io.File;
import java.io.IOException;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Util {

	public static int calcularFechaVigencia(Date fecha2) {
		// fechaInicial //fechaFinal
		Date fecha = new Date();
		int dias = (int) ((fecha2.getTime() - fecha.getTime()) / 86400000);

		return dias;
	}

	public static int calcularFecha(Date fecha1, Date fecha2) {
		// fechaInicial //fechaFinal
		int dias = (int) ((fecha2.getTime() - fecha1.getTime()) / 86400000);

		return dias;
	}

	public static String guardarArchivo(MultipartFile multiPart, String ruta) {
		// Obtenemos el nombre original del archivo
		String nombreOriginal = multiPart.getOriginalFilename();
		// Obtenemos la ruta ABSOLUTA del directorio images
		// apache-tomcat/webapps/cineapp/resources/img/
		nombreOriginal = nombreOriginal.replace(" ", "-");

		// String rutaFinal=request.getServletContext().getRealPath("/images/");
		String nombreFinal = randomAlphanumeric(8) + nombreOriginal;

		try {
			// Formamos el nombre del archivo para guardarlo en el disco duro
			File imageFile = new File(ruta + nombreFinal);
			System.out.println(imageFile.getAbsolutePath());
			// Aqui se guarda fisicamente el archivo en el disco duro
			multiPart.transferTo(imageFile);
			System.out.println(imageFile.getAbsolutePath());
			return nombreFinal;
		} catch (IOException e) {
			System.out.println("Error " + e.getMessage());
			return null;
		}
	}

	public static String randomAlphanumeric(int count) {
		String CARACTERES = "ABCDFGHIJKLMNOPQRSTUVWXY0123456789";
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * CARACTERES.length());
			builder.append(CARACTERES.charAt(character));

		}
		return builder.toString();
	}
}
