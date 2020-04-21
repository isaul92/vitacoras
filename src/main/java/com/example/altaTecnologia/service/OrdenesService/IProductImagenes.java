package com.example.altaTecnologia.service.OrdenesService;

import java.util.List;
import java.util.Optional;

import com.example.altaTecnologia.models.ordenesDeServicio.imagenesProductos;

public interface IProductImagenes {
void guardar(imagenesProductos imagenesProductos);
void eliminar (int id);
List<imagenesProductos> buscarTodas();
List<imagenesProductos> buscarPorProducto(int id);
void eliminarPorId(int id);
Optional <imagenesProductos> buscarPorId(int id);
}
