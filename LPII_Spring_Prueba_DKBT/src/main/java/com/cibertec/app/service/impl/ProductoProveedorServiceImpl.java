package com.cibertec.app.service.impl;
import com.cibertec.app.dto.ProveedorPrecioDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.app.entity.ProductoProveedor;
import com.cibertec.app.repository.ProductoProveedorRepository;
import com.cibertec.app.service.ProductoProveedorService;


@Service
public class ProductoProveedorServiceImpl implements ProductoProveedorService {
	
	@Autowired
	ProductoProveedorRepository  productoProveedorRepository;
	
	public ProductoProveedor obtenerRelacion(Long idProducto, Long idProveedor ) {
		return productoProveedorRepository.obtenerRelacionPorIds(idProducto, idProveedor);
	}
	
	@Override
	public ProductoProveedor guardarProductoProveedor(ProductoProveedor userEntity) {
		return productoProveedorRepository.save(userEntity);
	}

	@Override
	public List<ProductoProveedor> listarTodosProductoProveedor() {
		return productoProveedorRepository.findAll();
	}

	@Override
	public List<ProductoProveedor> buscarProductoProveedorByProductoId(Long id_producto) {
		return productoProveedorRepository.buscarByProductoId(id_producto);
	}

	@Override
	public List<ProductoProveedor> buscarProductoProveedorByProveedorId(Long id_proveedor) {
		return productoProveedorRepository.buscarByProveedorId(id_proveedor);
	}

	@Override
	public List<ProveedorPrecioDTO> listarProveedorConPrecio(Long idProd) {
	    List<Object[]> datos = productoProveedorRepository.listarProveedoresConPrecioPorProducto(idProd);
	    List<ProveedorPrecioDTO> lista = new ArrayList<>();
	    
	    for (Object[] fila : datos) {
	        Long idProveedor = ((Number) fila[0]).longValue();
	        String nombreProveedor = (String) fila[1];
	        BigDecimal precio = new BigDecimal(fila[2].toString());
	        
	        lista.add(new ProveedorPrecioDTO(idProveedor, nombreProveedor, precio));
	    }
	    
	    return lista;
	}

	@Override
	public void deshabilitarRelacionPorProveedor(Long idProveedor) {
		 productoProveedorRepository.deshabilitarRelacionPorProveedorBD(idProveedor);
	}

	@Override
	public void deshabilitarRelacionPorProducto(Long idProducto) {
		 productoProveedorRepository.deshabilitarRelacionPorProductoBD(idProducto);
	}

	@Override
	public void deshabilitarRelacionPorProductoYProveedor(Long idProveedor, Long idProducto) {
		productoProveedorRepository.deshabilitarRelacionPorProveedorYProducto(idProveedor, idProducto);
	}

	@Override
	public void activarRelacionPorProveedor(Long idProveedor) {
		productoProveedorRepository.activarRelacionPorProveedor(idProveedor);
	}

	@Override
	public void activarRelacionPorProductoYProveedor(Long idProveedor, Long idProducto) {
		productoProveedorRepository.activarRelacionPorProductoYProveedor(idProveedor, idProducto);
	}

}
