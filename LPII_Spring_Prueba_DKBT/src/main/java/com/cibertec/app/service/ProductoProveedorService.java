package com.cibertec.app.service;

import java.util.List;

import com.cibertec.app.dto.ProveedorPrecioDTO;
import com.cibertec.app.entity.ProductoProveedor;

public interface ProductoProveedorService {

	public ProductoProveedor guardarProductoProveedor(ProductoProveedor userEntity);
	
	public ProductoProveedor obtenerRelacion(Long idProducto, Long idProveedor );
		
	public void deshabilitarRelacionPorProveedor(Long idProveedor);
	
	public void deshabilitarRelacionPorProducto(Long idProducto);
	
	public void deshabilitarRelacionPorProductoYProveedor(Long idProveedor, Long idProducto);

	public List<ProductoProveedor> listarTodosProductoProveedor();
	
	public List<ProveedorPrecioDTO> listarProveedorConPrecio(Long idProd);
	
	public List<ProductoProveedor> buscarProductoProveedorByProductoId(Long id_producto);
	
	public List<ProductoProveedor> buscarProductoProveedorByProveedorId(Long id_proveedor);
	
	void activarRelacionPorProveedor(Long idProveedor);
	
	void activarRelacionPorProductoYProveedor(Long idProveedor, Long idProducto);

}
