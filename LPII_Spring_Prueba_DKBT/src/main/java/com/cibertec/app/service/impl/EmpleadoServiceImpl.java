package com.cibertec.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.app.entity.Empleado;
import com.cibertec.app.repository.EmpleadoRepository;
import com.cibertec.app.service.EmpleadoService;


@Service
public class EmpleadoServiceImpl implements EmpleadoService{
	private EmpleadoRepository empleadoRepositorio;
	
	@Autowired
    private EmpleadoRepository empleadoRepository;
	
	public EmpleadoServiceImpl(EmpleadoRepository empleadoRepositorio) {
		super();
		this.empleadoRepositorio = empleadoRepositorio;
	}

	@Override
	public Empleado guardarEmpleado(Empleado objEmpleado) {		
		return empleadoRepositorio.save(objEmpleado);
	}

	@Override
	public List<Empleado> listarTodosEmpleado() {
		return empleadoRepositorio.findAll();
	}

	@Override
	public Empleado buscarEmpleadooById(Long idEmpleado) {
		return empleadoRepositorio.findById(idEmpleado).get();
	}

	@Override
	public Empleado actualizarEmpleado(Empleado registroDTO) {
		return empleadoRepositorio.save(registroDTO);
	}

	@Override
    public List<Empleado> buscarPorNombreOApellido(String term) {
        return empleadoRepository.findByNombresContainingIgnoreCaseOrApellidosContainingIgnoreCase(term, term);
    }
	
}
	

