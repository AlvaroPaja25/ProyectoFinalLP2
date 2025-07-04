package com.cibertec.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.cibertec.app.entity.Empresa;
import com.cibertec.app.service.EmpresaService;

@ControllerAdvice
public class GlobalAttributesConfig {

    @Autowired
    private EmpresaService empresaService;

    @ModelAttribute("empresaGlobal")
    public Empresa empresaGlobal() {
        return empresaService.obtenerPorId(1);
    }
}
