package com.example.altaTecnologia.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.altaTecnologia.models.vitacora;
import com.example.altaTecnologia.service.IVitacoraService;

public class ReportsGenerated {
	@Autowired
	IVitacoraService repoService;

	public static ByteArrayInputStream getAll(List<vitacora> vitacoras) throws IOException {
		String[] colums = { "id", "fecha", "horaInicial", "horaFinal", "categoriaActiv", "proyecto", "licencia",
				"contacto", "correoTel", "estatus", "subCat", "notas", "minDuracion" };
		// creo libro
		HSSFWorkbook wb = new HSSFWorkbook();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		// creo hoja
		HSSFSheet sheet = wb.createSheet();

		// CREAR BACKGROUND
		HSSFCellStyle titleStyle = wb.createCellStyle();
		HSSFPalette palette = wb.getCustomPalette();
		HSSFColor myColor = palette.findSimilarColor(193, 206, 212);
		short palIndex = myColor.getIndex();
		titleStyle.setFillForegroundColor(palIndex);
		titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		// creo renglon
		HSSFRow row = sheet.createRow(0);

		// creo celdas
		for (int i = 0; i < colums.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(colums[i]);
			// style.setFillForegroundColor(HSSFColor.LIME.index);
			cell.setCellStyle(titleStyle);
		}
		// CREAR BACKGROUND
		HSSFCellStyle titleStyle2 = wb.createCellStyle();
		HSSFPalette palette2 = wb.getCustomPalette();
		HSSFColor myColor2 = palette.findSimilarColor(135, 187, 213);
		short palIndex2 = myColor2.getIndex();
		titleStyle2.setFillForegroundColor(palIndex2);
		titleStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		List<vitacora> lista = vitacoras;
		int initRow = 1;

		for (vitacora v : lista) {
			row = sheet.createRow(initRow);

			HSSFCell cell = row.createCell(0);
			cell.setCellValue(v.getId());
			// style.setFillForegroundColor(HSSFColor.LIME.index);
			cell.setCellStyle(titleStyle2);

///Me carga la chingada xd
			row.createCell(1).setCellValue(v.getFecha() + "");
			row.getCell(1).setCellStyle(titleStyle2);
sheet.setDefaultColumnWidth(10);
			row.createCell(2).setCellValue(v.getHoraInicial());
			row.getCell(2).setCellStyle(titleStyle2);
			row.createCell(3).setCellValue(v.getHoraFinal());
			row.getCell(3).setCellStyle(titleStyle2);
			row.createCell(4).setCellValue(v.getCusategoriaActiv().getNombre());
			row.getCell(4).setCellStyle(titleStyle2);
			row.createCell(5).setCellValue(v.getProyecto().getNombre());
			row.getCell(5).setCellStyle(titleStyle2);
			row.createCell(6).setCellValue(v.getLicencia());
			row.getCell(6).setCellStyle(titleStyle2);
			row.createCell(7).setCellValue(v.getContacto());
			row.getCell(7).setCellStyle(titleStyle2);
			row.createCell(8).setCellValue(v.getCorreoTel());
			row.getCell(8).setCellStyle(titleStyle2);
			sheet.setColumnWidth(8, 10000);
			row.createCell(9).setCellValue(v.getEstatus());
			row.getCell(9).setCellStyle(titleStyle2);
			row.createCell(10).setCellValue(v.getSubCat().getNombre());
			row.getCell(10).setCellStyle(titleStyle2);
			sheet.setColumnWidth(10, 10000);
			row.createCell(11).setCellValue(v.getNotas());
			row.getCell(11).setCellStyle(titleStyle2);
			row.createCell(12).setCellValue(v.getMinDuracion());
			row.getCell(12).setCellStyle(titleStyle2);

			initRow++;

		}

		wb.write(stream);
		wb.close();

		return new ByteArrayInputStream(stream.toByteArray());
	}

}
