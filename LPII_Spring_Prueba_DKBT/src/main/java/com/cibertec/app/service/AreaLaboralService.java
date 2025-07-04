package com.cibertec.app.service;

import java.util.List;

import com.cibertec.app.entity.AreaLaboral;


public interface AreaLaboralService {

	public List<AreaLaboral> listarTodosAreaLaboral();
	
	public AreaLaboral buscarById(Integer id);
}
