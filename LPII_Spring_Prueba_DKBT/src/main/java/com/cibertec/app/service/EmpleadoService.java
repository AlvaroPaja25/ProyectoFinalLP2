package com.cibertec.app.service;

import java.util.List;

import com.cibertec.app.entity.Empleado;

public interface EmpleadoService {

    public Empleado guardarEmpleado(Empleado registroDTO);
	
	public List<Empleado> listarTodosEmpleado();
	
	public Empleado buscarEmpleadooById(Long idEmpleado);
	
	public Empleado actualizarEmpleado(Empleado registroDTO);
	
	List<Empleado> buscarPorNombreOApellido(String term);

	
}
