package com.cibertec.app.service.impl;

import com.cibertec.app.entity.OrdenCompra;
import com.cibertec.app.repository.OrdenCompraRepository;
import com.cibertec.app.service.OrdenCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenCompraServiceImpl implements OrdenCompraService {

    @Autowired
    private OrdenCompraRepository repository;

    @Override
    public List<OrdenCompra> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<OrdenCompra> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public OrdenCompra guardar(OrdenCompra ordenCompra) {
        return repository.save(ordenCompra);
    }

    @Override
    public void eliminarPorId(Long id) {
        repository.deleteById(id);
    }
}
