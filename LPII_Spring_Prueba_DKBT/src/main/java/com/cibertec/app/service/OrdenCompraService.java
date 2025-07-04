package com.cibertec.app.service;

import com.cibertec.app.entity.OrdenCompra;

import java.util.List;
import java.util.Optional;

public interface OrdenCompraService {

    List<OrdenCompra> listarTodos();

    Optional<OrdenCompra> obtenerPorId(Long id);

    OrdenCompra guardar(OrdenCompra ordenCompra);

    void eliminarPorId(Long id);
}
