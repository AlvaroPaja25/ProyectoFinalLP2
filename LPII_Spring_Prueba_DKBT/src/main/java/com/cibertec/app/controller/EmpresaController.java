package com.cibertec.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cibertec.app.entity.Empresa;
import com.cibertec.app.service.EmpresaService;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/editar/{id}")
    public String mostrarFormulario(@PathVariable Integer id, Model model) {
        Empresa empresa = empresaService.obtenerPorId(id);
        if (empresa != null) {
            model.addAttribute("empresa", empresa);
            return "empresa/empresa_form";
        } else {
            return "redirect:/error";
        }
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarEmpresa(
        @PathVariable Integer id,
        @ModelAttribute Empresa empresaForm,
        @RequestParam("logoFile") MultipartFile logoFile
    ) {
        empresaService.actualizarEmpresa(id, empresaForm, logoFile);
        return "redirect:/empresa/editar/" + id + "?success";
    }

}
