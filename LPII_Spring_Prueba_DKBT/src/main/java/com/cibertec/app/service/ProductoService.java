package com.cibertec.app.service;

import java.util.List;

import com.cibertec.app.entity.Producto;

public interface ProductoService {
	
	public Producto guardarProducto(Producto userEntity);

	public List<Producto> listarTodosProductos();
	
	public List<Producto> listarTodosProductosVigentes();
	
	public List<Producto> listarTodosProductosDescontinuados();

	public Producto actualizarProducto(Producto userEntity);

	public void DescontinuarProductoById(Long idProd);
	
	public Producto buscarProductoById(Long idProd);
	
	public Producto buscarProductoByName(String nombre);

}
