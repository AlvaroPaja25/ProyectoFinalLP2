package com.cibertec.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.app.entity.Categoria;
import com.cibertec.app.repository.CategoriaRepository;
import com.cibertec.app.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService{
	
	@Autowired
	CategoriaRepository categoriaRepository;

	@Override
	public List<Categoria> listarTodosCategoria() {
		return categoriaRepository.findAll();
	}

	@Override
	public Categoria buscarById(Integer id) {
		return categoriaRepository.findById(id).get();
	}
}
