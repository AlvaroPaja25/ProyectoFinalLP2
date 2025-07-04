package com.cibertec.app.service;

import org.springframework.web.multipart.MultipartFile;

import com.cibertec.app.entity.Empresa;

public interface EmpresaService {
    void actualizarEmpresa(Integer id, Empresa empresaForm, MultipartFile logoFile);
    Empresa obtenerPorId(Integer id);

}
