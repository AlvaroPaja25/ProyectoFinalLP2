package com.cibertec.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.app.entity.Producto;
import com.cibertec.app.repository.ProductoRepository;
import com.cibertec.app.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService{

	@Autowired
	ProductoRepository productoRepository;
    
	@Override
	public Producto guardarProducto(Producto userEntity) {
		return productoRepository.save(userEntity);
	}

	@Override
	public List<Producto> listarTodosProductos() {
		return productoRepository.findAll();
	}

	@Override
	public Producto actualizarProducto(Producto userEntity) {
		return productoRepository.save(userEntity);
	}

	@Override
	public void DescontinuarProductoById(Long idProd) {
		productoRepository.descontinuarProducto(idProd);
	}

	@Override
	public Producto buscarProductoById(Long idProd) {
		return productoRepository.findById(idProd).get();
	}

	@Override
	public Producto buscarProductoByName(String nombre) {
		return productoRepository.buscarProductoPorNombre(nombre);
	}

	@Override
	public List<Producto> listarTodosProductosVigentes() {
		return productoRepository.listarVigentes();
	}

	@Override
	public List<Producto> listarTodosProductosDescontinuados() {
		return productoRepository.listarDescontinuados();
	}
}