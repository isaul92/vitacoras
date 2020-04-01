package com.example.altaTecnologia.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.altaTecnologia.models.vitacora;

public interface IVitacoraService {

Page<vitacora> buscarTodas(Pageable page);
Page<vitacora>buscarTodasFechasYLicencia(Pageable page,Date fecha,String licencia);
Page<vitacora> buscarTodasFechas(Pageable page,Date fecha);
Page<vitacora> buscarTodas(Pageable page,String licencia);
List<vitacora> buscarPorFechaReporte(Date fecha1,Date fecha2);
List <vitacora> buscarTodas();
Optional <vitacora> buscarPorId(int id);
void eliminar(int id);
void guardar(vitacora vitacora);
ByteArrayInputStream exportAllData()  throws IOException;
	
	
}
