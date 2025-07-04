package com.cibertec.app.service;

import com.cibertec.app.dto.DetalleSolicitudDTO;
import com.cibertec.app.dto.SolicitudCompraFormDTO;
import com.cibertec.app.entity.SolicitudCompra;

import java.util.List;
import java.util.Optional;

public interface SolicitudCompraService {

    List<SolicitudCompra> listarTodos();

    Optional<SolicitudCompra> obtenerPorId(Long id);

    SolicitudCompra guardar(SolicitudCompra solicitudCompra);

    void eliminarPorId(Long id);
    
    List<DetalleSolicitudDTO> obtenerProductosReabastecimiento();
    
    void guardarSolicitudConOrdenes(SolicitudCompraFormDTO dto);
}
