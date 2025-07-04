package com.cibertec.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.app.entity.Proveedor;
import com.cibertec.app.repository.ProveedorRepository;
import com.cibertec.app.service.ProveedorService;


@Service
public class ProveedorServiceImpl  implements ProveedorService{

	@Autowired
	ProveedorRepository proveedorRepository;
	
	@Override
	public Proveedor guardarProveedor(Proveedor userEntity) {
		return proveedorRepository.save(userEntity);
	}

	@Override
	public List<Proveedor> listarTodosProveedores() {
		return proveedorRepository.findAll();
	}

	@Override
	public Proveedor actualizarProducto(Proveedor userEntity) {
		return proveedorRepository.save(userEntity);
	}

	@Override
	public void deshabilitarProveedorById(Long idProv) {
		proveedorRepository.deshabilitarProveedor(idProv);
		
	}

	@Override
	public Proveedor obtenerProveedorById(Long idProv) {
		return proveedorRepository.findById(idProv).get();
	}

	@Override
	public Proveedor buscarProveedorByName(String nombre) {
		return proveedorRepository.buscarProveedorPorNombre(nombre);
	}

	@Override
	public void activarProveedorById(Long id) {
		proveedorRepository.activarProveedorById(id);
	}

}
