package com.example.altaTecnologia.repository.OrdenesReposity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.altaTecnologia.models.ordenesDeServicio.unidadmedida;

public interface ServiceMedidasRepo extends JpaRepository<unidadmedida, Integer> {
	@Query("SELECT m FROM unidadmedida m where m.estatus=1 ")
	Page<unidadmedida> buscarTodas(Pageable page);

	@Query("SELECT m FROM unidadmedida m where m.estatus=1 ")
	List<unidadmedida> buscarTodas();

	@Query("SELECT m FROM unidadmedida m where m.id=:id and m.estatus=1 ")
	Optional<unidadmedida> buscarPorId(int id);

}
