package com.cibertec.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.app.entity.AreaLaboral;
import com.cibertec.app.repository.AreaLaboralRepository;
import com.cibertec.app.service.AreaLaboralService;

@Service
public class AreaLaboralServiceImpl implements AreaLaboralService{

	@Autowired
	AreaLaboralRepository areaLaboralRepository;
	
	@Override
	public List<AreaLaboral> listarTodosAreaLaboral() {
		return areaLaboralRepository.findAll();
	}

	@Override
	public AreaLaboral buscarById(Integer id) {
		return areaLaboralRepository.findById(id).get();
	}

}
