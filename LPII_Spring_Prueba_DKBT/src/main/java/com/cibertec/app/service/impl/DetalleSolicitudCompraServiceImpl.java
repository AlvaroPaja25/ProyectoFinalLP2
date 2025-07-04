package com.cibertec.app.service.impl;

import com.cibertec.app.entity.DetalleSolicitudCompra;
import com.cibertec.app.entity.DetalleSolicitudCompraId;
import com.cibertec.app.repository.DetalleSolicitudCompraRepository;
import com.cibertec.app.service.DetalleSolicitudCompraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleSolicitudCompraServiceImpl implements DetalleSolicitudCompraService {

    @Autowired
    private DetalleSolicitudCompraRepository repository;

    @Override
    public List<DetalleSolicitudCompra> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<DetalleSolicitudCompra> obtenerPorId(DetalleSolicitudCompraId id) {
        return repository.findById(id);
    }

    @Override
    public DetalleSolicitudCompra guardar(DetalleSolicitudCompra detalle) {
        return repository.save(detalle);
    }

    @Override
    public void eliminarPorId(DetalleSolicitudCompraId id) {
        repository.deleteById(id);
    }
}
