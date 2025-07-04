package com.cibertec.app.service;

import java.util.List;

import com.cibertec.app.entity.CargoEmpleado;


public interface CargoEmpleadoService {

	public List<CargoEmpleado> listarTodosCargoEmpleado();
	
	public CargoEmpleado buscarById(Integer id);

}
