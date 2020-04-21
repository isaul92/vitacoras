package com.example.altaTecnologia.service.OrdenesService.Clientes;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.altaTecnologia.models.Clientes.requisitosfiscales;
import com.example.altaTecnologia.repository.OrdenesReposity.Clientes.IRequistosFiscales;
import com.example.altaTecnologia.repository.OrdenesReposity.Clientes.SeviceInformacionFiscal;

@Service
public class ServiceInformacionFiscal implements IRequistosFiscales {
	

	@Value("${altaTecnologia.ruta.informacionFiscal}")
	private String rutaInformacion;
	@Autowired
	private SeviceInformacionFiscal repoFiscal;

	@Override
	public void guardar(requisitosfiscales re) {
		repoFiscal.save(re);

	}

	@Override
	public void eliminar(int id,requisitosfiscales r) {
		
		
		File fichero = new File(rutaInformacion + r.getCer());
		if (fichero.delete())
			System.out.println("El fichero ha sido borrado satisfactoriamente");
		else
			System.out.println("El fichero no puede ser borrado");
		fichero = new File(rutaInformacion + r.getKeyFiscal());
		if (fichero.delete())
			System.out.println("El fichero ha sido borrado satisfactoriamente");
		else
			System.out.println("El fichero no puede ser borrado");
		repoFiscal.deleteById(id);
	}

	@Override
	public List<requisitosfiscales> buscarTodos() {
		// TODO Auto-generated method stub
		return repoFiscal.findAll();
	}

	@Override
	public Optional<requisitosfiscales> buscarPorId(int id) {
		// TODO Auto-generated method stub
		return repoFiscal.findById(id);
	}

}
