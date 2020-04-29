package com.example.altaTecnologia.repository.OrdenesReposity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.altaTecnologia.models.ordenesDeServicio.partidasordenservicios;

public interface ServicePartidas extends JpaRepository<partidasordenservicios, Integer> {

}
