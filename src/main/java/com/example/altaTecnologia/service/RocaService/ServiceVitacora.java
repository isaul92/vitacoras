package com.example.altaTecnologia.service.RocaService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.altaTecnologia.models.vitacorasRoca.vitacora;
import com.example.altaTecnologia.repository.RocaService.VitacoraRepository;

@Service
public class ServiceVitacora implements IVitacoraService {

	@Autowired
	VitacoraRepository repoVita;

	@Override
	public List<vitacora> buscarTodas() {
		// TODO Auto-generated method stub
		return repoVita.findAll();
	}

	@Override
	public Optional<vitacora> buscarPorId(int id) {
		// TODO Auto-generated method stub
		return repoVita.findById(id);
	}

	@Override
	public void eliminar(int id) {
		repoVita.deleteById(id);

	}

	@Override
	public void guardar(vitacora vitacora) {
		repoVita.save(vitacora);

	}

	@Override
	public Page<vitacora> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub

		return repoVita.buscarTodosOrdernadoFecha(page);
	}

	@Override
	public Page<vitacora> buscarTodas(Pageable page, String licencia) {
		// TODO Auto-generated method stub
		return repoVita.buscarTodosOrdernadoFecha(page, licencia);
	}

	@Override
	public Page<vitacora> buscarTodasFechas(Pageable page, Date fecha) {
		// TODO Auto-generated method stub
		return repoVita.buscarTodosFechaOrdernadoFecha(page, fecha);
	}

	@Override
	public Page<vitacora> buscarTodasFechasYLicencia(Pageable page, Date fecha, String licencia) {
		// TODO Auto-generated method stub
		return repoVita.buscarTodosFechaYLicenciaOrdernadoFecha(page, fecha, licencia);
	}

	@Override
	public ByteArrayInputStream exportAllData() throws IOException {
	
		return null;
		
	//	return ReportsGenerated.getAll(repoVita.findAll());

	}

	@Override
	public List<vitacora> buscarPorFechaReporte(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return repoVita.buscarPorFecha(fecha1, fecha2);
	}

}
