package com.cibertec.app.service;

import com.cibertec.app.entity.DetalleOrdenCompra;
import com.cibertec.app.entity.DetalleOrdenCompraId;

import java.util.List;
import java.util.Optional;

public interface DetalleOrdenCompraService {
    
    List<DetalleOrdenCompra> listarTodos();

    Optional<DetalleOrdenCompra> obtenerPorId(DetalleOrdenCompraId id);

    DetalleOrdenCompra guardar(DetalleOrdenCompra detalle);

    void eliminarPorId(DetalleOrdenCompraId id);
}
