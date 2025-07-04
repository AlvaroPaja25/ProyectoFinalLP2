package com.cibertec.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.app.entity.CargoEmpleado;
import com.cibertec.app.repository.CargoEmpleadoRepository;
import com.cibertec.app.service.CargoEmpleadoService;

@Service
public class CargoEmpleadoServiceImpl implements CargoEmpleadoService{

	@Autowired
	CargoEmpleadoRepository cargoEmpleadoRepository;
	
	@Override
	public List<CargoEmpleado> listarTodosCargoEmpleado() {
		return cargoEmpleadoRepository.findAll();
	}

	@Override
	public CargoEmpleado buscarById(Integer id) {
		return cargoEmpleadoRepository.findById(id).get();
	}

}
