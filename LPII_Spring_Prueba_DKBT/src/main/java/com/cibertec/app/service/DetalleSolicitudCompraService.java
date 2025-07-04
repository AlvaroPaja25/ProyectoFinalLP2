package com.cibertec.app.service;

import com.cibertec.app.entity.DetalleSolicitudCompra;
import com.cibertec.app.entity.DetalleSolicitudCompraId;

import java.util.List;
import java.util.Optional;

public interface DetalleSolicitudCompraService {

    List<DetalleSolicitudCompra> listarTodos();

    Optional<DetalleSolicitudCompra> obtenerPorId(DetalleSolicitudCompraId id);

    DetalleSolicitudCompra guardar(DetalleSolicitudCompra detalle);

    void eliminarPorId(DetalleSolicitudCompraId id);
}
