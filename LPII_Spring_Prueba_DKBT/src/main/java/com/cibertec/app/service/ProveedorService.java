package com.cibertec.app.service;

import java.util.List;

import com.cibertec.app.entity.Proveedor;

public interface ProveedorService {
	
	public Proveedor guardarProveedor(Proveedor userEntity);

	public List<Proveedor> listarTodosProveedores();

	public Proveedor actualizarProducto(Proveedor userEntity);

	public void deshabilitarProveedorById(Long idProv);
	
	public Proveedor obtenerProveedorById(Long idProv);
	
	public Proveedor buscarProveedorByName(String nombre);
	
	void activarProveedorById(Long id);

}
