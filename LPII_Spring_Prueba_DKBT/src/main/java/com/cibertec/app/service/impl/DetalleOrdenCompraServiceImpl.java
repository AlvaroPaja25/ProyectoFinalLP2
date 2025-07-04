package com.cibertec.app.service.impl;

import com.cibertec.app.entity.DetalleOrdenCompra;
import com.cibertec.app.entity.DetalleOrdenCompraId;
import com.cibertec.app.repository.DetalleOrdenCompraRepository;
import com.cibertec.app.service.DetalleOrdenCompraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleOrdenCompraServiceImpl implements DetalleOrdenCompraService {

    @Autowired
    private DetalleOrdenCompraRepository repository;

    @Override
    public List<DetalleOrdenCompra> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<DetalleOrdenCompra> obtenerPorId(DetalleOrdenCompraId id) {
        return repository.findById(id);
    }

    @Override
    public DetalleOrdenCompra guardar(DetalleOrdenCompra detalle) {
        return repository.save(detalle);
    }

    @Override
    public void eliminarPorId(DetalleOrdenCompraId id) {
        repository.deleteById(id);
    }
}
