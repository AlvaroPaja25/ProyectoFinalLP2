package com.cibertec.app.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.app.entity.Empleado;
import com.cibertec.app.service.EmpleadoService;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping("/empleados")
public class EmpleadoRestController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/buscar")
    public List<EmpleadoDTO> buscar(@RequestParam("term") String term) {
        List<Empleado> empleados = empleadoService.buscarPorNombreOApellido(term);

        return empleados.stream()
                .map(emp -> new EmpleadoDTO(emp.getId(), emp.getNombres(), emp.getApellidos()))
                .collect(Collectors.toList());
    }

    @Data
    @AllArgsConstructor
    public static class EmpleadoDTO {
        private Long id;
        private String nombres;
        private String apellidos;
    }
}

